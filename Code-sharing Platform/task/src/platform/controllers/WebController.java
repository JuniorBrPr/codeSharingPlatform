package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import platform.CodeSnippetService;
import platform.models.CodeSnippet;

@Controller
public class WebController {
    CodeSnippetService codeSnippetService;

    public WebController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/code")
    public String getCodeSnippetHtml(Model model) {
        model.addAttribute("code", codeSnippetService.getCodeSnippet().getCode());
        model.addAttribute("date", codeSnippetService.getCodeSnippet().getDate());
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
        return "redirect:/code";
    }
}
