package wind.beanmapper.config;


import java.util.List;
import java.util.Map;

import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.Instantiater;
import wind.beanmapper.property.PropertyMap;
import wind.beanmapper.property.PropertyMapper;
import wind.beanmapper.property.PropertyNameMapper;
import wind.beanmapper.property.PropertyResolver;

/**
 * Created by wind on 2016/8/13.
 */
public class MapperConfig {

    private PropertyResolver propertyResolver;

    private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converterMap;

    private Instantiater instantiater;

    private PropertyMap<PropertyMapper> propertyMappers;

    private PropertyMap<PropertyConfig<?, ?>> propertyConfigMap;

    public boolean mapNull;

    public boolean mapNullReverse;

    public PropertyResolver getPropertyResolver() {
        return propertyResolver;
    }

    public Instantiater getInstantiater() {
        return instantiater;
    }

    public PropertyConfig getPropertyConfig(String name) {
        return propertyConfigMap.get(name);
    }

    public PropertyMapper getPropertyMapper(String name) {
        return propertyMappers.get(name);
    }

    public <A, B> Converter<A, B> getConverter(Class<A> clsA, Class<B> clsB) {
        Map<Class<?>, Converter<?, ?>> map = converterMap.get(clsA);
        if (map == null) {
            map = converterMap.get(clsB);
            return (Converter<A, B>) map.get(clsA);
        } else {
            return (Converter<A, B>) map.get(clsB);
        }
    }
}
