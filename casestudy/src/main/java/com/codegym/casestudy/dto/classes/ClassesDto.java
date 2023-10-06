package com.codegym.casestudy.dto.classes;

import com.codegym.casestudy.model.classes.Classes;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
public class ClassesDto implements Validator {

    private int id;
    private String className;
    private String description;
    private String startDate;
    private String endDate;
    private boolean isDeleted;
    private List<Classes> classList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Classes> getClassList() {
        return classList;
    }

    public void setClassList(List<Classes> classList) {
        this.classList = classList;
    }

    public ClassesDto() {
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return ClassesDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClassesDto classesDto = (ClassesDto) target;
        String name = classesDto.getClassName();
        List<Classes> classList1 = classesDto.getClassList();
        if ("K0-123".equals(name)) {
            errors.rejectValue("className", null, "Tên lớp phải bắt đầu bằng K0");
        } else if (classList1 != null && classList1.contains(name)) {
            errors.rejectValue("className", null, "Tên lớp đã tồn tại");
        }
    }
}
