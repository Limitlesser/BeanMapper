package wind.beanmapper.config;

import wind.beanmapper.Context;
import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.Instantiater;
import wind.beanmapper.property.PropertyResolver;

/**
 * Created by wind on 2016/8/15.
 */
public class ConfigContext<T> extends Context<T, MapperConfig> {

    private MapperConfig mapperConfig;
    private MapperConfig.Builder builder;

    public ConfigContext(T context, Callback<MapperConfig> callback) {
        super(context, callback);
        builder = new MapperConfig.Builder();
    }

    public ConfigContext<T> propertyResolver(PropertyResolver propertyResolver) {
        builder.propertyResolver(propertyResolver);
        return this;
    }

    public ConfigContext<T> instantiater(Instantiater instantiater) {
        builder.instantiater(instantiater);
        return this;
    }

    public ConfigContext<T> converter(Converter converter) {
        builder.converter(converter);
        return this;
    }

    public ConfigContext<T> propertyConfig(String name, PropertyConfig propertyConfig) {
        builder.propertyConfig(name, propertyConfig);
        return this;
    }

    public ConfigContext<T> propertyConfig(String nameA, String nameB, PropertyConfig propertyConfig) {
        builder.propertyConfig(nameA, nameB, propertyConfig);
        return this;
    }

    public ConfigContext<T> propertyMapper(String nameA, String nameB) {
        builder.propertyMapper(nameA, nameB);
        return this;
    }


    @Override
    protected MapperConfig result() {
        mapperConfig = builder.build();
        return mapperConfig;
    }
}
