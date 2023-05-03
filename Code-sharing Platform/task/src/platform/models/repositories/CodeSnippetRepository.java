package platform.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;

import java.util.List;

@Service
public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, Long> {
    CodeSnippet findById(long id);
    List<CodeSnippet> findTop10ByOrderByIdDesc();
}
