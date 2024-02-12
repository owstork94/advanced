package hello.advanced.app.v2;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.HelloTraceV2.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
        private final OrderRepositoryV2 orderRepositoryV2;
        private final HelloTraceV2 traceV2;
        public void orderItem(String itemId, TraceId traceId) {
            TraceStatus status = null;
            try {
                status = traceV2.beginSync(traceId, "OrderServiceV2.orderItem");
                orderRepositoryV2.save(itemId, status.getTraceId());
                traceV2.end(status);
            } catch (Exception e) {
                traceV2.exception(status, e);
                throw e;
            }
        }
}
