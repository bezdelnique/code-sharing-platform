package com.heman.platform;

public class CodeSnippetNotFoundException extends RuntimeException {
    CodeSnippetNotFoundException(int id) {
        super("Code snippet " + id + " not found");
    }
}
