package com.codegym.casestudy.service.role;

import com.codegym.casestudy.model.role.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
}
