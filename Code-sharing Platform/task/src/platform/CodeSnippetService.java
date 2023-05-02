package platform;

import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.models.CodeSnippetRepository;

import java.time.LocalDate;

@Service
public class CodeSnippetService {
    CodeSnippetRepository codeSnippetRepository;

    public CodeSnippetService(CodeSnippetRepository codeSnippetRepository) {
        this.codeSnippetRepository = codeSnippetRepository;
    }

    public CodeSnippet getCodeSnippet() {
        return codeSnippetRepository.getCodeSnippet();
    }

    public void createSnippet(String snippet) {
        codeSnippetRepository.setCodeSnippet(new CodeSnippet(snippet, LocalDate.now()));
    }
}
