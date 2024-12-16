package org.infomoney;

import org.infomoney.model.News;
import org.infomoney.service.NewsFetcher;
import org.infomoney.service.NewsParser;
import org.infomoney.service.ScrapingService;
import org.infomoney.service.InfoMoneyApiClient;
import org.infomoney.service.NewsPrinter;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.List;

public class InfoMoneyScraper {

    public static void main(String[] args) {
        String url = "https://www.infomoney.com.br/mercados/";

        ScrapingService scrapingService = new ScrapingService();
        InfoMoneyApiClient apiClient = new InfoMoneyApiClient();
        NewsParser newsParser = new NewsParser();
        NewsFetcher newsFetcher = new NewsFetcher(scrapingService, apiClient);

        try {
            List<News> newsList = newsFetcher.fetchFirstPageNews(url);

            Document doc = scrapingService.fetchPage(url);
            newsList.addAll(newsFetcher.fetchAdditionalNews(doc));

            for (News news : newsList) {
                newsParser.parseNews(news);
                NewsPrinter.printNews(news);
                System.out.println("\n"); // Just to improve visualization
            }

        } catch (IOException e) {
            System.err.println("An error occurred during the scraping process: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
