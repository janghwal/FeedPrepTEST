package com.example.feedprep.domain.user.dto.response;

import com.example.feedprep.domain.techstack.entity.UserTechStack;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String address;
    private String introduction;
    private UserRole role;
    private Double rating;
    private List<UserTechStack> userTechStacks;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.introduction = user.getIntroduction();
        this.role = user.getRole();
        this.rating = user.getRating();
        this.userTechStacks = user.getUserTechStacks();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
