package hello.advanced;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.FieldLogTrace;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FieldLogTraceTest {

    @Test
    void testParallelExecution() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2); // 4개의 쓰레드를 가진 풀을 생성

        // 테스트 작업을 제출
        executor.submit(this::beging_end);
        executor.submit(this::beging_end1);

        executor.shutdown(); // 작업 제출을 완료
        executor.awaitTermination(1, TimeUnit.MINUTES); // 모든 작업이 완료될 때까지 최대 1분 대기
    }
    @Test
    void beging_end(){

        FieldLogTrace trace = new FieldLogTrace();
        TraceStatus status1 = trace.begin("ctrl");
        TraceStatus status2 = trace.begin("service");
        TraceStatus status3 = trace.begin("repo");
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void beging_end1(){
        FieldLogTrace trace = new FieldLogTrace();
        TraceStatus status1 = trace.begin("ctrl2");
        TraceStatus status2 = trace.begin("service2");
        TraceStatus status3 = trace.begin("repo2");
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);


    }




}
