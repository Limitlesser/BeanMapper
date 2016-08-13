package wind.beanmapper;

/**
 * Created by wind on 2016/8/13.
 */
public interface Mapper<S, D> {

    D map(S source);

}
