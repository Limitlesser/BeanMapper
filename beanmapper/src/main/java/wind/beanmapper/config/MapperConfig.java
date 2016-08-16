package wind.beanmapper.config;


import java.util.HashMap;
import java.util.Map;

import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.Instantiate;
import wind.beanmapper.property.PropertyMap;
import wind.beanmapper.property.PropertyMapper;
import wind.beanmapper.property.PropertyNameMapper;
import wind.beanmapper.property.PropertyResolver;

/**
 * Created by wind on 2016/8/13.
 */
public class MapperConfig {

    private PropertyResolver propertyResolver;

    private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converterMap = new HashMap<>();

    private Instantiate instantiate;

    private PropertyMap<PropertyMapper> propertyMappers = new PropertyMap<>();

    private PropertyMap<PropertyConfig<?, ?>> propertyConfigMap = new PropertyMap<>();


    public PropertyResolver getPropertyResolver() {
        return propertyResolver;
    }

    public Instantiate getInstantiate() {
        return instantiate;
    }

    public PropertyConfig getPropertyConfig(String name) {
        return propertyConfigMap.get(name);
    }

    public PropertyMapper getPropertyMapper(String name) {
        return propertyMappers.get(name);
    }

    public Converter<?, ?> getConverter(Class clsA, Class clsB) {
        Map<Class<?>, Converter<?, ?>> map = converterMap.get(clsA);
        if (map == null) {
            map = converterMap.get(clsB);
            if (map == null) {
                return null;
            }
            return map.get(clsA);
        } else {
            return map.get(clsB);
        }
    }

    public void setPropertyResolver(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    public void setInstantiate(Instantiate instantiate) {
        this.instantiate = instantiate;
    }

    public void addConverter(Converter converter) {
        if (converterMap == null) {
            converterMap = new HashMap<>();
        }
        Map<Class<?>, Converter<?, ?>> map = converterMap.get(converter.typeA());
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(converter.typeB(), converter);
    }

    public void putPropertyConfig(String name, PropertyConfig propertyConfig) {
        propertyConfigMap.put(name, propertyConfig);
    }

    public void putPropertyConfig(String nameA, String nameB, PropertyConfig propertyConfig) {
        propertyMappers.put(nameA, nameB, new PropertyNameMapper(nameA, nameB));
        propertyConfigMap.put(nameA, nameB, propertyConfig);
    }

    public void addPropertyMapper(String nameA, String nameB) {
        propertyMappers.put(nameA, nameB, new PropertyNameMapper(nameA, nameB));
    }

}
