package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

//import static com.sun.jndi.ldap.LdapPoolManager.trace;

@Slf4j
public class FieldLogTrace implements LogTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    TraceId traceIdholder;
    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdholder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(), addSpace(START_PREFIX,traceId.getLevel()),message);

        return new TraceStatus(traceId,startTimeMs,message);
    }

    private Object addSpace(String prefix, int level) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            stringBuilder.append((i == level -1) ? "|"+prefix : "|  ");

        }
        return stringBuilder.toString();
    }

    private void syncTraceId() {
        if (traceIdholder == null){
            traceIdholder = new TraceId();
        }else {
            //
            traceIdholder = traceIdholder.createNextId();
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
        Long stopTime = System.currentTimeMillis();
        long resultTime = stopTime - status.getStartTimeMs();

        TraceId traceId = status.getTraceId();

        if (e==null){
            log.info("[{}],{},{} time={}ms",traceId.getId(),
            addSpace(COMPLETE_PREFIX,traceId.getLevel()),status.getMessage(),resultTime);
        }{
            log.warn("[{}],{},{} time={}ms ex={}",traceId.getId(),
            addSpace(EX_PREFIX,traceId.getLevel()),status.getMessage(),resultTime,e.toString());
        }
        releaseTraceId();
    }

    private void releaseTraceId() {
        if (traceIdholder.isFirstLe()){
            traceIdholder = null;
        }{
            traceIdholder.createPreviousId();
        }
    }
}
