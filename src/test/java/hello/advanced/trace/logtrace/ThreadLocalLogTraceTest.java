package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;


public class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2(){
        TraceStatus status = trace.begin("ctrl");
        TraceStatus status2 = trace.begin("service");
        TraceStatus status3 = trace.begin("repo");
        trace.end(status3);
        trace.end(status2);
        trace.end(status);
    }
}
