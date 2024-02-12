package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;

public class FieldLogTrace implements LogTrace{
    @Override
    public TraceStatus begin(String message) {
        return null;
    }

    @Override
    public void end(TraceStatus status) {

    }

    @Override
    public void exception(TraceStatus status, Exception e) {

    }
}
