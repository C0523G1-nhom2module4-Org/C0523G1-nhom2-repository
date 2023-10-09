package com.codegym.casestudy.repository.role;

import com.codegym.casestudy.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
}
