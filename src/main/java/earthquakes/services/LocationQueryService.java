package earthquakes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationQueryService {

    private Logger logger = LoggerFactory.getLogger(LocationQueryService.class);

    public String getJSON(String location) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://nominatim.openstreetmap.org/search/";
        String params = String.format("?%s?format=json", location);

        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from LocationQueryService.getJSON: " + retVal);
        return retVal;
    }

}