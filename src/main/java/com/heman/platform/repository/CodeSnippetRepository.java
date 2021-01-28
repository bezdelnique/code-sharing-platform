package com.heman.platform.repository;


import org.springframework.data.repository.CrudRepository;
import com.heman.platform.entity.CodeSnippet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, Integer> {
    List<CodeSnippet> findByOrderByCreatedDesc();

    Optional<Object> findById(UUID id);
}
