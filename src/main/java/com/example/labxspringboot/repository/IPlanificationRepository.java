package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Planification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanificationRepository extends JpaRepository<Planification , Long> {
}
