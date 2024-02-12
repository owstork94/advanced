package hello.advanced.app.v2;

import hello.advanced.trace.HelloTraceV2.HelloTraceV2;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderContollerV2 {

    private final OrderServiceV2 orderServiceV2;
    private final HelloTraceV2 tracev2;


    @GetMapping("v2/request")
    public String request(String itemId) {
//        System.out.println("OrderContollerv2.request");

        TraceStatus status = null;
        try {
            status = tracev2.begin("OrderContollerv2.request");
            orderServiceV2.orderItem(itemId,status.getTraceId());
            tracev2.end(status);
        } catch (Exception e) {
            tracev2.exception(status, e);
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
