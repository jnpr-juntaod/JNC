/**
 *  Copyright 2007 Tail-F Systems AB. All rights reserved.
 *
 *  This software is the confidential and proprietary
 *  information of Tail-F Systems AB.
 *
 *  $Id$
 *
 */
package com.tailf.inm;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.Hashtable;

/**
 * A simple SAX parser, for parsing NETCONF messages, into a simple
 * {@link Element} tree.
 * 
 */
public class XMLParser {

    /** the parser implementation */
    protected XMLReader parser;

    /**
     * Constructor. Initializes the parser instance.
     */
    public XMLParser() throws INMException {
        try {
            String javaVersion = System.getProperty("java.version");
            if (javaVersion.startsWith("1.4")) {
                parser = XMLReaderFactory
                        .createXMLReader("org.apache.crimson.parser.XMLReaderImpl");
            } else {
                parser = XMLReaderFactory.createXMLReader();
            }
        } catch (Exception e) {
            throw new INMException(INMException.PARSER_ERROR,
                    "failed to initialize parser: " + e);
        }

    }

    /**
     * The handler with hooks for startElement etc. The SAX parser will build up
     * the parse tree, by calling these hooks.
     */
    private class ConfHandler extends DefaultHandler {

        // pointer to current element (node)
        public Element current;
        public Element top;
        public PrefixMap prefixes = null;

        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            Element parent = current;
            Element child = new Element(uri, localName);
            child.prefixes = prefixes;
            prefixes = null;

            // add other attributes
            for (int i = 0; i < attributes.getLength(); i++) {
                String attrName = attributes.getLocalName(i);
                // String attrType= attributes.getType(i);
                String attrUri = attributes.getURI(i);
                String attrValue = attributes.getValue(i);
                Attribute attr = new Attribute(attrUri, attrName, attrValue);
                // System.out.println("ATTRIBUTE: "+attributes.getQName(i)+
                // "  URI="+attributes.getURI(i));
                trace("add attr: " + attr);
                child.addAttr(attr);
            }
            if (current == null) {
                trace("add to top: " + child);
                top = child;
            } else {
                current.addChild(child);
                trace("add child: " + child);
            }
            current = child; // step down
        }

        public void endElement(String uri, String localName, String qName) {
            // check that we don't have mixed content
            if (current.hasChildren() && current.value != null) {
                // MIXED content not allowed
                current.value = null;
            }
            // step up
            current = current.getParent();
        }

        public void characters(char[] ch, int start, int length) {
            if (current.value == null)
                current.value = new String();
            current.value = current.value + new String(ch, start, length);
        }

        public void startPrefixMapping(String prefix, String uri) {
            trace("startPrefixMapping: uri=\"" + uri + "\" prefix=" + prefix);
            if (prefixes == null)
                prefixes = new PrefixMap();
            prefixes.add(new Prefix(prefix, uri));
            trace("added prefixmapping: " + prefix);
        }
    }

    /**
     * Read in an XML file and parse it and return an element tree.
     */
    public Element readFile(String filename) throws INMException {
        try {
            ConfHandler handler = new ConfHandler();
            parser.setContentHandler(handler);
            parser.parse(filename);
            return handler.top;
            // } catch (INMException e) {
            // throw e;
        } catch (Exception e) {
            throw new INMException(INMException.PARSER_ERROR, "parse file: "
                    + filename + " error: " + e);
        }
    }

    /**
     * Parses an XML string returning an element tree from it.
     * 
     * @param is
     *            Inputsource (byte stream) where the XML text is read from
     */
    public Element parse(InputSource is) throws INMException {
        try {
            ConfHandler handler = new ConfHandler();
            parser.setContentHandler(handler);
            parser.parse(is);
            return handler.top;
            // } catch (INMException e) {
            // throw e;
        } catch (Exception e) {
            throw new INMException(INMException.PARSER_ERROR, "parse error: "
                    + e);
        }
    }

    /**
     * Parses an XML String, returning a Element tree representing the XML
     * structure.
     * 
     * @param str
     *            String containing the XML text to parse
     */
    public Element parse(String str) throws INMException {
        ByteArrayInputStream istream = new ByteArrayInputStream(str.getBytes());
        InputSource is = new InputSource(istream);
        return parse(is);
    }

    /**
     * trace
     */
    protected void trace(String s) {
        if (Element.debugLevel >= Element.DEBUG_LEVEL_PARSER)
            System.err.println("*XMLParser: " + s);
    }
}
