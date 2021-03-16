package by.tc.task05.service.helper.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.JSONArray;
import org.json.JSONObject;
import by.tc.task05.entity.Location;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.helper.LocationHelper;

public class LocationHelperImpl implements LocationHelper {

    private final static String LOCATION_IQ_API_URL =
            "https://eu1.locationiq.com/v1/search.php";
    private final static String TOKEN_PARAM_KEY = "key";
    private final static String LOCATION_IQ_ACCESS_TOKEN =
            "pk.c46d63a472dc505f81781cfbd989a154";
    private final static String QUERY_PARAM_KEY = "q";
    private final static String FORMAT_PARAM_KEY = "format";
    private final static String FORMAT = "json";

    @Override
    public Location locate(String searchQuery) throws ServiceException {
        Location location = null;
        try {
            HttpRequest request =
                    HttpRequest.newBuilder().uri(new URI(LOCATION_IQ_API_URL))
                            .headers(TOKEN_PARAM_KEY, LOCATION_IQ_ACCESS_TOKEN,
                                    QUERY_PARAM_KEY, searchQuery,
                                    FORMAT_PARAM_KEY, FORMAT)
                            .GET().build();
            HttpResponse<String> response = HttpClient
                            .newBuilder()
                            .build()
                            .send(request, BodyHandlers.ofString());
            JSONArray responseJSON = new JSONArray(response.body());
            JSONObject firstResult = responseJSON.getJSONObject(0);
            location = new Location(firstResult.getDouble("lat"), firstResult.getDouble("lon"));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new ServiceException("Location API Exception", e);
        }
        return location;
    }

}
