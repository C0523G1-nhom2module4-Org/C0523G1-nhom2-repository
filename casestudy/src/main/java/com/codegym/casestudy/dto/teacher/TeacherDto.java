package com.codegym.casestudy.dto.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDto implements Validator {
    private int id;
    private String name;
    private String identity;
    private int gender;
    private String birthday;
    private String phone;
    private int salary;
    private String address;
    private int status;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherDto teacherDto = (TeacherDto) target;
        if (teacherDto.getName().equals("")) {
            errors.rejectValue("name", null, "Không được để trống!");
        }
        if (teacherDto.getAddress().equals("")) {
            errors.rejectValue("address", null, "Không được để trống!");
        } else if (!teacherDto.getAddress().matches("^[0-9]{1,4} (([A-Z][a-z]+) )+([A-Z][a-z]+){1}$")) {
            errors.rejectValue("address", null, "Địa chỉ phải bắt đầu với số nhà và tên đường\nVD: 123 Dien Bien Phu");
        }
        if (teacherDto.getIdentity().equals("")) {
            errors.rejectValue("identity", null, "Không được để trống!");
        } else if (!teacherDto.getIdentity().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("identity", null, "Số CMND là các số từ (0-9) có chiều dài là 9 đến 12 số ");
        }
        if (teacherDto.getPhone().equals("")) {
            errors.rejectValue("phone", null, "Không được để trống!");
        } else if (!teacherDto.getPhone().matches("^0[0-9]{9,11}$")) {
            errors.rejectValue("phone", null, "Số điện thoại phải đúng định dạng (0)xxxxxxxxx hoặc (0)xxxxxxxxxxx với x là các số từ (0-9)");
        }
    }
}
