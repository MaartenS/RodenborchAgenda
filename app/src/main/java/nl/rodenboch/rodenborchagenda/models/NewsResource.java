package nl.rodenboch.rodenborchagenda.models;

import java.io.Serializable;

public class NewsResource implements Serializable {

    private String title;

    private String author;

    private String published;

    private String summary;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublished() {
        return published;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return title;
    }
}
