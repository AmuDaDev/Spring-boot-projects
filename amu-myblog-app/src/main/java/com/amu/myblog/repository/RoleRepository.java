package com.amu.myblog.repository;

import com.amu.myblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
