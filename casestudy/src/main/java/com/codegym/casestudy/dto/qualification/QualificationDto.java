package com.codegym.casestudy.dto.qualification;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class QualificationDto implements Validator {
    @NotNull(message = "Lỗi hệ thống, vui lòng liên hệ QTV để được hướng dẫn")
    private Long id;

    @NotBlank(message = "Trường này không được để trống")
    private String name;

    private Long fee;

    @NotBlank(message = "Trường này không được để trống")
    private String description;

    public QualificationDto(String name, Long fee, String description) {
        this.name = name;
        this.fee = fee;
        this.description = description;
    }

    public QualificationDto(Long id, String name, Long fee, String description) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.description = description;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        QualificationDto qualificationDto = (QualificationDto) target;
        Long fee = qualificationDto.getFee();

        if (fee < 0) {
            errors.rejectValue("fee", null, "Học phí không thể mang giá trị âm");
        }
        if (fee.toString().length() == 0) {
            errors.rejectValue("fee", null,"Trường này không được để trống");
        }
    }
}
