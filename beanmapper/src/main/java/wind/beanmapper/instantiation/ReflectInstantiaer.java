package wind.beanmapper.instantiation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wind on 2016/8/15.
 */
public class ReflectInstantiaer implements Instantiater {
    @Override
    public <T> T instantiate(Class<T> cls) {
        try {
            Constructor<T> constructor = cls.getDeclaredConstructor(null);
            return constructor.newInstance(null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
