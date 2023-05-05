package platform.services;

import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.models.repositories.CodeSnippetRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CodeSnippetService {
    CodeSnippetRepository codeSnippetRepository;
    DateTimeFormatter dateTimeFormatter;

    public CodeSnippetService(CodeSnippetRepository codeSnippetRepository, DateTimeFormatter dateTimeFormatter) {
        this.codeSnippetRepository = codeSnippetRepository;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CodeSnippet getCodeSnippet(String UUID) {
        CodeSnippet codeSnippet = codeSnippetRepository.findByUUID(UUID);
        if (codeSnippet == null) {
            return null;
        }

        if (codeSnippet.isViewRestriction()) {
            if (codeSnippet.getViews() > 0) {
                codeSnippet.setViews(codeSnippet.getViews() - 1);
                codeSnippetRepository.save(codeSnippet);
            } else {
                codeSnippetRepository.delete(codeSnippet);
                return null;
            }
        }
        if (codeSnippet.isTimeRestriction()) {
            long newTime = codeSnippet.getTime() -
                    Duration.between(LocalDateTime.parse(codeSnippet.getDate(), dateTimeFormatter),
                            LocalDateTime.now()).toSeconds();
            codeSnippet.setTime(newTime);
            if (codeSnippet.getTime() <= 0) {
                codeSnippetRepository.delete(codeSnippet);
                return null;
            }
        }
        return codeSnippet;
    }

    public String createSnippet(String snippet, long time, long views) {
        String now = LocalDateTime.now().format(dateTimeFormatter);
        CodeSnippet codeSnippet = new CodeSnippet(snippet, now, views, time);
        try {
            codeSnippet = codeSnippetRepository.save(codeSnippet);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return codeSnippet.getUUID();
    }

    public List<CodeSnippet> getLatestSnippets() {
        return codeSnippetRepository.findTop10ByTimeRestrictionFalseAndViewRestrictionFalseOrderByIdDesc();
    }
}
