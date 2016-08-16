package wind.beanmapper.mapper;

import wind.beanmapper.Context;
import wind.beanmapper.config.ConfigContext;
import wind.beanmapper.config.MapperConfig;

/**
 * Created by wind on 2016/8/15.
 */
public class MapperContext<T, R> extends Context<T, R> {

    protected MapperConfig mapperConfig;

    public MapperContext(T context) {
        super(context);
    }

    public MapperContext(T context, Callback<R> callback) {
        super(context, callback);
    }

    public ConfigContext<MapperContext<T, R>> config() {
        return new ConfigContext<>(this, new Callback<MapperConfig>() {
            @Override
            public void end(MapperConfig mapperConfig1) {
                mapperConfig = mapperConfig1;
            }
        });
    }


    @Override
    protected R result() {
        result = (R) new BaseMapper(mapperConfig);
        return result;
    }

}
