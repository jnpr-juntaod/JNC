/* 
 * @(#)IpPrefix.java        1.0 06/05/15
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-inet-types", revision: "2013-07-15".
 */

package net.juniper.yang.mo.ietfInetTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangUnion;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "/Users/juntao/workspace/js-easy-rest/examples/pet-store/server/target/scala-2.11/src_managed/main/net/juniper/yang/mo/ietfInetTypes/ip-prefix"
 * <p>
 * See line 288 in
 * /Users/juntao/3rdParty/pyang-1.5/modules/ietf-inet-types.yang
 *
 * @version 1.0 2015-05-06
 * @author Auto Generated
 */
public class IpPrefix extends YangUnion {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for IpPrefix object from a string.
     * @param value Value to construct the IpPrefix from.
     */
    public IpPrefix(String value) throws YangException {
        super(value,
            new String[] {
                "net.juniper.yang.mo.ietfInetTypes.Ipv4Prefix",
                "net.juniper.yang.mo.ietfInetTypes.Ipv6Prefix",
            }
        );
        check();
    }

    /**
     * Sets the value using a string value.
     * @param value The value to set.
     */
    public void setValue(String value) throws YangException {
        super.setValue(value);
        check();
    }

    /**
     * Checks all restrictions (if any).
     */
    public void check() throws YangException {
    }

}
