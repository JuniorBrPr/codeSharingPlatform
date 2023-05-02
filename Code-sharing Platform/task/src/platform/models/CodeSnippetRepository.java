package platform.models;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class CodeSnippetRepository {
    private CodeSnippet codeSnippet;

    public CodeSnippetRepository() {
        this.codeSnippet = new CodeSnippet("public static void main(String[] args) {" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);" +
                "}", LocalDate.now());
    }

    public CodeSnippet getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(CodeSnippet codeSnippet) {
        this.codeSnippet = codeSnippet;
    }
}
