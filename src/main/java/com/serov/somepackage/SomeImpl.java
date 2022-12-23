package com.serov.somepackage;

/**
 * A public class that implements the interface SomeInterface that contains the function that does something
 * @author n.a.serov
 */
public class SomeImpl implements SomeInterface {
	/**
	 * Default constructor without parameters
	 */
	public SomeImpl() {}
	/**
	 * A function that does something (prints "A")
	 */
    public void doSomething() {
    	System.out.println("A");
    }
}