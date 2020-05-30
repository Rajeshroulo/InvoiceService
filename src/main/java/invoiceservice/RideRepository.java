package invoiceservice;

import java.util.*;

public class RideRepository {
    public Map<String, ArrayList<Ride>> userRides = null;

    public RideRepository() { this.userRides = new HashMap<>(); }

    public void addRide(String userId, Ride[] rides){

        ArrayList<Ride> rideList = this.userRides.get(userId);
        if(rideList == null)
            this.userRides.put(userId,new ArrayList<>(Arrays.asList(rides)));
        else
            this.userRides.get(userId).addAll(new ArrayList<>(Arrays.asList(rides)));
    }

    public Ride[] getRides(String userId){
        return this.userRides.get(userId).toArray(new Ride[0]);
    }

}
