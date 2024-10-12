package com.cerimonial.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends MongoRepository<T, ID> {
    @Query("{ id: ?0 }")
    @Update("{ $set: { deletedAt: new Date() } }")
    void deleteById(ID id);

    @Query("{ id: ?0, deletedAt: null }")
    Optional<T> findById(ID id);

    @Override
    @Query("{ deletedAt: null }")
    List<T> findAll();

    @Override
    @Query("{ deletedAt: null }")
    Page<T> findAll(Pageable pageable);
}
