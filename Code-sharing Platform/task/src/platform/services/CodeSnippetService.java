package platform.services;

import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.models.repositories.CodeSnippetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CodeSnippetService {
    CodeSnippetRepository codeSnippetRepository;

    public CodeSnippetService(CodeSnippetRepository codeSnippetRepository) {
        this.codeSnippetRepository = codeSnippetRepository;
    }

    public CodeSnippet getCodeSnippet(int id) {
        return codeSnippetRepository.getCodeSnippet(id);
    }

    public int createSnippet(String snippet) {
        codeSnippetRepository.setCodeSnippet(new CodeSnippet(snippet, LocalDate.now()));
        return codeSnippetRepository.getCounter() - 1;
    }

    public List<CodeSnippet> getLatestSnippets() {
        return codeSnippetRepository.getLatestSnippets();
    }
}
