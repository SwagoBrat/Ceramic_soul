package org.example.testspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test-db")
public class DataForm {
    private String name;
    private String email;
    private String question;
    public DataForm() {

    }
    public DataForm(String name, String email, String question) {
        super();
        this.name = name;
        this.email = email;
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
