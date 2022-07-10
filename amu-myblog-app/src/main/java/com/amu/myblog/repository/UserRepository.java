package com.amu.myblog.repository;

import com.amu.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    //define custom JPQL query
    @Query("select u from User u where u.username =:username and u.email =:email")
    User findByUsernameAndEmailJPQL(@Param("username") String username, @Param("email") String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
