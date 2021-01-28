package com.heman.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import com.heman.platform.entity.CodeSnippet;
import com.heman.platform.service.CodeSnippetService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class CodeController {
    @Autowired
    CodeSnippetService service;


    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ModelAndView view(@PathVariable("id") UUID id) {
        final var codeSnippet = service.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no code with the given ID")
        );

        var params = new HashMap<String, Object>();
        params.put("codeSnippet", codeSnippet);
        return new ModelAndView("code", params);
    }


    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ModelAndView latest() {
        List<CodeSnippet> codeSnippets = service.findLatest();

        var params = new HashMap<String, Object>();
        params.put("latest", codeSnippets);
        return new ModelAndView("latest", params);
    }


    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ModelAndView create() {
        return new ModelAndView("code_new");
    }
}
