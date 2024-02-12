package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
        private final OrderRepositoryV0 orderRepositoryV0;
        public void orderItem(String itemId) {
            System.out.println("OrderService.orderItem");
            orderRepositoryV0.save(itemId);
        }
}
