package sk.lemmatizer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.lemmatizer.dao.WordDao;
import sk.lemmatizer.model.Word;
import sk.lemmatizer.utils.TextUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LemmatizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LemmatizationService.class);

    @Autowired
    private WordDao wordDao;

    public String fastLemmatize(String text, boolean useDatabase, boolean useTvaroslovnik, boolean inputDiacritics, boolean outputDiacritics, boolean keepStructure) {
        StringBuffer stringBuffer = new StringBuffer();
        if (keepStructure) {
            Matcher matcher = Pattern.compile("(\\p{L})+").matcher(text);
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, findSingleLemma(matcher.group(), useDatabase, useTvaroslovnik, inputDiacritics));
            }
            matcher.appendTail(stringBuffer);
        } else {
            String words[] = TextUtils.removeNonAlpha(text.toLowerCase()).split("\\s");
            for (int i = 0; i < words.length; i++) {
                stringBuffer.append(findSingleLemma(words[i], useDatabase, useTvaroslovnik, inputDiacritics));
                if (i < words.length - 1) {
                    stringBuffer.append(" ");
                }
            }
        }
        return outputDiacritics ? stringBuffer.toString() : TextUtils.deAccent(stringBuffer.toString());
    }

    public List<List<Word>> fullLemmatize(String text, boolean useDatabase, boolean useTvaroslovnik, boolean inputDiacritics, boolean outputDiacritics, boolean keepStructure) {
        List<List<Word>> wordList = new ArrayList<List<Word>>();
        for (String word : TextUtils.removeNonAlpha(text.toLowerCase()).split("\\s")) {
            wordList.add(findAllLemmas(word, useDatabase, useTvaroslovnik, inputDiacritics));
        }
        return wordList;
    }

    private String findSingleLemma(String word, boolean useDatabase, boolean useTvaroslovnik, boolean inputDiacritics) {
        String lemma = null;
        if (useDatabase) {
            List<Word> wordList = wordDao.findByForm(word);
            if (wordList.isEmpty() && !inputDiacritics) {
                wordList = wordDao.findByFormAi(word);
            }
            if (!wordList.isEmpty()) {
                lemma = wordList.get(0).getLemma();
            }
        }
        if (lemma == null && useTvaroslovnik) {
            //TODO: add tvaroslovnik implementation
        }
        return lemma != null ? lemma : word;
    }

    private List<Word> findAllLemmas(String word, boolean useDatabase, boolean useTvaroslovnik, boolean inputDiacritics) {
        List<Word> wordList = new ArrayList<Word>();
        if (useDatabase) {
            wordList.addAll(wordDao.findByForm(word));
            if (!inputDiacritics) {
                wordList.addAll(wordDao.findByFormAi(word));
            }
        }
        if (useTvaroslovnik) {
            //TODO: add tvaroslovnik implementation
        }
        return wordList;
    }

}
