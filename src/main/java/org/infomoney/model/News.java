package org.infomoney.model;

public class News {
    private String url;
    private String title;
    private String subtitle;
    private String author;
    private String publicationDate;
    private String content;

    public News(String url, String title) {
        this.url = url;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("URL: %s\nTitle: %s\nSubtitle: %s\nAuthor: %s\nPublication Date: %s\nContent: %s",
                url,
                title,
                getOrDefault(subtitle),
                getOrDefault(author),
                publicationDate,
                content.replaceAll("\\n", " ")
        );
    }

    private String getOrDefault(String value) {
        return value != null ? value : "Not Available";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
