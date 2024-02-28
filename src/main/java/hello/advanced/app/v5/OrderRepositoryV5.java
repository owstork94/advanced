package hello.advanced.app.v5;

import hello.advanced.template.AbstractTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

    private final LogTrace trace;
    public void save(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call(){
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        };
        template.excute("OrderRepository.save()");
    }
    private void sleep(int mil){
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
