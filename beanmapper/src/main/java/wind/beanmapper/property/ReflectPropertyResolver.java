package wind.beanmapper.property;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wind on 2016/8/15.
 */
public class ReflectPropertyResolver implements PropertyResolver {
    @Override
    public Map<String, Property<?>> getProperties(Class cls) {
        Map<String, Property<?>> propertyMap = new HashMap<>();
        Field[] fields = cls.getDeclaredFields();
        for (final Field field : fields) {
            field.setAccessible(true);
            Property<?> property = new Property<>(field.getName(), field.getType(), new Property.Entry() {
                @Override
                public Object get(Object object) {
                    try {
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                public void set(Object object, Object value) {
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            propertyMap.put(field.getName(), property);
        }
        return propertyMap;
    }
}
