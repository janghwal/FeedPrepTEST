package com.example.feedprep.common.s3.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import org.springframework.core.env.Environment;


@ExtendWith(MockitoExtension.class)
class S3ServiceImplTest {

    @Mock
    private S3Client s3Client;

    @Mock
    private Environment env;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private S3ServiceImpl s3Service;

    @Test
    @DisplayName("업로드 성공")
    void uploadFile_success() {
        // given
        String bucket = "test-bucket";
        String directory = "test-dir";
        String originalFileName = "test.pdf";
        String contentType = "application/pdf";
        byte[] fileContent = "dummy".getBytes();

        MockMultipartFile mockFile = new MockMultipartFile(
            "file",
            originalFileName,
            contentType,
            fileContent
        );

        // when
        when(env.getProperty("aws.s3.bucket")).thenReturn(bucket);

        String result = s3Service.uploadFile(mockFile, directory);

        // then
        assertThat(result).contains(directory);
        assertThat(result).contains(originalFileName);

        verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    @DisplayName("업로드 할 파일이 없음")
    void uploadFile_NOT_FOUND_FILE() {
        // given
        String directory = "test-dir";
        MockMultipartFile emptyFile = new MockMultipartFile(
            "file",
            "empty.pdf",
            "application/pdf",
            new byte[0]
        );

        // when
        CustomException exception = assertThrows(CustomException.class, () ->
            s3Service.uploadFile(emptyFile,directory)
        );

        // then
        assertEquals(ErrorCode.NOT_FOUND_FILE, exception.getErrorCode());
    }

    @Test
    @DisplayName("업로드 중에 문제 발생")
    void uploadFile_S3_UPLOAD_FAILED() {
        // given
        String bucket = "test-bucket";
        String directory = "test-dir";
        String originalFileName = "test.pdf";
        byte[] fileContent = "dummy".getBytes();

        MockMultipartFile mockFile = new MockMultipartFile(
            "file",
            originalFileName,
            "application/pdf",
            fileContent
        );

        // when
        when(env.getProperty("aws.s3.bucket")).thenReturn(bucket);

        doThrow(new RuntimeException("S3 업로드 실패"))
            .when(s3Client)
            .putObject(any(PutObjectRequest.class), any(RequestBody.class));

        CustomException exception = assertThrows(CustomException.class, () ->
            s3Service.uploadFile(mockFile,directory)
        );

        // then
        assertEquals(ErrorCode.S3_UPLOAD_FAILED, exception.getErrorCode());
    }

    @Test
    @DisplayName("파일 삭제 완료")
    void deleteFile_success() {
        // given
        String bucket = "test-bucket";
        String fileKey = "some-file-key";

        // when
        when(env.getProperty("aws.s3.bucket")).thenReturn(bucket);

        s3Service.deleteFile(fileKey);

        // then
        verify(s3Client).deleteObject(any(DeleteObjectRequest.class));
    }

    @Test
    @DisplayName("파일 삭제 실패")
    void deleteFile_DONT_DELETE_S3FILE() {
        // given
        String bucket = "test-bucket";
        String fileKey = "some-file-key";

        // when
        when(env.getProperty("aws.s3.bucket")).thenReturn(bucket);

        doThrow(new RuntimeException("삭제 실패"))
            .when(s3Client).deleteObject(any(DeleteObjectRequest.class));

        CustomException exception = assertThrows(CustomException.class, () -> {
            s3Service.deleteFile(fileKey);
        });

        // then
        assertEquals(ErrorCode.DONT_DELETE_S3FILE, exception.getErrorCode());
    }
}