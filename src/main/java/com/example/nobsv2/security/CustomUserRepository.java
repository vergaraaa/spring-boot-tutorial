package com.example.nobsv2.security;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, String> {
}
