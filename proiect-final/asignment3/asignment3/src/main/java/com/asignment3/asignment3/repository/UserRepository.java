package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameIgnoreCase(String username);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
