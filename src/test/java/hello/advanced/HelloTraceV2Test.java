package hello.advanced;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.HelloTraceV2.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

    HelloTraceV2 traceV2 = new HelloTraceV2();

    @Test
    public void test() {
        TraceStatus status = traceV2.begin("HelloTraceV2Test.test");
        traceV2.beginSync(status.getTraceId(),"HelloTraceV2Test.test");
        traceV2.beginSync(status.getTraceId(),"HelloTraceV2Test.test");
        traceV2.beginSync(status.getTraceId(),"HelloTraceV2Test.test");
        traceV2.end(status);
    }
}
