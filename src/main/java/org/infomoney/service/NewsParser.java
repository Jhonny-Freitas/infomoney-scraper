package org.infomoney.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.infomoney.model.News;

import java.io.IOException;

public class NewsParser {

    private final ScrapingService scrapingService;

    public NewsParser() {
        this.scrapingService = new ScrapingService();
    }

    public void parseNews(News news) throws IOException {
        Document newsPage = scrapingService.fetchPage(news.getUrl());

        String subtitle = extractSubtitle(newsPage);
        String author = extractAuthor(newsPage);
        String publicationDate = extractPublicationDate(newsPage);
        String content = extractContent(newsPage);

        news.setSubtitle(subtitle);
        news.setAuthor(author);
        news.setPublicationDate(publicationDate);
        news.setContent(content);
    }

    private String extractSubtitle(Document document) {
        String subtitle = document.select("div.text-lg.md\\:text-xl.font-medium.tracking-tight.text-wl-neutral-600").text();
        return subtitle.isEmpty() ? "Not Available" : subtitle;
    }

    private String extractAuthor(Document document) {
        Element authorElement = document.select("a.text-base.md\\:text-lg.font-semibold.tracking-tight.text-wl-action-link").first();
        return authorElement != null ? authorElement.text() : "Not Available";
    }

    private String extractPublicationDate(Document document) {
        Element dateElement = document.select("time[datetime]").first();
        return dateElement != null ? dateElement.text() : "Not Available";
    }

    private String extractContent(Document document) {
        StringBuilder content = new StringBuilder();
        Elements elements = document.select("article > p, article > h2");

        for (Element element : elements) {
            if (shouldIncludeElement(element)) {
                content.append(element.text()).append("\n");
            }
        }

        return content.toString();
    }

    private boolean shouldIncludeElement(Element element) {
        if (element.tagName().equals("p") && element.select("a[href]").size() > 0) {
            String link = element.select("a[href]").attr("href");
            return !link.startsWith("https://www.infomoney.com.br/");
        }
        return true;
    }
}
