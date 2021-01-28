package com.heman.platform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.heman.platform.entity.CodeSnippet;
import com.heman.platform.service.CodeSnippetService;

import java.util.List;
import java.util.UUID;

@RestController
public class CodeRestController {
    @Autowired
    CodeSnippetService service;

    @GetMapping(value = "/api/code/{id}")
    public ResponseEntity<String> view(@PathVariable("id") UUID id) throws JsonProcessingException {
        final var codeSnippet = service.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no code with the given ID")
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(codeSnippet);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }


    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<String> latest() throws JsonProcessingException {
        List<CodeSnippet> latest = service.findLatest();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(latest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }


    @PostMapping("/api/code/new")
    public ResponseEntity<String> create(@RequestBody CodeSnippet codeSnippetNew) throws JsonProcessingException {
        CodeSnippet codeSnippet = service.save(codeSnippetNew);
        System.out.println("[DEBUG] " + codeSnippet);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("id", String.valueOf(codeSnippet.getId()));
        String json = mapper.writeValueAsString(jsonObject);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }
}
