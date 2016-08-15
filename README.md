## BeanMapper

a lib for map properties of java pojo

# Base Usage

        A a = new A();
        a.setName("wind");
        a.setAge(17);
        B b = BeanMapper.map(a, B.class);
        System.out.print(b);
        assertEquals("wind", b.getName());
        assertEquals(17, b.getAge());
# Advance
        ClassMapper<A, B> classMapper = BeanMapper.classMapper(A.class, B.class)
                .config()
                .propertyResolver(new CachedPropertyResolver(new ReflectPropertyResolver()))
                .instantiater(new ReflectInstantiaer())
                .field("name", "Name")
                .field("birthday", "age", new Converter<Date, Integer>() {
                    @Override
                    public Integer convert(Date date) {
                        return new Date().getYear() - date.getYear();
                    }

                    @Override
                    public Date reconvert(Integer integer) {
                        return new Date(new Date().getYear() - integer, 0, 0);
                    }
                })
                .end()
                .get();
        B b = classMapper.map(a, B.class);

this project protected by `SATA` protocol (The Star And Thank Author License) 

you shall give a star before your usage ,details see [LICENSE](LICENSE.txt)

