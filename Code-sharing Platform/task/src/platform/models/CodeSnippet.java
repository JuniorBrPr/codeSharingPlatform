package platform.models;

import java.time.LocalDate;

public class CodeSnippet {
    private String code;
    private LocalDate date;

    public CodeSnippet() {
    }

    public CodeSnippet(String code, LocalDate date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
