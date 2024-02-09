package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Reactif;
import com.example.labxspringboot.entity.TestAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITestAnalyseRepository extends JpaRepository<TestAnalyse,Long> {

    List<TestAnalyse> findByDeletedFalse();
    Optional<TestAnalyse> findByIdAndDeletedFalse(Long id);


}
