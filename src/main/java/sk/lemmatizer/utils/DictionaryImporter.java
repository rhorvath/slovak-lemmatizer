package sk.lemmatizer.utils;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sk.lemmatizer.dao.WordDao;
import sk.lemmatizer.model.Word;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DictionaryImporter implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryImporter.class);

    private final WordDao wordDao;

    @Autowired
    public DictionaryImporter(WordDao wordDao) {
        this.wordDao = wordDao;
    }

    @Override
    public void run(String... args) throws Exception {
        for (String arg : args) {
            if (arg.startsWith("import") && arg.contains("=")) {
                this.importWords(arg.split("=")[1]);
            }
        }
    }

    private void importWords(String filepath) throws IOException {
        LOGGER.warn("Starting dictionary import");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
        CSVReader reader = new CSVReader(isr, '\t', '\"', 1);
        String[] row = reader.readNext();
        while (row != null) {
            Word word = new Word();
            word.setLemma(row[0]);
            word.setForm(row[1]);
            word.setFormAi(TextUtils.deAccent(row[1]));
            word.setTag(row[2]);
            wordDao.save(word);
            row = reader.readNext();
        }
        reader.close();
        LOGGER.warn("Dictionary import finished");
    }

}
