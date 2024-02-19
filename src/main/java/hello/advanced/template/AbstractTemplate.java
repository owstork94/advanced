package hello.advanced.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate <T>{
    private final LogTrace logTrace;

    public T excute(String message){

        TraceStatus status = null;

       try {
           status = logTrace.begin(message);

           //변화하는..
         T result = call();

           logTrace.end(status);
           return result;
       }catch (Exception e){
           logTrace.exception(status,e);
           throw e;
    }

}

    protected abstract T call();
}
