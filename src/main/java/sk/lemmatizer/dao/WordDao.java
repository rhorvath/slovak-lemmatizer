package sk.lemmatizer.dao;

import org.springframework.data.repository.CrudRepository;
import sk.lemmatizer.model.Word;

import java.util.List;

public interface WordDao extends CrudRepository<Word, Long> {

    public List<Word> findByForm(String form);

    public List<Word> findByFormAi(String formAi);

    public List<Word> findByLemma(String lemma);

}
