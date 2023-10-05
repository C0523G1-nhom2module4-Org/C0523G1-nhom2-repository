package com.codegym.casestudy.dto.contract;

import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto implements Validator {
    private int id;
    private long fee;
    private String date;
    private Student student;
    private Qualification qualification;
    private int status;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDto contractDto = (ContractDto) target;

        if (contractDto.date == null){
            errors.rejectValue("date","invalid","Ngày kí hợp đồng không được để trống");
        } else if (contractDto.date.equals("")) {
            errors.rejectValue("date","invalid","Ngày kí hợp đồng không được để trống");
        }

        if (contractDto.fee < 0){
            errors.rejectValue("fee","invalid","Vui lòng nhập đúng giá trị ( > 0) ");
        }
    }
}
