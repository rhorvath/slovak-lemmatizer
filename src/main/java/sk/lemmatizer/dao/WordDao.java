package sk.lemmatizer.dao;

import org.springframework.data.repository.CrudRepository;
import sk.lemmatizer.model.Word;

import java.util.List;

public interface WordDao extends CrudRepository<Word, Long> {

    List<Word> findByForm(String form);

    List<Word> findByFormAi(String formAi);

    List<Word> findByLemma(String lemma);

}
