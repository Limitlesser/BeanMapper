package wind.beanmapper.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wind on 2016/8/15.
 */
public class ReflectUtils {

    public static Class getParameterizedTypes(Object object, int index) {
        Type superclassType = object.getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        Type[] types = ((ParameterizedType) superclassType).getActualTypeArguments();
        return (Class) types[index];
    }

}
