package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import platform.services.CodeSnippetService;
import platform.models.dtos.CodeSubmitDTO;

@Controller
public class ApiController {
    CodeSnippetService codeSnippetService;

    public ApiController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<?> getCodeSnippet(@PathVariable int id) {
        return new ResponseEntity<>(codeSnippetService.getCodeSnippet(id), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postCodeSnippet(@RequestBody CodeSubmitDTO codeSnippet) {
        return new ResponseEntity<>("{id:\"%d\"}".formatted(codeSnippetService.createSnippet(codeSnippet.code())),
                HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatestCodeSnippets() {
        return new ResponseEntity<>(codeSnippetService.getLatestSnippets(), HttpStatus.OK);
    }
}
