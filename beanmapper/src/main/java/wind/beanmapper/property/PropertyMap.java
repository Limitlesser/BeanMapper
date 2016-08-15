package wind.beanmapper.property;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wind on 2016/8/15.
 */
public class PropertyMap<T> {

    Map<String, T> propertyMapA = new HashMap<>();
    Map<String, T> propertyMapB = new HashMap<>();

    public T get(String name) {
        T a = propertyMapA.get(name);
        return a == null ? propertyMapB.get(name) : a;
    }

    public void put(String name, T value) {
        propertyMapA.put(name, value);
    }

    public void put(String nameA, String nameB, T value) {
        propertyMapA.put(nameA, value);
        propertyMapB.put(nameB, value);
    }

}
