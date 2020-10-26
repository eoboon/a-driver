package mtaxi;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DriverRepository extends PagingAndSortingRepository<Driver, Long>{

    Driver findByOrderId(Long orderId);
}