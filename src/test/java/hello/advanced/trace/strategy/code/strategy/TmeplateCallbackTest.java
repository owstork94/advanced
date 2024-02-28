package hello.advanced.trace.strategy.code.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TmeplateCallbackTest {

    @Test
    void callBackV1(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.excute(() -> log.info("callBack 1-1"));
    }

    @Test
    void callBackV2(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.excute(new Callback() {
            @Override
            public void call() {
                log.info("callBack1-2");
            }
        });
    }
}
