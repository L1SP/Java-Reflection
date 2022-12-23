package com.serov.injectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.serov.annotations.AutoInjectable;

/**
 * Injector class implementation for AutoInjectable annotation
 * @author n.a.serov
 */
public class Injector {
	/**
	 * Default constructor without parameters
	 */
	public Injector() {}
    /**
     * Properties to be injected
     */
    private static Properties properties;
    /**
     * A function that injects the properties to the given object
     * @param <T> Type name
     * @param object Object to get the injection
     * @param path Path to file with parameters
     * @return Returns a new object instance with injected fields
     * @throws IOException If couldn't open FileInputStream when opening the file
     */
    public < T > T inject(T object, String path) throws IOException {
        loadProperties(path);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String fieldClassname = field.getType().toString().split(" ")[1];
                String injectedClassName = properties.getProperty(fieldClassname);
                field.setAccessible(true);
                Constructor < ? > [] constructors = null;
                try {
                    constructors = Class.forName(injectedClassName).getConstructors();
                } catch (SecurityException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Object classObject = null;
                try {
                    classObject = constructors[0].newInstance();
                } catch (InstantiationException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    field.set(object, classObject);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * Loads properties form file
     * @param path Path to file with parameters
     * @throws IOException If couldn't open FileInputStream when opening the file
     */
    private void loadProperties(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        properties = new Properties();
        properties.load(inputStream);
    }
}