package hello.advanced.app.v1;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
        private final OrderRepositoryV1 orderRepositoryV1;
        private final HelloTraceV1 traceV1;
        public void orderItem(String itemId) {
            TraceStatus status = null;
            try {
                status = traceV1.begin("OrderService.orderItem");
                orderRepositoryV1.save(itemId);
                traceV1.end(status);
            } catch (Exception e) {
                traceV1.exception(status, e);
                throw e;
            }
        }
}
