package sk.lemmatizer.integration;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sk.lemmatizer.config.Application;
import sk.lemmatizer.controller.LemmatizationController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
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

}
