/* 
 * @(#)Minimal.java        1.0 4/7/12
 *
 * This file has been auto-generated by JPyang, the Java output format plug-in
 * of pyang. Origin: module "minimal", revision: "unknown".
 */

package gen;

import com.tailf.confm.*;
import com.tailf.inm.*;
import java.util.Hashtable;

/**
 * The root class for namespace http://exampleCom/ns/minimal/10 (accessible from 
 * Minimal.NAMESPACE) with prefix "minimal" (Minimal.PREFIX).
 *
 * @version    1.0 2012-7-4
 * @author    Auto Generated
 */
public class Minimal {

    public static final String NAMESPACE = "http://exampleCom/ns/minimal/10";

    public static final String PREFIX = "minimal";
    
    /**
     * Enable the elements in this namespace to be aware
     * of the data model and use the generated classes.
     */
    public static void enable() throws INMException {
        Container.setPackage(NAMESPACE, PREFIX);
        Minimal.registerSchema();
    }

    /**
     * Register the schema for this namespace in the global
     * schema table (CsTree) making it possible to lookup
     * CsNode entries for all tagpaths
     */
    public static void registerSchema() throws INMException {
        java.net.URL schemaUrl = ClassLoader.getSystemResource("Minimal.schema");
        SchemaParser parser = new SchemaParser();
        Hashtable h = CsTree.create(NAMESPACE);
        if (schemaUrl == null)
            parser.readFile("Minimal.schema", h);
        else
            parser.readFile(schemaUrl, h);
    }

}