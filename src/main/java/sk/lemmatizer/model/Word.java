package sk.lemmatizer.model;

import javax.persistence.*;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lemma")
    private String lemma;

    @Column(name = "form")
    private String form;

    @Column(name = "form_ai")
    private String formAi;

    @Column(name = "tag")
    private String tag;

    public Word() {
    }

    public Word(String lemma, String form, String formAi, String tag) {
        this.lemma = lemma;
        this.form = form;
        this.formAi = formAi;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getFormAi() {
        return formAi;
    }

    public void setFormAi(String formAi) {
        this.formAi = formAi;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
