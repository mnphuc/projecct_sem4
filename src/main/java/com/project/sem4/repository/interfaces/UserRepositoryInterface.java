package com.project.sem4.repository.interfaces;

import com.project.sem4.model.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryInterface extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
