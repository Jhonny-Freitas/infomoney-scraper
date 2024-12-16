package org.infomoney.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.infomoney.model.News;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class NewsFetcher {

    private final ScrapingService scrapingService;
    private final InfoMoneyApiClient apiClient;

    public NewsFetcher(ScrapingService scrapingService, InfoMoneyApiClient apiClient) {
        this.scrapingService = scrapingService;
        this.apiClient = apiClient;
    }

    public List<News> fetchFirstPageNews(String url) throws IOException {
        List<News> newsList = new ArrayList<>();
        Document doc = scrapingService.fetchPage(url);

        for (Element newsDiv : doc.select("div.flex.flex-col.gap-1")) {
            Element linkElement = newsDiv.select("h2 a").first();
            if (linkElement != null) {
                String title = linkElement.text();
                String newsUrl = linkElement.attr("href");
                News news = new News(newsUrl, title);
                newsList.add(news);
            }
        }

        return newsList;
    }

    public List<News> fetchAdditionalNews(Document doc) throws IOException {
        List<News> newsList = new ArrayList<>();
        Element loadMoreSection = doc.select("section[data-ds-component=load-more-cards]").first();

        if (loadMoreSection != null) {
            String dataConfig = loadMoreSection.attr("data-config");
            JsonObject configJson = JsonParser.parseString(dataConfig).getAsJsonObject();
            int postId = configJson.get("post_id").getAsInt();
            String categories = configJson.get("categories").toString();
            String tags = configJson.get("tags").toString();

            JsonArray newsJson = apiClient.fetchNewsData(postId, categories, tags);
            // i <= 20 to return the first 20 news from the "Mercados" category, if you want them all just replace with newsJson.size()
            for (int i = 0; i <= 20; i++) {
                JsonObject newsItem = newsJson.get(i).getAsJsonObject();
                String title = newsItem.get("post_title").getAsString();
                String newsUrl = newsItem.get("post_permalink").getAsString();
                News news = new News(newsUrl, title);
                newsList.add(news);
            }
        }
        return newsList;
    }
}
