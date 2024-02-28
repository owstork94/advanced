package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallable;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class OrderContollerV5 {

    private final OrderServiceV5 orderServiceV5;
//    private final LogTrace trace;
    private final TraceTemplate template;

    public OrderContollerV5(OrderServiceV5 orderServiceV5, LogTrace logTrace) {
        this.orderServiceV5 = orderServiceV5;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("v5/request")
    public String request(String itemId){

//        TraceTemplate template = new TraceTemplate(trace);
       return template.excute("OrderControllerV5.ctrl", new TraceCallable<>() {
            @Override
            public String call() {
                orderServiceV5.orderItem(itemId);
                return "ok";
            }
        });
    }
}
