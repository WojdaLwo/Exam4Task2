package task2.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import task2.exceptions.*;

import java.io.IOException;

public class Service {

    public String getResponse(String currencyFrom, String currencyTo, double amount) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=" + currencyTo + "&from=" + currencyFrom + "&amount="+amount)
                .addHeader("apikey", "KT1gj4FobGPu3QSjyAVT4YpxP71WcgUZ")
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public double exchange(String currencyFrom, String currencyTo, double amount) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(getResponse(currencyFrom, currencyTo, amount));
        if(jsonNode.has("error")) {
            String str = jsonNode.get("error").get("code").toString();
            if (str.contains("invalid_from_currency")||str.contains("invalid_to_currency")) {
                throw new WrongCurrencyException();
            } else if(str.contains("invalid_conversion_amount")){
                throw new WrongAmountException();
            }
        }
        return jsonNode.get("result").asDouble();
    }

    public String test(){
        return "dupa";
    }



}
