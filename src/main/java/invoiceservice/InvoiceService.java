package invoiceservice;

public class InvoiceService {
    private   int COST_PER_TIME;
    private   double MINIMUM_COST_PER_KILOMETER ;
    private   double MINIMUM_FARE;
    private final RideRepository rideRepository;

    enum ServiceType {NORMAL,PREMIUM}

    public InvoiceService() {
        this.rideRepository=new RideRepository();
    }

    public double calculateFare(ServiceType serviceType,double distance, int time) {
        if(serviceType.equals(ServiceType.PREMIUM)) {
            MINIMUM_COST_PER_KILOMETER = 15;
            COST_PER_TIME = 2;
            MINIMUM_FARE = 20;
        } else {
            MINIMUM_COST_PER_KILOMETER = 10;
            COST_PER_TIME = 1;
            MINIMUM_FARE = 5;
        }
        double totalFare = distance*MINIMUM_COST_PER_KILOMETER + time*COST_PER_TIME;
        return Math.max(totalFare,MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides){
        double totalFare =0;
        for (Ride ride:rides){
            totalFare += this.calculateFare(ride.serviceType,ride.distance,ride.time);
        }
      return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides){
        rideRepository.addRide(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId){
        return this.calculateFare(rideRepository.getRides(userId));
    }

}
