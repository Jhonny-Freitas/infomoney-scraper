package org.infomoney.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingService {

    public Document fetchPage(String url) throws IOException {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new IOException("Failed to fetch the page: " + url, e);
        }
    }

    public Elements fetchElementsBySelector(Document document, String selector) {
        return document.select(selector);
    }

    public String fetchJsonFromApi(String url, String payload, List<String> headers) throws IOException {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .headers(buildHeaders(headers))
                    .requestBody(payload)
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .execute();

            if (response.statusCode() != 200) {
                throw new IOException("API returned error: " + response.statusCode());
            }

            return response.body();
        } catch (IOException e) {
            throw new IOException("Failed to fetch JSON from API", e);
        }
    }

    private Map<String, String> buildHeaders(List<String> headers) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "application/json");
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-Requested-With", "XMLHttpRequest");
        headerMap.put("User-Agent", "Mozilla/5.0");
        headerMap.put("Referer", "https://www.infomoney.com.br/mercados/");
        headerMap.put("Origin", "https://www.infomoney.com.br");
        return headerMap;
    }
}
