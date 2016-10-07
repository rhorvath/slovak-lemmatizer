package sk.lemmatizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.lemmatizer.model.Word;
import sk.lemmatizer.service.LemmatizationService;

import java.util.List;

/**
 * Lemmatizer rest service endpoints.
 */
@RestController
public class LemmatizationController {

    @Autowired
    private LemmatizationService lemmatizationService;

    @RequestMapping(value = "/fast", method = RequestMethod.GET)
    public String fastLemmatizeFromUrl(
            @RequestParam String text,
            @RequestParam(defaultValue = "true") boolean useDatabase,
            @RequestParam(defaultValue = "true") boolean useTvaroslovnik,
            @RequestParam(defaultValue = "true") boolean inputDiacritics,
            @RequestParam(defaultValue = "true") boolean outputDiacritics,
            @RequestParam(defaultValue = "true") boolean keepStructure
    ) {
        return lemmatizationService.fastLemmatize(text, useDatabase, useTvaroslovnik, inputDiacritics, outputDiacritics, keepStructure);
    }

    @RequestMapping(value = "/fast", method = RequestMethod.POST)
    public String fastLemmatizeFromBody(
            @RequestBody String text,
            @RequestParam(defaultValue = "true") boolean useDatabase,
            @RequestParam(defaultValue = "true") boolean useTvaroslovnik,
            @RequestParam(defaultValue = "true") boolean inputDiacritics,
            @RequestParam(defaultValue = "true") boolean outputDiacritics,
            @RequestParam(defaultValue = "true") boolean keepStructure
    ) {
        return lemmatizationService.fastLemmatize(text, useDatabase, useTvaroslovnik, inputDiacritics, outputDiacritics, keepStructure);
    }

    @RequestMapping(value = "/full", method = RequestMethod.GET)
    public List<List<Word>> fullLemmatizeFromUrl(
            @RequestParam String text,
            @RequestParam(defaultValue = "true") boolean useDatabase,
            @RequestParam(defaultValue = "true") boolean useTvaroslovnik,
            @RequestParam(defaultValue = "true") boolean inputDiacritics
    ) {
        return lemmatizationService.fullLemmatize(text, useDatabase, useTvaroslovnik, inputDiacritics);
    }

    @RequestMapping(value = "/full", method = RequestMethod.POST)
    public List<List<Word>> fullLemmatizeFromBody(
            @RequestBody String text,
            @RequestParam(defaultValue = "true") boolean useDatabase,
            @RequestParam(defaultValue = "true") boolean useTvaroslovnik,
            @RequestParam(defaultValue = "true") boolean inputDiacritics
    ) {
        return lemmatizationService.fullLemmatize(text, useDatabase, useTvaroslovnik, inputDiacritics);
    }

}
