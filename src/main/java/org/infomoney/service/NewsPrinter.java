package org.infomoney.service;

import org.infomoney.model.News;

public class NewsPrinter {

    public static void printNews(News news) {
        System.out.println("URL: " + news.getUrl());
        System.out.println("Title: " + news.getTitle());
        System.out.println("Subtitle: " + (news.getSubtitle().equals("Not Available") ? "No subtitle available" : news.getSubtitle()));
        System.out.println("Author: " + news.getAuthor());
        System.out.println("Publication Date: " + news.getPublicationDate());
        System.out.println("Content: " + NewsFormatter.cleanContent(news.getContent()));
    }
}