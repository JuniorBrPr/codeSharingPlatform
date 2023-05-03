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
        return codeSnippetRepository.findById(id);
    }

    public long createSnippet(String snippet) {
        return codeSnippetRepository.save(new CodeSnippet(snippet, LocalDate.now())).getId();
    }

    public List<CodeSnippet> getLatestSnippets() {
        return codeSnippetRepository.findTop10ByOrderByIdDesc();
    }
}
