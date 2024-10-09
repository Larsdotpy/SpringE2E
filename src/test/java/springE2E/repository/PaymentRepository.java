package springE2E.repository;

import springE2E.model.CoverPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<CoverPhoto, Long> {
    List<CoverPhoto> findByOrderId(Long orderId);
}
