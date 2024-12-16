package org.infomoney.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

public class InfoMoneyApiClient {

    private final ScrapingService scrapingService;

    public InfoMoneyApiClient() {
        this.scrapingService = new ScrapingService();
    }

    public JsonArray fetchNewsData(int postId, String categories, String tags) throws IOException {
        String payload = buildPayload(postId, categories, tags);
        String responseBody = scrapingService.fetchJsonFromApi(
                "https://www.infomoney.com.br/wp-json/infomoney/v1/cards",
                payload,
                List.of("Accept", "Content-Type", "X-Requested-With", "User-Agent", "Referer", "Origin")
        );

        if (responseBody == null || responseBody.isEmpty()) {
            throw new IOException("Received empty response from API");
        }

        return JsonParser.parseString(responseBody).getAsJsonArray();
    }

    private String buildPayload(int postId, String categories, String tags) {
        return String.format("{\"post_id\": %d, \"categories\": %s, \"tags\": %s}", postId, categories, tags);
    }
}
