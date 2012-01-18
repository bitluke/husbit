package com.bfs.husbit.util.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by IntelliJ IDEA.
 * User: lukman
 * Date: 1/4/12
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD})
@Qualifier
public @interface HusbitDatabase {
}
