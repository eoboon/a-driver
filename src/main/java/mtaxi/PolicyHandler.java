package mtaxi;

import mtaxi.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    DriverRepository driverRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelOrderRequested_ReceiveCancelOrder(@Payload CancelOrderRequested cancelOrderRequested){

        if(cancelOrderRequested.isMe()){
            System.out.println("##### listener ReceiveCancelOrder : " + cancelOrderRequested.toJson());

            Driver driver = new Driver();
            driverRepository.findById(Long.valueOf(cancelOrderRequested.getOrderId())).ifPresent((Driver)->{
                Driver.setDriverId(cancelOrderRequested.getDriverId());
                Driver.setStatus("Canceled");
                driverRepository.save(Driver);
            });
        }
    }

}
