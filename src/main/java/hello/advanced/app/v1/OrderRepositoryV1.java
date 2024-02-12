package hello.advanced.app.v1;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
//@RequiredArgsConstructor
public class OrderRepositoryV1 {
    public OrderRepositoryV1(HelloTraceV1 traceV1) {
        this.traceV1 = traceV1;
    }

    private final HelloTraceV1 traceV1;

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = traceV1.begin("OrderRepositoryV1.save");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            traceV1.end(status);
        } catch (IllegalStateException e) {
            traceV1.exception(status, e);
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
