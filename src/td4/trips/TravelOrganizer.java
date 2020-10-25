package td4.trips;

import td4.Service;
import td4.core.Description;
import td4.core.Product;

import java.util.ArrayList;
import java.util.List;

public class TravelOrganizer {
    List<Service> services;

    public TravelOrganizer(List<Service> services) {
        this.services = services;
    }

    public TravelOrganizer() {
        services = new ArrayList<>();
    }

    public void addService(Service s){
        if(!services.contains(s))
            services.add(s);
    }

    public Trip createATrip(Description desc){
        Trip t = new Trip(desc);
        for(Service s : services){
            Product p=null;
            p = s.find(desc);
            if(p != null)
                t.add(p);
        }
        return t;
    }
}
