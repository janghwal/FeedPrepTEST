package com.example.feedprep.domain.document.service;


import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.document.dto.responseDto.DocumentResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.repository.DocumentRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
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
    public DocumentResponseDto createDocument(MultipartFile file, String resume, Long tokenMyId) {

        User user = userRepository.findByIdOrElseThrow(tokenMyId);

        String bucket = env.getProperty("aws.s3.bucket");

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
}
