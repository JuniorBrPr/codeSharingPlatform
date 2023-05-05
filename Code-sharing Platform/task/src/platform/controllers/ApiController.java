package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import platform.models.CodeSnippet;
import platform.models.dtos.CodeSubmitDTO;
import platform.services.CodeSnippetService;

@Controller
public class ApiController {
    CodeSnippetService codeSnippetService;

    public ApiController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code/{UUID}")
    public ResponseEntity<?> getCodeSnippet(@PathVariable String UUID) {
        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet(UUID);
        if (codeSnippet == null) {
            return new ResponseEntity<>("{\"message\": \"Code snippet not found\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(codeSnippet, HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postCodeSnippet(@RequestBody CodeSubmitDTO codeSnippet) {
        try {
            Long.parseLong(codeSnippet.time());
            Long.parseLong(codeSnippet.views());
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("{\"message\": \"Invalid time or views\"}", HttpStatus.BAD_REQUEST);
        }

        String id = codeSnippetService.createSnippet(
                codeSnippet.code(),
                Long.parseLong(codeSnippet.time()),
                Long.parseLong(codeSnippet.views()));

        if (id == null) {
            return new ResponseEntity<>("{\"message\": \"Bad Request\"}", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>("{\"id\": \"%s\"}"
                .formatted(id), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatestCodeSnippets() {
        return new ResponseEntity<>(codeSnippetService.getLatestSnippets(), HttpStatus.OK);
    }
}
