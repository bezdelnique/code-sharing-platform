package com.heman.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heman.platform.entity.CodeSnippet;
import com.heman.platform.repository.CodeSnippetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CodeSnippetServiceImpl implements CodeSnippetService {
    @Autowired
    CodeSnippetRepository repo;


    @Override
    public List<CodeSnippet> findLatest() {
        List<CodeSnippet> codeSnippets = new ArrayList<>();
        int i = 1;
        for (CodeSnippet codeSnippet : repo.findByOrderByCreatedDesc()) {
            if (codeSnippet.hasRestriction()) {
                continue;
            }
            // repo.save(codeSnippet);

            codeSnippets.add(codeSnippet);
            if (i == 10) {
                break;
            }
            i++;
        }
        return codeSnippets;
    }

    @Override
    public Optional<Object> findById(UUID id) {
        Optional<Object> codeSnippetOptional = repo.findById(id);

        if (codeSnippetOptional.isPresent()) {
            CodeSnippet codeSnippet = (CodeSnippet) codeSnippetOptional.get();

            if (!codeSnippet.canShow()) {
                return Optional.empty();
            }

            repo.save(codeSnippet);
        }

        return codeSnippetOptional;
    }

    @Override
    public CodeSnippet save(CodeSnippet codeSnippet) {
        return repo.save(codeSnippet);
    }
}
