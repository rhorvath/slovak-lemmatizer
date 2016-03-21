package sk.lemmatizer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.lemmatizer.dao.WordDao;
import sk.lemmatizer.model.Word;


import java.util.List;

@Service
public class LemmatizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LemmatizationService.class);

    @Autowired
    private WordDao wordDao;

    public String fastLemmatize(String text, boolean useDatabase, boolean useTvaroslovnik, boolean inputDiacritics, boolean outputDiacritics, boolean keepStructure) {
        throw new UnsupportedOperationException("not implemented");
    }

    public List<List<Word>> fullLemmatize(String text, boolean useDatabase, boolean useTvaroslovnik, boolean inputDiacritics, boolean outputDiacritics, boolean keepStructure) {
        throw new UnsupportedOperationException("not implemented");
    }

}
