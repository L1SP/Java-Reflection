package com.serov.annotations;

import java.lang.annotation.*;

/**
 * Custom annotation declaration that works with fields
 * @author n.a.serov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {}