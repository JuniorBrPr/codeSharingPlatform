package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import platform.models.CodeSnippet;
import platform.models.dtos.CodeSubmitDTO;
import platform.services.CodeSnippetService;

@Controller
public class WebController {
    CodeSnippetService codeSnippetService;

    public WebController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/code/{UUID}")
    public String getCodeSnippetHtml(@PathVariable String UUID, Model model) {
        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet(UUID);
        if (codeSnippet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            model.addAttribute("codeSnippet", codeSnippet);
            return "code";
        }
    }

    @GetMapping("/code/new")
    public String postCodeSnippetHtml(@ModelAttribute CodeSubmitDTO snippet, Model model) {
        model.addAttribute("snippet", snippet);
        return "newSnippet";
    }

    @PostMapping("/code/new")
    public String postCodeSnippetHtml(@ModelAttribute CodeSubmitDTO snippet) {
        codeSnippetService.createSnippet(
                snippet.code(),
                Long.parseLong(snippet.time()),
                Long.parseLong(snippet.views()));
        return "redirect:latestSnippets";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeSnippetsHtml(Model model) {
        model.addAttribute("codeSnippets", codeSnippetService.getLatestSnippets());
        return "latestSnippets";
    }
}
