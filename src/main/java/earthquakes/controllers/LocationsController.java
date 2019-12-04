package earthquakes.controllers;

import earthquakes.services.LocationQueryService;
import earthquakes.searches.LocSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import earthquakes.osm.Place;
import earthquakes.entities.Location;
import earthquakes.repositories.LocationRepository;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

@Controller
public class LocationsController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public String index(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String placeId = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("id").toString();
        Iterable<Location> locations = locationRepository.findByPlaceId(placeId);
        model.addAttribute("locations", locations);
        return "locations/index";
    }

    @PostMapping("/locations/add")
    public String add(Location location, Model model) {
        locationRepository.save(location);
        model.addAttribute("locations", locationRepository.findAll());
        return "locations/index";
    }

    @GetMapping("/locations/search")
    public String getLocationsSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
        return "locations/search";
    }

    @GetMapping("/locations/results")
    public String getLocationsResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {

        LocationQueryService l = new LocationQueryService();

        model.addAttribute("locSearch", locSearch);
        String json = l.getJSON(locSearch.getLocation());
        model.addAttribute("json", json);
        List<Place> places = Place.listFromJson(json);
        model.addAttribute("places", places);
        return "locations/results";
    }
}
