package wind.beanmapper.config;


import java.util.HashMap;
import java.util.Map;

import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.Instantiater;
import wind.beanmapper.instantiation.ReflectInstantiaer;
import wind.beanmapper.property.PropertyMap;
import wind.beanmapper.property.PropertyMapper;
import wind.beanmapper.property.PropertyNameMapper;
import wind.beanmapper.property.PropertyResolver;
import wind.beanmapper.property.ReflectPropertyResolver;

/**
 * Created by wind on 2016/8/13.
 */
public class MapperConfig {

    private PropertyResolver propertyResolver;

    private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converterMap;

    private Instantiater instantiater;

    private PropertyMap<PropertyMapper> propertyMappers;

    private PropertyMap<PropertyConfig<?, ?>> propertyConfigMap;

    private MapperConfig(Builder builder) {
        propertyResolver = builder.propertyResolver;
        instantiater = builder.instantiater;
        converterMap = builder.converterMap;
        propertyMappers = builder.propertyMappers;
        propertyConfigMap = builder.propertyConfigMap;
    }


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

    public void setInstantiater(Instantiater instantiater) {
        this.instantiater = instantiater;
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
        propertyConfigMap.put(nameA, nameB, propertyConfig);
    }

    public void addPropertyMapper(String nameA, String nameB) {
        propertyMappers.put(nameA, nameB, new PropertyNameMapper(nameA, nameB));
    }

    public static class Builder {

        private PropertyResolver propertyResolver;

        private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converterMap;

        private Instantiater instantiater;

        private PropertyMap<PropertyMapper> propertyMappers = new PropertyMap<>();

        private PropertyMap<PropertyConfig<?, ?>> propertyConfigMap = new PropertyMap<>();

        public Builder() {
        }

        public Builder propertyResolver(PropertyResolver propertyResolver) {
            this.propertyResolver = propertyResolver;
            return this;
        }

        public Builder instantiater(Instantiater instantiater) {
            this.instantiater = instantiater;
            return this;
        }

        public Builder converter(Converter converter) {
            if (converterMap == null) {
                converterMap = new HashMap<>();
            }
            Map<Class<?>, Converter<?, ?>> map = converterMap.get(converter.typeA());
            if (map == null) {
                map = new HashMap<>();
            }
            map.put(converter.typeB(), converter);
            return this;
        }

        public Builder propertyConfig(String name, PropertyConfig propertyConfig) {
            propertyConfigMap.put(name, propertyConfig);
            return this;
        }

        public Builder propertyConfig(String nameA, String nameB, PropertyConfig propertyConfig) {
            propertyConfigMap.put(nameA, nameB, propertyConfig);
            return this;
        }

        public Builder propertyMapper(String nameA, String nameB) {
            propertyMappers.put(nameA, nameB, new PropertyNameMapper(nameA, nameB));
            return this;
        }

        public MapperConfig build() {
            if (propertyResolver == null) {
                propertyResolver = new ReflectPropertyResolver();
            }
            if (instantiater == null) {
                instantiater = new ReflectInstantiaer();
            }
            return new MapperConfig(this);
        }
    }
}
