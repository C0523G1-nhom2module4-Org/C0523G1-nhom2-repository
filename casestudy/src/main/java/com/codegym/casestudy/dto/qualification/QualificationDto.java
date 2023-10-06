package com.codegym.casestudy.dto.qualification;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class QualificationDto implements Validator {
    private Long id;

    @NotBlank(message = "Trường này không được để trống")
    private String name;

    private Long fee;

    @NotBlank(message = "Trường này không được để trống")
    private String description;

    private Integer courseDuration;

    public QualificationDto(String name, Long fee, String description, Integer courseDuration) {
        this.name = name;
        this.fee = fee;
        this.description = description;
        this.courseDuration = courseDuration;
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
            errors.rejectValue("fee", null, "Trường này không được để trống");
        }
        if (qualificationDto.courseDuration <= 0) {
            errors.rejectValue("courseDuration", null, "Giá trị không hợp lệ");
        }
    }
}
