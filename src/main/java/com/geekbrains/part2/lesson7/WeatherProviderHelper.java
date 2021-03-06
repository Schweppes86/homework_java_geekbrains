package com.geekbrains.part2.lesson7;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.part2.lesson7.entity.DailyForecast;
import com.geekbrains.part2.lesson7.entity.DailyForecastForDB;
import com.geekbrains.part2.lesson7.enums.Periods;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.sqlite.core.DB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherProviderHelper implements WeatherProvider {
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = com.geekbrains.part2.lesson7.ApplicationGlobalState.getInstance().getApiKey();
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather(Periods period) throws IOException {
        String selectedCity = com.geekbrains.part2.lesson7.ApplicationGlobalState.getInstance().getSelectedCity();
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
            String responseString = response.body().string();

            String forecastTemperature =  objectMapper.readTree(responseString).get(0).at("/Temperature")
                    .at("/Metric").at("/Value").asText();
            System.out.println("?? ???????????? " + selectedCity + " ?????????????? ?????????????????????? " + forecastTemperature + " ??C.");
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
            String responseString = response.body().string();

            String dailyForecastsString = objectMapper.readTree(responseString).at("/DailyForecasts").toString();
            List<DailyForecast> dailyForecasts = objectMapper.readValue(dailyForecastsString, new TypeReference<List<DailyForecast>>() {});
            System.out.println(dailyForecasts);

            DailyForecastForDB dailyForecastModelForDB1 = new DailyForecastForDB();
            DailyForecastForDB dailyForecastModelForDB2 = new DailyForecastForDB();
            DailyForecastForDB dailyForecastModelForDB3 = new DailyForecastForDB();
            DailyForecastForDB dailyForecastModelForDB4 = new DailyForecastForDB();
            DailyForecastForDB dailyForecastModelForDB5 = new DailyForecastForDB();

            ArrayList<DailyForecastForDB> dailyForecastModelForDBList = new ArrayList<>();
            dailyForecastModelForDBList.add(dailyForecastModelForDB1);
            dailyForecastModelForDBList.add(dailyForecastModelForDB2);
            dailyForecastModelForDBList.add(dailyForecastModelForDB3);
            dailyForecastModelForDBList.add(dailyForecastModelForDB4);
            dailyForecastModelForDBList.add(dailyForecastModelForDB5);

            for (int i = 0; i < 5; i++){
                dailyForecastModelForDBList.get(i)
                        .setCity(selectedCity)
                        .setLocalDate(dailyForecasts.get(i).getDate())
                        .setTempMin(dailyForecasts.get(i).getTemperatureObject().getMinimum().getValue())
                        .setTempMax(dailyForecasts.get(i).getTemperatureObject().getMaximum().getValue())
                        .setTextDay(dailyForecasts.get(i).getDayObject().getIconPhrase())
                        .setTextNight(dailyForecasts.get(i).getNightObject().getIconPhrase());
            }

            DBController dBRepository = new DBController();
            try {
                for (int i = 0; i < 5; i++){
                    dBRepository.saveWeatherData(dailyForecastModelForDBList.get(i));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            throw new IOException("???????????????????? ???????????????? ???????????????????? ?? ????????????. " +
                    "?????? ???????????? ?????????????? = " + response.code() + " ???????? ???????????? = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("?????????????????? ?????????? ???????????? " + selectedCity);


        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("???????????? ?????????? " + cityName + " ?? ???????????? " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}