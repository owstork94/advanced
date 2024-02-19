package hello.advanced.trace.template;


import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class templateMethodTest {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();

    }

    private void logic1() {
        Long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        Long endTime = System.currentTimeMillis();
        Long resultTime = endTime - startTime;
//        System.out.println("resultTime = "+resultTime);
        log.info("resultTime = {}",resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행 ");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}",resultTime);
    }

    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();

        template1.excue();

        AbstractTemplate template2 = new SubClassLogic2();

        template2.excue();

    }

    @Test
    void TemplateMethodV2(){
        AbstractTemplate abstractTemplate = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("sss");
            }
        };
        abstractTemplate.excue();

        AbstractTemplate abstractTemplate1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("kkk");
            }
        };
        abstractTemplate1.excue();
    }

}
