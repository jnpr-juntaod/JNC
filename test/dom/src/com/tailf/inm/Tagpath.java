/*    -*- Java -*-
 *
 *  Copyright 2009 Tail-F Systems AB. All rights reserved.
 *
 *  This software is the confidential and proprietary
 *  information of Tail-F Systems AB.
 *
 *  $Id$
 *
 */

package com.tailf.inm;

import java.util.*;

/**
 * The Tagpath class is used to represent the name of individual schema nodes.
 * Each ConfM CsNode is identified by a Tagpath. This class really belongs to
 * the ConfM package, but resides here nevertheless.
 */

public class Tagpath {
    public String[] p;

    public Tagpath(int size) {
        p = new String[size];
    }

    public Tagpath(String[] tp) {
        p = tp;
    }

    public Tagpath(String s) {
        String[] tags = s.split("/");
        p = new String[tags.length];
        for (int i = 0; i < tags.length; i++)
            p[i] = new String(tags[i]);
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < p.length; i++) {
            ret += p[i];
            if (i != p.length - 1)
                ret += "/";
        }
        return ret;
    }

    public int hashCode() {
        int h = 0;
        for (int i = 0; i < p.length; i++) {
            h += p[i].hashCode();
        }
        return h;
    }

    public boolean equals(Object o) {
        if ((o instanceof Tagpath)) {
            Tagpath tp = (Tagpath) o;
            if (tp.p.length == p.length) {
                for (int i = 0; i < tp.p.length; i++) {
                    if (!tp.p[i].equals(p[i]))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

}
