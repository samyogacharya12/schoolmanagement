package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.enumconstants.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByName(String name);

    List<User> findByRoles(UserType userType);
}
