/*
 * Copyright (c) 2012-2014 nadavc <https://twitter.com/nadavc>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package org.groovykoans.koan09

import org.codehaus.groovy.runtime.InvokerHelper

class Robot {
    // ------------ START EDITING HERE ----------------------
    int x, y
    private final int offset = 1

    void left(){
        x -= offset
    }

    void right(){
        x += offset
    }

    void up(){
        y += offset
    }

    void down(){
        y -= offset
    }

    def invokeMethod(String name, Object args){
        def nameMatcher = ~/([A-Z][a-z]{1,4})/
        def methods = this.metaClass.methods*.name.sort().unique()
        name.findAll(nameMatcher) {
            def methodName = it[1].toLowerCase()
            if (methodName in methods) {
                this."${methodName}"()
            }
        }
    }
    // ------------ STOP EDITING HERE  ----------------------
}
