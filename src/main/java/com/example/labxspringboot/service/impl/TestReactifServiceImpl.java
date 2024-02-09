package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.entity.TestReactif;
import com.example.labxspringboot.repository.ITestReactifRepository;
import com.example.labxspringboot.service.ITestReactifService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestReactifServiceImpl implements ITestReactifService {
    @Autowired
    private ITestReactifRepository iTestReactifRepository;
    @Override
    public TestReactif addTestReactif(TestReactif testReactif) {
        return iTestReactifRepository.save(testReactif);
    }
}
