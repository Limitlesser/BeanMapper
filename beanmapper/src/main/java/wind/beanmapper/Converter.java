package wind.beanmapper;

/**
 * Created by wind on 2016/8/13.
 */
public interface Converter<A, B> {

    B convert(A a);

    A reconvert(B b);

}
