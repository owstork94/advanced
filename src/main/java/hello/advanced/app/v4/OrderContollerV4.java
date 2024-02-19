package hello.advanced.app.v4;

import hello.advanced.template.AbstractTemplate;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderContollerV4 {

    private final OrderServiceV4 orderServiceV4;
    private final LogTrace trace;


    @GetMapping("v4/request")
    public String request(String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderServiceV4.orderItem(itemId);
                return "ok";
            }
        };
    return template.excute("OrderController.request()");
    }
}
