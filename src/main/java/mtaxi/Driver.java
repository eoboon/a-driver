package mtaxi;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Driver_table")
public class Driver {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long driverId;
    private String status;
    private String location;
    private Long orderId;

    @PostUpdate
    public void onPostUpdate(){

        System.out.println("onPostUpdate status:" + this.getStatus());
        if(null != this.getStatus()&&!"".equals(this.getStatus())) {
            if (this.getStatus().equals("Agreed")) {
                System.out.println("onPostUpdate in Agreed");
                OrderAgreed orderAgreed = new OrderAgreed();

                orderAgreed.setOrderId(this.getOrderId());
                orderAgreed.setStatus("Agreed");
                orderAgreed.setDriverId(this.getDriverId());

                BeanUtils.copyProperties(this, orderAgreed);
                orderAgreed.publishAfterCommit();

            } else if (this.getStatus().equals("Declined")) {
                System.out.println("onPostUpdate in Declined");
                OrderDeclined orderDeclined = new OrderDeclined();

                orderDeclined.setOrderId(this.getOrderId());
                orderDeclined.setStatus("Declined");
                orderDeclined.setDriverId(this.getDriverId());

                BeanUtils.copyProperties(this, orderDeclined);
                orderDeclined.publishAfterCommit();
            }
        }

    }


    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }




}
