package wind.beanmapper;

/**
 * Created by wind on 2016/8/15.
 */
public abstract class Context<T, R> {

    private T context;
    private Callback<R> callback;

    public Context(T context, Callback<R> callback) {
        this.context = context;
        this.callback = callback;
    }

    abstract protected R result();


    public T end() {
        callback.end(result());
        return context;
    }

    public interface Callback<R> {

        void end(R r);
    }
}
