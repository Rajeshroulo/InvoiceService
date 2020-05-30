package invoiceservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService=null;

    @Before
    public void beforeClass(){
        invoiceService= new InvoiceService();
    }
    @Test
    public void givendistanceAndTime_ShouldReturnTotalFare(){
        double distance =2.0;
        int time =5;
    double fare= invoiceService.calculateFare(distance, time);
    Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0,5),
                        new Ride(0.1,1)};
        InvoiceSummary summary=invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30);
         Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary(){
        String userId="a@b.com";
        Ride[ ] rides={
                new Ride(2.0,5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndMultipleRides_ShouldReturnInvoiceSummary(){
        String userId="a@b.com";
        Ride[ ] rides={
                new Ride(2.0,5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId, rides);
        Ride[ ] rides1={
                new Ride(2.0,5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId, rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4,60.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

}