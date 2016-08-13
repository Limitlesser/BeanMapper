package wind.beanmapper;

/**
 * Created by wind on 2016/8/13.
 */
public class PropertyConfig<A, B> {

    public Converter<A, B> converter;
    public boolean mapNull;
    public boolean mapNullReverse;


}
