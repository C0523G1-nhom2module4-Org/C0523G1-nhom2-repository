package com.codegym.casestudy.dto.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClassesDto implements Validator {

    private int id;
    private String className;
    private boolean isDeleted;
    private List<String> existingClassNames;


    @Override
    public boolean supports(Class<?> clazz) {
        return ClassesDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClassesDto classesDto = (ClassesDto) target;
        String name = classesDto.getClassName();
        List<String> existingClassNames = classesDto.getExistingClassNames();
        if (!name.startsWith("K0")) {
            errors.rejectValue("name", "invalid", "Tên lớp phải bắt đầu bằng K0");
        } else if (existingClassNames != null && existingClassNames.contains(name)) {
            errors.rejectValue("name", "duplicate", "Tên lớp đã tồn tại");
        }
    }
}
