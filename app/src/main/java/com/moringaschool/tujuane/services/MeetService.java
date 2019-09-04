package com.moringaschool.tujuane;


import com.moringaschool.tujuane.models.Friend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeetService {
    public static void findFriends(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEET_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MEET_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.MEET_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Friend> processResults(Response response) {
        ArrayList<Friend> friends = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject meetJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = meetJSON.getJSONArray("businesses");
            if (response.isSuccessful()) {
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject restaurantJSON = businessesJSON.getJSONObject(i);
                    String name = friendJSON.getString("name");
                    String phone = friendJSON.optString("display_phone", "Phone not available");
                    String website = friendJSON.getString("url");
                    double rating = friendJSON.getDouble("rating");
                    String imageUrl = friendJSON.getString("image_url");
                    double latitude = friendJSON.getJSONObject("coordinates").getDouble("latitude");
                    double longitude = friendJSON.getJSONObject("coordinates").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = friendJSON.getJSONObject("location").getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }
                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = restaurantJSON.getJSONArray("categories");
                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        categories.add(categoriesJSON.getJSONObject(y).getString("title"));
                    }
                    Friend friend = new Friend(name, phone, website, rating,
                            imageUrl, address, latitude, longitude, categories);
                    friends.add(friend);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return friends;

    }
}


