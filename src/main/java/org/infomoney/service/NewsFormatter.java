package org.infomoney.service;

public class NewsFormatter {

    public static String cleanContent(String content) {
        if (content != null) {
            content = content.replaceAll("<.*?>", "").replaceAll("\\n", " ").trim();
        }
        return content;
    }
}