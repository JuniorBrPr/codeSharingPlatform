package platform.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CodeSnippet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String code;

    @NotNull
    String date;

    private long time;

    private long views;

    @JsonIgnore
    private boolean timeRestriction;

    @JsonIgnore
    private boolean viewRestriction;

    private String UUID;

    public CodeSnippet() {
    }

    public CodeSnippet(String code, String date, long views, long time) {
        this.code = code;
        this.date = date;
        this.views = Math.max(views, 0);
        this.time = Math.max(time, 0);
        this.timeRestriction = time > 0;
        this.viewRestriction = views > 0;
        this.UUID = java.util.UUID.randomUUID().toString();
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isTimeRestriction() {
        return timeRestriction;
    }

    public boolean isViewRestriction() {
        return viewRestriction;
    }

    @JsonIgnore
    public String getUUID() {
        return UUID;
    }

}
