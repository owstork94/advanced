package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.excute(new StrategyLogic1());
        context.excute(new StrategyLogic2());
    }

    @Test
    void strategyV3(){
        ContextV2 context = new ContextV2();
        context.excute(() -> log.info("비즈니스 로직 1 실행"));
    }
}
