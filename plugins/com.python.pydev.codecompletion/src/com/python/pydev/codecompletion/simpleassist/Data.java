/**
 * Copyright (c) 2005-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 * 
 * Code for Cacheca model implementation for code completion included - Siddhika Cowlagi,  Vincent Hellendoorn , Premkumar T Devanbu
 */

package com.python.pydev.codecompletion.simpleassist;

import java.net.URL;

//Author: This class was originally written in C++ by Zhaopeng; converted to Java by Christine

public class Data {

    //data wrapper class

    static boolean USE_BACKOFF = true;

    static int NGRAM_ORDER = 3;

    static boolean USE_CACHE = true;
    static int CACHE_ORDER = 10;
    static int CACHE_MIN_ORDER = 3;
    static boolean USE_FILE_CACHE = true;

    static int BEAM_SIZE = 10;

    static Ngram NGRAM;
    static Cache CACHE;

    static boolean Init(URL u, int ngram_order) {
        NGRAM_ORDER = ngram_order;
        NGRAM = new Ngram(u, NGRAM_ORDER, BEAM_SIZE);
        CACHE = new Cache(CACHE_ORDER, CACHE_MIN_ORDER);
        return true;
    }

}
