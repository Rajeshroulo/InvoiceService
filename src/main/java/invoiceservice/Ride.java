package invoiceservice;

public class Ride {
    public  int time;
    public double distance;
    public InvoiceService.ServiceType serviceType;

    public Ride(InvoiceService.ServiceType serviceType,double distance, int time){
        this.serviceType = serviceType;
        this.distance=distance;
        this.time=time;
    }

}
