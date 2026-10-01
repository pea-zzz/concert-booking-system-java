package ConcertBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concert 
{
    private String name;
    private String date;
    private String time;
    private String venue;
    private List<String> seatAreas;
    private List<Integer> seatPrices;
    private Map<String, Integer> seatsAvailable;


    public String getName() 
    {
        return name;
    }

    public String getDate() 
    {
        return date;
    }

    public String getTime() 
    {
        return time;
    }

    public String getVenue() 
    {
        return venue;
    }

    public List<String> getSeatAreas() 
    {
        return seatAreas;
    }

    public List<Integer> getSeatPrices() 
    {
        return seatPrices;
    }
    
    public Map<String, Integer> getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(Map<String, Integer> seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void addSeat(String area, int price, int capacity) 
    {
        seatAreas.add(area);
        seatPrices.add(price);
        seatsAvailable.put(area, capacity);
    }
    
    public Concert(String name, String date, String time, String venue) 
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.seatAreas = new ArrayList<>();
        this.seatPrices = new ArrayList<>();
        this.seatsAvailable = new HashMap<>();
    }
    
    public boolean isSeatingAreaAvailable(String selectedArea) {
        return seatsAvailable.getOrDefault(selectedArea, 0) > 0;
    }
}