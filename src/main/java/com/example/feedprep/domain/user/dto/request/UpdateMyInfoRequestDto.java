package com.example.feedprep.domain.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMyInfoRequestDto {

    private String name;
    private String address;
    private String introduction;
}
