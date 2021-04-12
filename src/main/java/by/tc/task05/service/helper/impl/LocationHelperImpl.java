package by.tc.task05.service.helper.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.tc.task05.service.exception.InvalidLocationException;
import by.tc.task05.service.exception.ServiceException;
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
    private final static String URL_TEMPLATE =
            "%1$s?%2$s=%3$s&%4$s=%5$s&%6$s=%7$s";

    private final static String LATITUDE_KEY = "lat";
    private final static String LONGTITUDE_KEY = "lon";
    private final static int LOCATION_NOT_FOUND_CODE = 404;
    private final static int LOCATION_API_SERVER_ERROR_CODE = 500;

    @Override
    public Location locate(String searchQuery) throws ServiceException {
        Location location = null;
        try {
            String url = String.format(URL_TEMPLATE, LOCATION_IQ_API_URL,
                    TOKEN_PARAM_KEY, LOCATION_IQ_ACCESS_TOKEN, QUERY_PARAM_KEY,
                    URLEncoder.encode(searchQuery, StandardCharsets.UTF_8),
                    FORMAT_PARAM_KEY, FORMAT);
            HttpRequest request =
                    HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build()
                    .send(request, BodyHandlers.ofString());
            if (response.statusCode() == LOCATION_NOT_FOUND_CODE) {
                throw new InvalidLocationException();
            } else if (response.statusCode() ==
                    LOCATION_API_SERVER_ERROR_CODE) {
                throw new LocationAPIException();
            }
            JSONArray responseJSON = new JSONArray(response.body());
            JSONObject firstResult = responseJSON.getJSONObject(0);
            location = new Location(firstResult.getDouble(LATITUDE_KEY),
                    firstResult.getDouble(LONGTITUDE_KEY));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new ServiceException(e);
        }
        return location;
    }

}
