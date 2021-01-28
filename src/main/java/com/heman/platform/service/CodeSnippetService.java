package com.heman.platform.service;

import com.heman.platform.entity.CodeSnippet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeSnippetService {
    List<CodeSnippet> findLatest();

    Optional<Object> findById(UUID id);

    CodeSnippet save(CodeSnippet codeSnippet);
}
