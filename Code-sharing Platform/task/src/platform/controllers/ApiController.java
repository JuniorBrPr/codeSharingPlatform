package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import platform.CodeSnippetService;
import platform.models.dtos.CodeSubmitDTO;

@Controller
public class ApiController {
    CodeSnippetService codeSnippetService;

    public ApiController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code")
    public ResponseEntity<?> getCodeSnippet() {
        return new ResponseEntity<>(codeSnippetService.getCodeSnippet(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postCodeSnippet(@RequestBody CodeSubmitDTO codeSnippet) {
        codeSnippetService.createSnippet(codeSnippet.code());
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
