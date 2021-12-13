package com.geekbrains.part2.lesson6;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.part2.lesson6.enums.Periods;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherProvider {
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();
    private String minTemp;
    private String maxTemp;
    private String date;
    private int avgTemp;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather(Periods period)  throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();
        String cityKey = detectCityKey(selectedCity);
        if (period.equals(Periods.NOW)) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }

        if (period.equals(Periods.FIVE_DAYS)) {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(BASE_HOST)
                        .addPathSegment(FORECAST_ENDPOINT)
                        .addPathSegment(API_VERSION)
                        .addPathSegment("daily")
                        .addPathSegment("5day")
                        .addPathSegment(cityKey)
                        .addQueryParameter("apikey", API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .addHeader("accept", "application/json")
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                getAvarageWeatherFor5days(response.body().string(), selectedCity);

        }
    }

//    private void readResponse(String response) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode forecast = objectMapper
//                .readTree(response)
//                .at("/DailyForecasts/");
//        System.out.println(forecast);
//
//        //List<WeatherResponse> dayList = objectMapper.readValue(forecast.toString(), new TypeReference<List<WeatherResponse>>() {});
//        //System.out.println(dayList);
//    }

    private void getAvarageWeatherFor5days(String response, String selectedCity) throws JsonProcessingException {
        for (int i = 0; i < 5; i++) {
            date = objectMapper.readTree(response)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Date")
                    .asText();

            minTemp = objectMapper.readTree(response)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Temperature/Minimum/Value")
                    .asText();

            maxTemp = objectMapper.readTree(response)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Temperature/Maximum/Value")
                    .asText();

            avgTemp = (int) ((((Float.parseFloat(minTemp) + Float.parseFloat(maxTemp)) / 2) - 32) / 1.8);
            System.out.println(date.replaceAll("T.*", "") +
                    " Средняя температура в " + selectedCity + " равна " + avgTemp + " °C");
        }
    }

    private String detectCityKey(String selectedCity) throws IOException {
        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);


        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}

