package sk.lemmatizer.integration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sk.lemmatizer.controller.LemmatizationController;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("/testdb.xml")
@ActiveProfiles("dev")
public class LemmatizationServiceTests {

    @Autowired
    private LemmatizationController lemmatizationController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(lemmatizationController).build();
    }

    @Test
    public void getLemmaFastEmptyText() throws Exception {
        mockMvc.perform(get("/fast?text="))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getLemmaFastExistingWord() throws Exception {
        mockMvc.perform(get("/fast?text=mamu"))
                .andExpect(status().isOk())
                .andExpect(content().string("mama"));
    }

    @Ignore  //TODO: fix encoding, mockMVC is not returning utf-8
    @Test
    public void getLemmaFastMultipleWords() throws Exception {
        mockMvc.perform(get("/fast?text=Mamu. Pekným časom!"))
                .andExpect(status().isOk())
                .andExpect(content().encoding("utf-8"))
                .andExpect(content().string("mama. pekný čas!"));
    }

    @Test
    public void getLemmaFull() throws Exception {
        mockMvc.perform(get("/full?text=Mamu. časom test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", hasSize(1)))
                .andExpect(jsonPath("$[1]", hasSize(2)))
                .andExpect(jsonPath("$[2]", hasSize(0)))
                .andExpect(jsonPath("$[0][0].lemma").value("mama"))
                .andExpect(jsonPath("$[0][0].tag").value("SSfs4"));
    }

}
