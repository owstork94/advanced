package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {
    public void excute(Strategy strategyLogic1) {
        long startTime = System.currentTimeMillis();
        strategyLogic1.call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}",resultTime);
    }
}
