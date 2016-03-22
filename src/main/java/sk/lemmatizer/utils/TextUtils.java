package sk.lemmatizer.utils;


import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Slovak text processing methods.
 */
public class TextUtils {

    private static final String CHARS_TO_REMOVE_REGEXP = "[^a-záäčďéíĺňľóôŕšťúýž]+";

    private TextUtils(){}

    /**
     * Removes an accent from text.
     * @param str text to remove accent from
     * @return text without an accent
     */
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    /**
     * Removes all non alphabetic characters from text.
     * Method expects input to contain lower case characters only.
     * @param str text with lower case only chars
     * @return text without non alphabetic characters
     */
    public static String removeNonAlpha(String str) {
        return str.replaceAll(CHARS_TO_REMOVE_REGEXP, " ").trim();
    }

}
