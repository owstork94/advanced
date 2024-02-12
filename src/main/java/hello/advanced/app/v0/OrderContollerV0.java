package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderContollerV0 {

    private final OrderServiceV0 orderServiceV0;


    @GetMapping("v0/request")
    public String request(String itemId) {
        System.out.println("OrderContollerV0.request");
        orderServiceV0.orderItem(itemId);
        return "ok";
    }
}
