package com.example.teste.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
