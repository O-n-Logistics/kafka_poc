package on.ssgdeal.producer;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Orders, Long> {

    void deleteByProductName(String productName);

    boolean existsByProductName(String productName);
}
