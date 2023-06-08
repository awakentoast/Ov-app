package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Data {
    protected final Map<String, Location> locationMap = new TreeMap<>();
    protected final Map<String, Route> routeMap = new TreeMap<>();
    
    public String[] getLocationNames() {
        String[] names = new String[locationMap.size()];
        int index = 0;
        for (String locationName : locationMap.keySet()) {
            names[index++] = locationName;
        }
        return names;
    }

    public Location[] getLocations() {
        Location[] locations = new Location[locationMap.size()];
        int index = 0;
        for (Location location : locationMap.values()) {
            locations[index++] = location;
        }
        return locations;
    }
    
    
    public List<Trip> getValidRoutes(Location start, Location destination, LocalTime departure) {
        List<Trip> trips = new ArrayList<>();
        
        for (var e : routeMap.entrySet()) {
            String key = e.getKey();
            
            String patternString = start.getName() + ".*?" + destination.getName();
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(key);

            
            // Controleren of er een overeenkomst is gevonden
            while (matcher.find()) {
                var route = e.getValue();
                LocalTime departureTimeTrip = LocalTime.parse(key.split("\\|")[1]);
                if (departureTimeTrip.isAfter(departure)) {
                    double distance = route.getDistance(start, destination);
                    distance = Math.round(distance * 100.0) / 100.0;
                    int duration = route.getTripTime(start, destination);
                    trips.add(new Trip(departureTimeTrip, start, destination, distance, duration, getTransportType()));
                }
            }
        }
        return trips;
    }
    
    public abstract Location findLocation(String locationName);
    
    public abstract String getTransportType();
}





