package com.codegym.casestudy.dto.contact_email_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactEmailDto {
    private String contactName;
    private String emailAddress;
    private String emailSubject;
    private String emailContent;
}
