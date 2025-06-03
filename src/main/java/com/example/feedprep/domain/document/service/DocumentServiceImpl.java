package com.example.feedprep.domain.document.service;


import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.document.dto.responseDto.DocumentListResponseDto;
import com.example.feedprep.domain.document.dto.responseDto.DocumentResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.repository.DocumentRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final S3Client s3Client;
    private final Environment env;

    @Override
    @Transactional
    public DocumentResponseDto createDocument(MultipartFile file, String resume, Long tokenMyId) {

        if(file.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_FILE);
        }

        User user = userRepository.findByIdOrElseThrow(tokenMyId);

        // user의 이력서는 최대 5개까지 생성 제한
        long cntDocument = documentRepository.countByUser(user);
        if(cntDocument >= 5) {
            throw new CustomException(ErrorCode.DONT_CREATE_MORE);
        }

        // 접속 할 버켓 이름
        String bucket = env.getProperty("aws.s3.bucket");

        // 램덤 UUID로 파일이름 중복 방지 - 중복될 확률이 거의 0에 수렴.
        String fileName = resume + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .contentType(file.getContentType())
            .acl(ObjectCannedACL.PRIVATE).build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (Exception e) {
            throw new CustomException(ErrorCode.S3_UPLOAD_FAILED);
        }

        Document document = Document.builder().user(user).fileUrl(fileName).build();

        Document saveDocument = documentRepository.save(document);

        return new DocumentResponseDto(
            saveDocument.getDocumentId(),
            saveDocument.getUser().getName(),
            saveDocument.getUser().getRole(),
            saveDocument.getFileUrl(),
            saveDocument.getCreatedAt(),
            saveDocument.getModifiedAt()
        );
    }

    @Override
    public List<DocumentListResponseDto> getMyDocumentList(Long tokenMyId) {

        List<Document> documentList = documentRepository.findAllByUserUserId(tokenMyId);

        List<DocumentListResponseDto> documentListResponseDtos =
            documentList.stream().map(document -> new DocumentListResponseDto(
                document.getDocumentId(),
                document.getCreatedAt(),
                document.getModifiedAt()
            )).collect(Collectors.toList());

        return documentListResponseDtos;
    }

    @Override
    public DocumentResponseDto getMyDocument(Long documentId, Long tokenMyId) {

        Document document = documentRepository.findByIdOrElseThrow(documentId);

        // 본인 문서가 아니면 접근 권한 없음
        if(!document.getUser().getUserId().equals(tokenMyId)){
            throw new CustomException(ErrorCode.FOREIGN_DOCUMENT_ACCESS);
        }

        return new DocumentResponseDto(document);
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId, Long tokenMyId) {
        Document document = documentRepository.findByIdOrElseThrow(documentId);

        // 본인 문서가 아니면 접근 권한 없음
        if(!document.getUser().getUserId().equals(tokenMyId)){
            throw new CustomException(ErrorCode.FOREIGN_DOCUMENT_ACCESS);
        }

        String fileUrl = document.getFileUrl();
        String bucket = env.getProperty("aws.s3.bucket");

        documentRepository.delete(document);

        try{
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(fileUrl)
                .build();

            s3Client.deleteObject(deleteObjectRequest);

        } catch (Exception e) {
            throw new CustomException(ErrorCode.DONT_DELETE_S3FILE);
        }
    }
}
