package wind.beanmapper.mapper;

import wind.beanmapper.Context;
import wind.beanmapper.config.ConfigContext;
import wind.beanmapper.config.MapperConfig;

/**
 * Created by wind on 2016/8/15.
 */
public class MapperContext<T> extends Context<T, Mapper> {

    private BaseMapper mapper;
    private MapperConfig mapperConfig;

    public MapperContext(T context, Callback<Mapper> callback) {
        super(context, callback);
    }

    public ConfigContext<MapperContext<T>> config() {
        return new ConfigContext<>(this, new Callback<MapperConfig>() {
            @Override
            public void end(MapperConfig mapperConfig1) {
                mapperConfig = mapperConfig1;
            }
        });
    }


    @Override
    protected Mapper result() {
        mapper = new BaseMapper(mapperConfig);
        return mapper;
    }

}
