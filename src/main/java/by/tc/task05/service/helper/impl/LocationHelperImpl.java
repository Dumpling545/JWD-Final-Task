package by.tc.task05.service.helper.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import by.tc.task05.entity.Location;
import by.tc.task05.service.exception.LocationAPIException;
import by.tc.task05.service.helper.LocationHelper;

public class LocationHelperImpl implements LocationHelper {

    private final static String LOCATION_IQ_API_URL =
            "https://eu1.locationiq.com/v1/search.php";
    private final static String LOCATION_IQ_ACCESS_TOKEN =
            "pk.c46d63a472dc505f81781cfbd989a154";

    private final static String TOKEN_PARAM_KEY = "key";
    private final static String QUERY_PARAM_KEY = "q";
    private final static String FORMAT_PARAM_KEY = "format";
    private final static String FORMAT = "json";
    private final static String URL_TEMPLATE = "%1$s?%2$s=%3$s&%4$s=%5$s&%6$s=%7$s";

    private final static String LATITUDE_KEY = "lat";
    private final static String LONGTITUDE_KEY = "lon";

    @Override
    public Location locate(String searchQuery) throws LocationAPIException {
        Location location = null;
        try {
            String url = String.format(URL_TEMPLATE, LOCATION_IQ_API_URL, TOKEN_PARAM_KEY,
                    LOCATION_IQ_ACCESS_TOKEN, QUERY_PARAM_KEY, searchQuery,
                    FORMAT_PARAM_KEY, FORMAT);
            HttpRequest request =
                    HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build()
                    .send(request, BodyHandlers.ofString());
            JSONArray responseJSON = new JSONArray(response.body());
            JSONObject firstResult = responseJSON.getJSONObject(0);
            location = new Location(firstResult.getDouble(LATITUDE_KEY),
                    firstResult.getDouble(LONGTITUDE_KEY));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new LocationAPIException(e);
        }
        return location;
    }

}
