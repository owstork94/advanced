package hello.advanced;

import hello.advanced.trace.HelloTraceV1.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV1Test {


    @Test
    void begin_end(){
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus status = traceV1.begin("hello");


        traceV1.end(status);



    }


//    @Test
//    void begin_end() {
//        // given
//        HelloTraceV1 trace = new HelloTraceV1();
//
//        // when
//        trace.begin("hello");
//        trace.end("hello");
//    }
}
