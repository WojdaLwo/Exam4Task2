package task2.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import task2.exceptions.WrongCurrencyException;

import java.io.IOException;

public class Service {

    public double exchange(String currencyFrom, String currencyTo, double amount) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=" + currencyTo + "&from=" + currencyFrom + "&amount=10")
                .addHeader("apikey", "KT1gj4FobGPu3QSjyAVT4YpxP71WcgUZ")
                .build();
        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body().string());
        if (jsonNode.get("result") == null) {
            throw new WrongCurrencyException();
        }
        return jsonNode.get("result").asDouble();
    }

}
