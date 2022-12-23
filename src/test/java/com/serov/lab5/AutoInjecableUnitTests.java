package com.serov.lab5;

import org.junit.jupiter.api.TestInstance;

import com.serov.injectors.Injector;
import com.serov.somepackage.SomeBean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


/**
 * Testing the AutoInjecable implementation
 * @author n.a.serov
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AutoInjecableUnitTests {
	/**
	 * An output stream for intercepting the print stream
	 */
	private final static ByteArrayOutputStream output = new ByteArrayOutputStream();
    /**
     * Default test class constructor without parameters
     */
    public AutoInjecableUnitTests() {}
    /**
     * Intercepting the print stream to compare the results with expected
     */
    @BeforeAll
    public static void interceptPrintStream() {
    	System.setOut(new PrintStream(output));
    }
    /**
     * Clearing the print stream after each test
     */
    @AfterEach
    public void clearPrintStream() {
		output.reset();
    }
    /**
     * Path to the first test file
     */
    private final static String pathOne = "src/test/resources/first.properties";
    /**
     * Path to the second test file
     */
    private final static String pathTwo = "src/test/resources/second.properties";
    /**
     * Test with the first set given in example
     */
    @Test
    public void testInjectAC() {
    	SomeBean someBean = null;
		try {
			someBean = (new Injector().inject(new SomeBean(), pathOne));
		} catch (IOException e) {
			e.printStackTrace();
		}
		someBean.foo();
        String lineSeparator = System.getProperty("line.separator");
        assertEquals("A"+lineSeparator+"C"+lineSeparator, output.toString());
    }
    /**
     * Test with the second set given in example
     */
    @Test
    public void testInjectBC() {
    	SomeBean someBean = null;
		try {
			someBean = (new Injector().inject(new SomeBean(), pathTwo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		someBean.foo();
        String lineSeparator = System.getProperty("line.separator");
        assertEquals("B"+lineSeparator+"C"+lineSeparator, output.toString());
    }
    /**
     * Test if the fields are not initialized
     */
    @Test
    public void testFieldsNotInitialized() {
    	assertThrows(NullPointerException.class, () -> {
    		(new SomeBean()).foo();
        });
    }
}