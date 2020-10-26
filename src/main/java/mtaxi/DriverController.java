package mtaxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class DriverController {
  @Autowired
  DriverRepository driverRepository;

  @RequestMapping(method= RequestMethod.POST, path="/postDrivers")
  public void postOrder(@RequestBody Driver driver) {

   Driver oneDriver = new Driver();

   System.out.println("@DriverController postOrder-driver.getOrderId:" + driver.getOrderId());
   oneDriver.setDriverId(driver.getDriverId());
   oneDriver.setStatus(driver.getStatus());
   oneDriver.setLocation(driver.getLocation());
   oneDriver.setOrderId(driver.getOrderId());

   driverRepository.save(oneDriver);

  }

  @RequestMapping(method=RequestMethod.POST, path="/drivers/check")
  //public void checkOrder(@RequestParam(value="orderId", required=false, defaultValue="0") Long orderId) {
  public void checkOrder(@RequestBody Driver data) {

   System.out.println("@DriverController checkOrder-data.getOrderId:" + data.getOrderId());
   //Driver pickDriver = driverRepository.findByOrderId(data.getOrderId());
   //driver db에 정보를 넣는다.
   Driver pickDriver = new Driver();
   pickDriver.setDriverId(data.getDriverId());
   pickDriver.setLocation(data.getLocation());
   pickDriver.setOrderId(data.getOrderId());
   pickDriver.setStatus(data.getStatus());
   driverRepository.save(pickDriver);
   int random1 = (int)(Math.random() * 10);
   System.out.println("######################################################################## ");
   System.out.println("####### pickDriver.getOrderId: " + pickDriver.getOrderId() + " :: random1 : "+random1);
   System.out.println("####### pickDriver.getStatus: " + pickDriver.getStatus() + "##########");
   System.out.println("######################################################################## ");

   //agree를 좀 더 자주 호출되게 하기 위해 조절
   pickDriver.setStatus("Agreed");
//   if (random1 > 1) {
//    pickDriver.setStatus("Agreed");
//   } else {
//    pickDriver.setStatus("Declined");
//   }
   driverRepository.save(pickDriver);
   System.out.println("End of DriverController checkOrder");

  }

 }
