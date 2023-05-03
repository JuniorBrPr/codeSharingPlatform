package platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import platform.services.CodeSnippetService;
import platform.models.CodeSnippet;

@Controller
public class WebController {
    CodeSnippetService codeSnippetService;

    public WebController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/code/{id}")
    public String getCodeSnippetHtml(@PathVariable int id, Model model) {
        model.addAttribute("codeSnippet", codeSnippetService.getCodeSnippet(id));
        return "code";
    }

    @GetMapping("/code/new")
    public String postCodeSnippetHtml(@ModelAttribute CodeSnippet snippet, Model model) {
        model.addAttribute("snippet", snippet);
        return "newSnippet";
    }

    @PostMapping("/code/new")
    public String postCodeSnippetHtml(@ModelAttribute CodeSnippet snippet) {
        codeSnippetService.createSnippet(snippet.getCode());
        return "redirect:/latestSnippets";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeSnippetsHtml(Model model) {
        model.addAttribute("codeSnippets", codeSnippetService.getLatestSnippets());
        return "latestSnippets";
    }
}
