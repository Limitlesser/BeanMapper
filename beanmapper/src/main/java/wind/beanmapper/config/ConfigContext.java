package wind.beanmapper.config;

import wind.beanmapper.Context;
import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.Instantiate;
import wind.beanmapper.property.PropertyResolver;

/**
 * Created by wind on 2016/8/15.
 */
public class ConfigContext<T> extends Context<T, MapperConfig> {

    private MapperConfig mapperConfig;

    public ConfigContext(T context) {
        super(context);
    }

    public ConfigContext(T context, Callback<MapperConfig> callback) {
        super(context, callback);
        mapperConfig = new MapperConfig();
    }

    public ConfigContext<T> propertyResolver(PropertyResolver propertyResolver) {
        mapperConfig.setPropertyResolver(propertyResolver);
        return this;
    }

    public ConfigContext<T> instantiater(Instantiate instantiate) {
        mapperConfig.setInstantiate(instantiate);
        return this;
    }

    public ConfigContext<T> converter(Converter converter) {
        mapperConfig.addConverter(converter);
        return this;
    }

    public ConfigContext<T> field(String name, Converter converter) {
        return field(name, new PropertyConfig(converter));
    }

    public ConfigContext<T> field(String name, PropertyConfig propertyConfig) {
        mapperConfig.putPropertyConfig(name, propertyConfig);
        return this;
    }

    public ConfigContext<T> field(String nameA, String nameB, PropertyConfig propertyConfig) {
        mapperConfig.putPropertyConfig(nameA, nameB, propertyConfig);
        return this;
    }

    public ConfigContext<T> field(String nameA, String nameB, Converter<?, ?> converter) {
        return field(nameA, nameB, new PropertyConfig(converter));
    }

    public ConfigContext<T> field(String nameA, String nameB) {
        mapperConfig.addPropertyMapper(nameA, nameB);
        return this;
    }


    @Override
    protected MapperConfig result() {
        return mapperConfig;
    }
}
