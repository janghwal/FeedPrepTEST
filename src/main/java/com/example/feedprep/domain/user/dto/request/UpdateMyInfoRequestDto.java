package com.example.feedprep.domain.user.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMyInfoRequestDto {

    private String name;
    private String address;
    private String introduction;
}
