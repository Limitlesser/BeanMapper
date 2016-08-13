package wind.beanmapper;

import java.util.Map;

/**
 * Created by wind on 2016/8/13.
 */
public class BaseMapper<S, D> implements Mapper<S, D> {


    private MapperConfig mapperConfig;


    @Override
    public D map(S source) {
        return null;
    }
}
