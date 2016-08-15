package wind.beanmapper.config;

import wind.beanmapper.converter.Converter;

/**
 * Created by wind on 2016/8/13.
 */
public class PropertyConfig<A, B> {

    public Converter<A, B> converter;

    public PropertyConfig(Converter converter) {
        this.converter = converter;
    }

    public Converter<A, B> getConverter() {
        return converter;
    }
}
