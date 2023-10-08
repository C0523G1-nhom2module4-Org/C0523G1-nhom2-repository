package com.codegym.casestudy.dto.student;

import com.codegym.casestudy.model.classes.Classes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListStudentDto implements Validator {
    private int id;
    private String name;
    private String identity;
    private int gender;
    private String birthday;
    private String phone;
    private int graduatePoint;
    private String address;
    private Classes classes;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ListStudentDto studentDto = (ListStudentDto) target;
        LocalDate temp = LocalDate.parse(studentDto.birthday).plusYears(18);
        LocalDate dayNow = LocalDate.now();

        if (studentDto.getName().equals("")) {
            errors.rejectValue("name", null, "Không được để trống!");
        }
        if (studentDto.getAddress().equals("")) {
            errors.rejectValue("address", null, "Không được để trống!");
        }
        if (studentDto.getIdentity().equals("")) {
            errors.rejectValue("identity", null, "Không được để trống!");
        } else if (!studentDto.getIdentity().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("identity", null, "Số CMND là các số từ (0-9) có chiều dài là 9 đến 12 số ");
        }
        if (studentDto.getPhone().equals("")) {
            errors.rejectValue("phone", null, "Không được để trống!");
        } else if (!studentDto.getPhone().matches("^0[0-9]{9,11}$")) {
            errors.rejectValue("phone", null, "Số điện thoại phải đúng định dạng (0)xxxxxxxxx hoặc với x là các số từ (0-9)");
        }
        if (temp.isAfter(dayNow)) {
            errors.rejectValue("birthday", null, "Chưa đủ 18 tuổi");
        }
    }
}
