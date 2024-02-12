package hello.advanced.app.v1;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderContollerV1 {

    private final OrderServiceV1 orderServiceV1;
    private final HelloTraceV1 traceV1;


    @GetMapping("v1/request")
    public String request(String itemId) {
//        System.out.println("OrderContollerv1.request");

        TraceStatus status = null;
        try {
            status = traceV1.begin("OrderContollerv1.request");
            orderServiceV1.orderItem(itemId);
            traceV1.end(status);
        } catch (Exception e) {
            traceV1.exception(status, e);
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
