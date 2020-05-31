package invoiceservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class InvoiceServiceTest {
    InvoiceService invoiceService=null;


    @Before
    public void setUp() throws Exception {
        invoiceService= new InvoiceService();
    }
    @Test
    public void givenNormalRideWithdistanceAndTime_ShouldReturnTotalFare(){
        double distance =2.0;
        int time =5;
    double fare= invoiceService.calculateFare(InvoiceService.ServiceType.NORMAL,distance, time);
    Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenNormalRideWithLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.NORMAL,distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenNormalMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(InvoiceService.ServiceType.NORMAL,2.0,5),
                        new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)};
        InvoiceSummary summary=invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30);
         Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndNormalRides_ShouldReturnInvoiceSummary(){
        String userId="a@b.com";
        Ride[ ] rides={
                new Ride(InvoiceService.ServiceType.NORMAL,2.0,5),
                new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndMultipleNormalRides_ShouldReturnInvoiceSummary(){
        String userId="a@b.com";
        Ride[ ] rides={
                new Ride(InvoiceService.ServiceType.NORMAL,2.0,5),
                new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
        };
        invoiceService.addRides(userId, rides);
        Ride[ ] rides1={
                new Ride(InvoiceService.ServiceType.NORMAL,2.0,5),
                new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
        };
        invoiceService.addRides(userId, rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4,60.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumRideWithDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.PREMIUM,distance,time);
        Assert.assertEquals(40,fare,0.0);
    }

    @Test
    public void givenPremiumRideWithLessDistanceAndTime_shouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.PREMIUM,distance,time);
        Assert.assertEquals(20,fare,0.0);
    }

    @Test
    public void givenMultiplePremiumRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(InvoiceService.ServiceType.PREMIUM,2.0, 5),
                new Ride(InvoiceService.ServiceType.PREMIUM,0.1,1)};
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,60.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(InvoiceService.ServiceType.PREMIUM,2.0,5),
                new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)};
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,45.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndMultipleRides_shouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(InvoiceService.ServiceType.NORMAL,2.0,5),
                new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)};
        invoiceService.addRides(userId,rides);
        Ride[] rides1 = {new Ride(InvoiceService.ServiceType.NORMAL,2.0,5),
                new Ride(InvoiceService.ServiceType.PREMIUM,0.1,1)};
        invoiceService.addRides(userId,rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4,75.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

}