package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();
    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();
        Long startTime = System.currentTimeMillis();
        log.info("[{}]{}{}",traceId.getId(),addspace(START_PREFIX,traceId.getLevel()),startTime);
        return new TraceStatus(traceId,startTime,message);
    }

    private Object addspace(String prefix,int level ) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level-1)?"|"+prefix : "  |");
        }
        return  sb.toString();
    }

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId == null){
            traceIdHolder.set(new TraceId());
        }else {
            traceIdHolder.set(traceId.createNextId());
        }

    }

    @Override
    public void end(TraceStatus status) {
        complate(status,null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complate(status,e);

    }

    private void complate(TraceStatus status, Exception e) {
       TraceId traceId = traceIdHolder.get();
       long startTime = status.getStartTimeMs();
       long endTime = System.currentTimeMillis();
       long stopTime = endTime - startTime;
        if (e == null){
            log.info("[{}]{}{} {}",traceId.getId(),addspace(COMPLETE_PREFIX, traceId.getLevel()),status.getMessage(),stopTime);
        }else {
            log.info("[{}]{}{} {}",traceId.getId(),addspace(EX_PREFIX, traceId.getLevel()),e.getMessage(),stopTime);
        }
        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();

        if (traceId.isFirstLe()){
            traceIdHolder.remove();
        }else {
            traceIdHolder.set(traceId.createPreviousId());
        }
    }
}
