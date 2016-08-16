package wind.beanmapper.instantiation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wind on 2016/8/15.
 */
public class ReflectInstantiate implements Instantiate {
    @Override
    public <T> T instantiate(Class<T> cls) {
        try {
            Constructor[] constructors = cls.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                Object[] params = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class parameterType = parameterTypes[i];
                    if (parameterType.equals(int.class)
                            || parameterType.equals(byte.class)
                            || parameterType.equals(short.class)
                            || parameterType.equals(long.class)
                            || parameterType.equals(float.class)
                            || parameterType.equals(double.class)
                            || parameterType.equals(char.class)) {
                        params[i] = 0;
                    } else if (parameterType.equals(boolean.class)) {
                        params[i] = false;
                    } else {
                        params[i] = null;
                    }
                }
                return (T) constructor.newInstance(params);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
