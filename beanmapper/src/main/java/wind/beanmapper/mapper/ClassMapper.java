package wind.beanmapper.mapper;

import wind.beanmapper.config.MapperConfig;

/**
 * Created by wind on 2016/8/15.
 */
public class ClassMapper<S, D> extends BaseMapper<S, D> {

    private Class<S> sClass;

    private Class<D> dClass;

    public ClassMapper(MapperConfig config) {
        super(config);
    }

    public ClassMapper(Class<S> sClass, Class<D> dClass, MapperConfig config) {
        super(config);
        this.sClass = sClass;
        this.dClass = dClass;
    }

    public Class<S> sClass() {
        return sClass;
    }

    public Class<D> dClass() {
        return dClass;
    }
}
