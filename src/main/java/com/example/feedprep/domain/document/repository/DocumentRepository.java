package com.example.feedprep.domain.document.repository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Long countByUser(User user);

    List<Document> findAllByUserUserId(Long tokenId);

    default Document findByIdOrElseThrow(Long documentId) {
        return findById(documentId).orElseThrow(
            () -> new CustomException(ErrorCode.NOT_FOUND_DOCUMENT));
    }
}
