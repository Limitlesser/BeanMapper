package wind.beanmapper.converter;

import wind.beanmapper.utils.ReflectUtils;

/**
 * Created by wind on 2016/8/13.
 */
public abstract class Converter<A, B> {

    public Class<A> typeA() {
        return ReflectUtils.getParameterizedTypes(this, 0);
    }

    public Class<A> typeB() {
        return ReflectUtils.getParameterizedTypes(this, 1);
    }

    public abstract B convert(A a);

    public abstract A reconvert(B b);

}
