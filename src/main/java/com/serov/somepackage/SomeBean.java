package com.serov.somepackage;

import com.serov.annotations.AutoInjectable;
/**
 * Another another public class that implements the interface SomeInterface that contains the function that does something
 * @author n.a.serov
 */
public class SomeBean {
	/**
	 * Default constructor without parameters
	 */
	public SomeBean() {}
	/**
	 * The first field to be injected
	 */
    @AutoInjectable
    private SomeInterface field1;
    /**
     * The second field to be injected
     */
    @AutoInjectable
    private SomeOtherInterface field2;
    /**
     * Function that does something with fields
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}