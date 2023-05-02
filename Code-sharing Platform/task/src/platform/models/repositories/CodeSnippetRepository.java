package platform.models.repositories;

import org.springframework.stereotype.Repository;
import platform.models.CodeSnippet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CodeSnippetRepository {
    static private int counter = 1;
    private final ConcurrentHashMap<Integer, CodeSnippet> database;

    public CodeSnippetRepository() {
        this.database = new ConcurrentHashMap<>();
    }

    public CodeSnippet getCodeSnippet(int id) {
        return database.get(id);
    }

    public void setCodeSnippet(CodeSnippet codeSnippet) {
        database.put(counter++, codeSnippet);
    }

    public int getCounter() {
        return counter;
    }

    public List<CodeSnippet> getLatestSnippets() {
        ArrayList<CodeSnippet> list = new ArrayList<>(
                database
                        .values()
                        .stream()
                        .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                        .toList()
                        .subList(Math.max(0, database.size() - 10), database.size())
        );
        Collections.reverse(list);
        return list;
    }
}
