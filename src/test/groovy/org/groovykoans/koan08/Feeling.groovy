/*
 * Copyright (c) 2012-2014 nadavc <https://twitter.com/nadavc>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package org.groovykoans.koan08

public enum Feeling {
    Happy, Sad, Neutral, Suicidal, Anticipation, Surprised, Relaxed, Guilty

    // ------------ START EDITING HERE ----------------------

    public boolean isCase(final Cartoon cartoon) {
        cartoon?.feeling == this
    }

    public boolean isCase(final Person person) {
        def result = false
        person?.feelings?.each {
            def feel = it
            values().each {
                if (feel == it){
                    result = true
                }
            }
        }
        result
    }

    // ------------ STOP EDITING HERE  ----------------------


}