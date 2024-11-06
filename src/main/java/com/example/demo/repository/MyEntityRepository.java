package com.example.demo.repository;

import com.example.demo.model.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MyEntityRepository extends JpaRepository<MyEntity, UUID> {

    @Modifying
    @Query(
        value = """
        DELETE
        FROM
            MyEntity AS me
        WHERE
            me.deleted IS NOT NULL
        """
    )
    public void deleteAllSoftDeleted();

    @Query(
        value = """
        SELECT me
        FROM
            MyEntity AS me
        WHERE
            me.deleted IS NULL
        """
    )
    public List<MyEntity> findAllNonSoftDeleted();

}
