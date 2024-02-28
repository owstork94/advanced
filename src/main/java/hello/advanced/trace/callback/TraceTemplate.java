package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

import java.util.concurrent.Callable;

public class TraceTemplate {

    private LogTrace logTrace;

    public TraceTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T excute(String message, TraceCallable<T> callback){
        TraceStatus status = null;

        try {
            status = logTrace.begin(message);

            T result = callback.call();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status,e);
            throw e;
        }
    }
}
