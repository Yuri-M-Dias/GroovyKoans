/*
 * Copyright (c) 2012-2014 nadavc <https://twitter.com/nadavc>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package org.groovykoans.koan06

import groovy.io.FileType

import java.nio.file.attribute.FileTime
import java.nio.file.spi.FileTypeDetector

/**
 * Koan06 - More closures
 *
 * Resource list:
 *   * http://docs.groovy-lang.org/latest/html/groovy-jdk/java/lang/Object.html#with(groovy.lang.Closure)
 *   * http://docs.groovy-lang.org/latest/html/groovy-jdk/java/lang/Object.html#collect(groovy.lang.Closure)
 *   * http://docs.groovy-lang.org/latest/html/groovy-jdk/java/util/Collection.html
 *   * http://docs.groovy-lang.org/latest/html/groovy-jdk/java/io/File.html#eachFileRecurse(groovy.lang.Closure)
 *   * http://docs.groovy-lang.org/latest/html/groovy-jdk/java/lang/Object.html
 */
class Koan06 extends GroovyTestCase {

    void test01_WithMethod() {
        // The 'with()' method [ http://docs.groovy-lang.org/latest/html/groovy-jdk/java/lang/Object.html#with(groovy.lang.Closure) ]
        // allows you to access an object within a closure without explicitly referring to it.

        // This is how Java StringBuilders are used:
        StringBuilder javaStringBuilder = new StringBuilder();
        javaStringBuilder.append("roses are #FF0000\\n");
        javaStringBuilder.append("violets are #0000FF\\n");
        javaStringBuilder.append("all my base\\n")
        javaStringBuilder.append("are belong to you\\n")
        String javaResult = javaStringBuilder.toString();

        // Groovy-fy the above code, using StringBuilder and with() to get the same result in Groovy
        String groovyResult
        // ------------ START EDITING HERE ----------------------
        groovyResult = new StringBuilder().with {
            it.append("roses are #FF0000\\n");
            it.append("violets are #0000FF\\n");
            it.append("all my base\\n")
            it.append("are belong to you\\n")
            it.toString()
        }

        // ------------ STOP EDITING HERE  ----------------------
        assert groovyResult == javaResult
    }

    void test02_CollectMethodOnLists() {
        // We're often required to iterate through a whole list and and perform some sort of transformation on
        // some (or all) of the items, returning a new list. Groovy has a method just for that:
        // http://docs.groovy-lang.org/latest/html/groovy-jdk/java/lang/Object.html#collect(groovy.lang.Closure)

        // Using collect() and a method from http://docs.groovy-lang.org/latest/html/groovy-jdk/java/util/Collection.html, create
        // a list of UNIQUE class types from the following list:
        def differentTypes = [1, 'String', "GString", 'a', 'Another string', 0]
        def uniqueTypes = []
        // ------------ START EDITING HERE ----------------------
        uniqueTypes = differentTypes.collect {
            it.getClass()
        }.unique()
        // ------------ STOP EDITING HERE  ----------------------
        assert uniqueTypes == [Integer, String]
    }

    void test03_FileIteration() {
        // Groovy's File enhancements includes an iterator that walks through all files
        // http://docs.groovy-lang.org/latest/html/groovy-jdk/java/io/File.html#eachFileRecurse(groovy.lang.Closure)

        // Use the eachFileRecurse iterator to find the number of files that contain the string 'Lorem'
        // under the src directory
        int count = 0
        // ------------ START EDITING HERE ----------------------
        new File('./src').eachFileRecurse FileType.FILES, {
             if (it.readLines().any{ it.contains('Lorem') }) {
                 count++;
             }
        }
        // ------------ STOP EDITING HERE  ----------------------
        assert count == 3

    }

    void test04_ConcludingExercise() {
        // Using methods from Groovy object (http://docs.groovy-lang.org/latest/html/groovy-jdk/java/lang/Object.html) and
        // range objects, store all the prime numbers between 200 and 250 in the target variable
        def primesBetween200And250 = []
        // ------------ START EDITING HERE ----------------------
        primesBetween200And250 = (200..250).findAll {
            if(it % 2 == 0 || it % 3 == 0) {
                return false
            } else {
                def i = 5;
                while (i*i <= it){
                    if (it % i == 0 || it % (i + 2) == 0) {
                        return false
                    }
                    i += 6
                }
                return true
            }
        }


        // ------------ STOP EDITING HERE  ----------------------
        assert primesBetween200And250 == [211, 223, 227, 229, 233, 239, 241]

    }

}
