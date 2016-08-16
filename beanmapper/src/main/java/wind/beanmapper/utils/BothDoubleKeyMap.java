package wind.beanmapper.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wind on 2016/8/16.
 */
public class BothDoubleKeyMap<K, V> {

    private Map<K, Map<K, V>> classMapperMap = new HashMap<>();

    public void put(K keyA, K keyB, V value) {
        Map<K, V> map = classMapperMap.get(keyA);
        if (map == null) {
            map = classMapperMap.get(keyB);
            if (map == null) {
                map = new HashMap<>();
            }
            map.put(keyA, value);
        } else {
            map.put(keyB, value);
        }
    }

    public V get(K keyA, K keyB) {
        Map<K, V> map = classMapperMap.get(keyA);
        if (map == null) {
            map = classMapperMap.get(keyB);
            if (map == null) {
                return null;
            }
            return map.get(keyA);
        }
        return map.get(keyB);
    }

}
