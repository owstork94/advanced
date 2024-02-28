package hello.advanced.trace;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV1(){
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.excute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV11 = new ContextV1(strategyLogic2);
        contextV11.excute();
    }

    @Test
    void strategyV2(){
        Strategy strategyLogic11 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1-1");
            }
        };
        log.info("class : {}",strategyLogic11);
        ContextV1 contextV1 = new ContextV1(strategyLogic11);
        contextV1.excute();


        Strategy strategyLogic12 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1-2");
            }
        };

        ContextV1 contextV11 = new ContextV1(strategyLogic12);
        contextV11.excute();
    }


    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스로직 2-1");
            }
        });
        contextV1.excute();
    }

    @Test
    void strategyV4(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스로직 3-1"));
        contextV1.excute();

        ContextV1 contextV11 = new ContextV1(() -> log.info("비즈니스로직 3-2"));
        contextV11.excute();
    }
}
