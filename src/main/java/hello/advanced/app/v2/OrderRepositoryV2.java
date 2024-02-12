package hello.advanced.app.v2;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.HelloTraceV2.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import org.springframework.stereotype.Repository;

@Repository
//@RequiredArgsConstructor
public class OrderRepositoryV2 {
    public OrderRepositoryV2(HelloTraceV2 traceV2) {
        this.traceV2 = traceV2;
    }

    private final HelloTraceV2 traceV2;

    public void save(String itemId, TraceId traceId) {
        TraceStatus status = null;
        try {
            status = traceV2.beginSync(traceId, "OrderRepositoryV2.save");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            traceV2.end(status);
        } catch (IllegalStateException e) {
            traceV2.exception(status, e);
            throw new RuntimeException(e);
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
