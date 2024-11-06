package com.example.demo.service;

import com.example.demo.model.MyEntity;
import com.example.demo.repository.MyEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MyEntityService {

    private MyEntityRepository repo;

    public MyEntityService(
        final MyEntityRepository repo
    ) {
        this.repo = repo;
    }

    @Transactional
    public MyEntity save(MyEntity myEntity) {
        return this.repo.save(myEntity);
    }

    @Transactional(readOnly = true)
    public List<MyEntity> getAll() {
        return this.repo.findAllNonSoftDeleted();
    }

    public Optional<MyEntity> getById(UUID id) {
        return this.repo.findById(id);
    }


    @Transactional
    public MyEntity softDelete(MyEntity myEntity) {
        myEntity.setDeleted(System.currentTimeMillis());
        return this.repo.save(myEntity);
    }

    @Transactional
    public void hardDeleteAllSoftDeletedEntities() {
        this.repo.deleteAllSoftDeleted();
    }
}
