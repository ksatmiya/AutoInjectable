package bean;

import annotation.AutoInjectable;
import interfaces.Some;
import interfaces.SomeOther;

public class SomeBean {

    @AutoInjectable
    private Some field1;

    @AutoInjectable
    private SomeOther field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }

    public Some getField1() {
        return field1;
    }

    public SomeOther getField2() {
        return field2;
    }
}
