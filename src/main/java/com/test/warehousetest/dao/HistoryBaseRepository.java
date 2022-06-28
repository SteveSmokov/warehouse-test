package com.test.warehousetest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface HistoryBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    @Override
    @Query("select e from #{#entityName} e where e.active = true")
    @Transactional(readOnly = true)
    List<T> findAll();

    @Override
    @Query("update #{#entityName} e set e.active=false where e.id = :id")
    @Modifying
    @Transactional
    void deleteById(ID id);

    @Query("select count(e) from #{#entityName} e where e.active = false")
    long countInActives();

    @Override
    @Query("select count(e) from #{#entityName} e where e.active = true")
    long count();

    @Override
    @Query("select e from #{#entityName} e where e.id=:id and e.active = true")
    @Transactional(readOnly = true)
    Optional<T> findById(ID id);

    @Query("select e from #{#entityName} e where e.id=:id")
    @Transactional(readOnly = true)
    Optional<T> findAnyById(ID id);
}
