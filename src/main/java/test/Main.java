package test;

import bean.SomeBean;
import injector.Injector;

public class Main {

    public static void main(String[] args) {
        SomeBean bean = new Injector().inject(new SomeBean());
        bean.foo();
    }
}
