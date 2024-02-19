package hello.advanced.app.v4;

import hello.advanced.template.AbstractTemplate;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
        private final OrderRepositoryV4 orderRepositoryV4;
        private final LogTrace trace;
        public void orderItem(String itemId) {

            AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
                @Override
                protected Void call() {
                    orderRepositoryV4.save(itemId);
                    return null;
                }
            };
            template.excute("OrderService.orderItem()");
//            TraceStatus status = null;
//            try {
//                status = trace.begin("OrderServiceV2.orderItem");
//                orderRepositoryV4.save(itemId);
//                trace.end(status);
//            } catch (Exception e) {
//                trace.exception(status, e);
//                throw e;
//            }
        }
}
