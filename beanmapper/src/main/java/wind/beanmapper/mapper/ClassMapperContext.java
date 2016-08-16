package wind.beanmapper.mapper;

/**
 * Created by wind on 2016/8/15.
 */
public class ClassMapperContext<T, A, B> extends MapperContext<T, ClassMapper<A, B>> {

    private Class<A> clsA;
    private Class<B> clsB;

    public ClassMapperContext(T context) {
        super(context);
    }

    public ClassMapperContext(T context, Callback callback) {
        super(context, callback);
    }

    public ClassMapperContext<T, A, B> classMapper(Class<A> clsA, Class<B> clsB) {
        this.clsA = clsA;
        this.clsB = clsB;
        return this;
    }

    @Override
    protected ClassMapper<A, B> result() {
        return new ClassMapper<>(clsA, clsB, mapperConfig);
    }

}
