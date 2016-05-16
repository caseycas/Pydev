/**
 * Copyright (c) 2005-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 * 
 * Code for Cacheca model implementation for code completion included - Siddhika Cowlagi,  Vincent Hellendoorn , Premkumar T Devanbu
 */

package com.python.pydev.codecompletion.simpleassist;

import java.util.ArrayList;

//@author Originally written in C++ by Zhaopeng; converted to Java by Christine

public final class Utilities {

    static ArrayList<String> split(String line, String separator)
    {
        ArrayList<String> items = new ArrayList<String>();

        int start, end;

        int sep_size = separator.length();

        start = 0;
        //See: http://en.wikipedia.org/wiki/Comparison_of_programming_languages_(string_functions)
        end = line.indexOf(separator);

        while (end != -1) //See: http://en.wikipedia.org/wiki/Comparison_of_programming_languages_(string_functions) >> returns this if not found
        {
            items.add(line.substring(start, end));

            start = end + sep_size;
            end = line.indexOf(separator, start);
        }

        items.add(line.substring(start, line.length()));

        return items;
    }

    static int countWords(String str)
    {
        if (str.equals("") || str == null) {
            return 0;
        }
        int numberSpaces = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                numberSpaces++;
            }
        }

        return ++numberSpaces;
    }

    static String getFirstNWords(String str, int n)
    {
        if (n == 0) {
            return "";
        }

        int pos = str.indexOf(" ");
        int count = 0;

        //indexOf returns -1 when not found
        while (++count < n && pos != -1) {
            pos = str.indexOf(" ", pos + 1);
        }

        if (count < n) {
            return str;
        } else {
            return str.substring(0, pos);
        }
    }

    static String getLastNWords(String str, int n)
    {
        if (n == 0) {
            return "";
        }

        int pos = str.lastIndexOf(" ");
        int count = 0;

        //indexOf returns -1 when not found
        while (++count < n && pos != -1) {
            pos = str.lastIndexOf(" ", pos - 1);
        }

        if (count < n) {
            return str;
        } else {
            return str.substring(pos + 1, str.length());
        }
    }

}
