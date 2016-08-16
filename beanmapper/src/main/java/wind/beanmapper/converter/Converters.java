package wind.beanmapper.converter;

import wind.beanmapper.utils.ReflectUtils;

/**
 * Created by wind on 2016/8/16.
 */
public class Converters {

    public static Class typeA(Converter converter) {
        return ReflectUtils.getParameterizedTypes(converter)[0];
    }

    public static Class typeB(Converter converter) {
        return ReflectUtils.getParameterizedTypes(converter)[1];
    }

}
