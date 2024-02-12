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
            traceIdholder = traceIdholder.createNextId();
        }
    }

    @Override
    public void end(TraceStatus status) {

    }

    @Override
    public void exception(TraceStatus status, Exception e) {

    }
}
