package com.amu.myblog.repository;

import com.amu.myblog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Created 2022/07/06
 * @Author Amu
 */
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        /*String email = "test@test.com";
        Optional<User> user = userRepository.findByEmail(email);
        assertThat(user).isNotNull();*/
    }

    @Test
    void findByUsernameOrEmail() {
    }

    @Test
    void findByUsernameAndEmailJPQL() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void existsByUsername() {
    }

    @Test
    void existsByEmail() {
    }
}