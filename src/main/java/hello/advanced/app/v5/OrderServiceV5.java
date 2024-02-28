package hello.advanced.app.v5;

import hello.advanced.template.AbstractTemplate;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrderServiceV5 {
        private final OrderRepositoryV5 orderRepositoryV5;
        private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepositoryV5, LogTrace logTrace) {
        this.orderRepositoryV5 = orderRepositoryV5;
        this.template = new TraceTemplate(logTrace);
    }

    public void orderItem(String itemId) {
        template.excute("OrderServiceV5.orderItem()", () -> {
            orderRepositoryV5.save(itemId);
            return null;
        });
    }
}
