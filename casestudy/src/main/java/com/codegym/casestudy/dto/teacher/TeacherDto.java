package com.codegym.casestudy.dto.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherDto teacherDto = (TeacherDto) target;
        if (teacherDto.birthday.equals("")) {
            errors.rejectValue("birthday", null, "Không được để trống!");
            if (teacherDto.getName().equals("")) {
                errors.rejectValue("name", null, "Không được để trống!");
            }
            if (teacherDto.getAddress().equals("")) {
                errors.rejectValue("address", null, "Không được để trống!");
            }
            if (teacherDto.getIdentity().equals("")) {
                errors.rejectValue("identity", null, "Không được để trống!");
            } else if (!teacherDto.getIdentity().matches("^[0-9]{9,12}$")) {
                errors.rejectValue("identity", null, "Số CMND là các số từ (0-9) có chiều dài là 9 đến 12 số ");
            }
            if (teacherDto.getPhone().equals("")) {
                errors.rejectValue("phone", null, "Không được để trống!");
            } else if (!teacherDto.getPhone().matches("^0[0-9]{9,11}$")) {
                errors.rejectValue("phone", null, "Số điện thoại phải đúng định dạng (0)xxxxxxxxx với x là các số từ (0-9)");
            }
        } else {
            LocalDate temp = LocalDate.parse(teacherDto.birthday).plusYears(18);
            LocalDate dayNow = LocalDate.now();
            if (teacherDto.getName().equals("")) {
                errors.rejectValue("name", null, "Không được để trống!");
            }
            if (teacherDto.getAddress().equals("")) {
                errors.rejectValue("address", null, "Không được để trống!");
            }
            if (teacherDto.getIdentity().equals("")) {
                errors.rejectValue("identity", null, "Không được để trống!");
            } else if (!teacherDto.getIdentity().matches("^[0-9]{9,12}$")) {
                errors.rejectValue("identity", null, "Số CMND là các số từ (0-9) có chiều dài là 9 đến 12 số ");
            }
            if (teacherDto.getPhone().equals("")) {
                errors.rejectValue("phone", null, "Không được để trống!");
            } else if (!teacherDto.getPhone().matches("^0[0-9]{9,11}$")) {
                errors.rejectValue("phone", null, "Số điện thoại phải đúng định dạng (0)xxxxxxxxx với x là các số từ (0-9)");
            }
            if (temp.isAfter(dayNow)) {
                errors.rejectValue("birthday", null, "chua du 18 tuoi");

            }
        }
}
}
