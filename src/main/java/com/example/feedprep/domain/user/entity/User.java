package com.example.feedprep.domain.user.entity;

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.techstack.entity.UserTechStack;
import com.example.feedprep.domain.user.enums.UserRole;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String name;

    private String email;

    private String password;

    private String address;

    private String introduction;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Long point;

    private Double rating;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user")
    private List<Document> documents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserTechStack> userTechStacks = new ArrayList<>();

    public User(String name, String email, String password, String address, String introduction,
        UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.introduction = introduction;
        this.role = role;
        this.point = 0L;
    }

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.point = 0L;
    }

    public void withdraw() {
        this.deletedAt = LocalDateTime.now();
    }
}
