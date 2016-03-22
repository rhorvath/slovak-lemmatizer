package sk.lemmatizer.utils;

import junit.framework.TestCase;
import org.junit.Test;

public class TextUtilsTest extends TestCase {

    @Test
    public void testDeAccentAlphanumAndSpecials() throws Exception {
        assertEquals(TextUtils.deAccent(
                "AaÁáÄäBbCcČčDdĎďDZdzDŽdžEeÉéFfGgHhCHchIiÍíJjKkLlĹĺĽľMmNnŇňOoÓóÔôPpQqRrŔŕSsŠšTtŤťUuÚúVvWwXxYyÝýZzŽž0123456789!@#$%^&*()_+}{: "),
                "AaAaAaBbCcCcDdDdDZdzDZdzEeEeFfGgHhCHchIiIiJjKkLlLlLlMmNnNnOoOoOoPpQqRrRrSsSsTtTtUuUuVvWwXxYyYyZzZz0123456789!@#$%^&*()_+}{: ");
    }

    @Test
    public void testDeAccentWhiteSpaces() throws Exception {
        assertEquals(TextUtils.deAccent(
                "Hello world!  time in sk:   čas"),
                "Hello world!  time in sk:   cas");
    }

    @Test
    public void testRemoveNonAlphaEmptyResult() throws Exception {
        assertEquals(TextUtils.removeNonAlpha("123+#$"), "");
    }

    @Test
     public void testRemoveNonAlphaTrim() throws Exception {
        assertEquals(TextUtils.removeNonAlpha("123abčde+#$"), "abčde");
    }

    @Test
    public void testRemoveNonAlphaWhiteSpaces() throws Exception {
        assertEquals(TextUtils.removeNonAlpha("hello!world. hi$$ "), "hello world hi");
    }

}