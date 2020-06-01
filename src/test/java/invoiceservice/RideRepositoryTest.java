package invoiceservice;

import invoiceservice.RideRepository;
import org.junit.Test;
import org.junit.Assert;

public class RideRepositoryTest {
    RideRepository rideRepository = new RideRepository();

    @Test
    public void givenUserId_WhenFound_ShouldReturnLength(){
    Ride[] rides = {new Ride(InvoiceService.ServiceType.PREMIUM,2.0,5),
            new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
    };

    rideRepository.addRide("abc@gmail.com",rides);
    Assert.assertEquals(rideRepository.getRides("abc@gmail.com").length,rides.length);
    }

}


