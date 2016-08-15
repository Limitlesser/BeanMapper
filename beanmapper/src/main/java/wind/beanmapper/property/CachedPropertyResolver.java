package wind.beanmapper.property;

import android.util.LruCache;

import java.util.Map;

/**
 * Created by wind on 2016/8/15.
 */
public class CachedPropertyResolver implements PropertyResolver {

    private LruCache<Class, Map<String, Property<?>>> propertyCache = new LruCache<>(1024 * 100);

    private PropertyResolver propertyResolver;

    public CachedPropertyResolver(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    @Override
    public Map<String, Property<?>> getProperties(Class cls) {
        Map<String, Property<?>> propertyMap = propertyCache.get(cls);
        if (propertyMap == null) {
            propertyMap = propertyResolver.getProperties(cls);
            propertyCache.put(cls, propertyMap);
        }
        return propertyMap;
    }
}
