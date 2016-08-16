package wind.beanmapper.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wind on 2016/8/15.
 */
public class AnyDoubleKeyMap<K, T> {

    Map<K, T> propertyMapA = new HashMap<>();
    Map<K, T> propertyMapB = new HashMap<>();

    public T get(K key) {
        T a = propertyMapA.get(key);
        return a == null ? propertyMapB.get(key) : a;
    }

    public void put(K key, T value) {
        propertyMapA.put(key, value);
    }

    public void put(K keyA, K keyB, T value) {
        propertyMapA.put(keyA, value);
        propertyMapB.put(keyB, value);
    }

}
