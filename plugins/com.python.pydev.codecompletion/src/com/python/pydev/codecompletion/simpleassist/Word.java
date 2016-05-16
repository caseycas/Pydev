/**
 * Copyright (c) 2005-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 * 
 * Code for Cacheca model implementation for code completion included - Siddhika Cowlagi,  Vincent Hellendoorn , Premkumar T Devanbu
 */

package com.python.pydev.codecompletion.simpleassist;

//@author Originally written in C++ by Zhaopeng; converted to Java by Christine

//Note: originally an inner class of Ngram.cpp

public class Word {
    String mToken;
    float mProb;

    public Word() {
        mProb = 0.0f;
    }

    public Word(String token, float prob) {
        mProb = prob;
        mToken = token;
    }

    @Override
    public String toString() {
        return this.mToken + ":" + this.mProb;
    }
}