/**
(c) Copyright 2008 Hewlett-Packard Development Company, L.P.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.*/

package com.hp.gagawa.builder.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class JavaBuilder {

	public static void main(String[] args) {
		String myTag = null;
		if(args.length > 0){
			myTag = args[0];
		}
		
		DOMParser parser = new DOMParser();
		try {
			File xml = new File("builder/com/hp/gagawa/builder/tags.xml");
			parser.parse(xml.getCanonicalPath());
			Document doc = parser.getDocument();
			
			NodeList nodes = doc.getElementsByTagName("tag");
			int ilen = nodes.getLength();
			for(int i = 0; i < ilen; i++){
				Node node = nodes.item(i);
				NamedNodeMap nnm = node.getAttributes();
				String name = "";
				boolean allowKids = false;
				if(nnm != null){
					name = nnm.getNamedItem("name").getNodeValue();
					allowKids = nnm.getNamedItem("allowsKids") == null;
				}
				ArrayList<String> req = new ArrayList<String>();
				ArrayList<String> opt = new ArrayList<String>();
				NodeList kids = node.getChildNodes();
				int jlen = kids.getLength();
				NodeList attrs = null;
				for(int j = 0; j < jlen; j++){
					if(kids.item(j).getNodeName().equals("attributes")){
						attrs = kids.item(j).getChildNodes();
						break;
					}
				}
				if(attrs != null){
					jlen = attrs.getLength();
					for(int j = 0; j < jlen; j++){
						Node attr = attrs.item(j);
						NamedNodeMap am = attr.getAttributes();
						if(am != null){
							String nodeValue = attr.getFirstChild().getNodeValue();
							if(am.getNamedItem("required") != null){
								req.add(nodeValue);
							}else{
								opt.add(nodeValue);
							}
						}
					}
				}
				if(myTag == null || myTag.equalsIgnoreCase(name)){
					new Tag(name, allowKids, req, opt).writeTagClass();
				}
			}
			
			
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Complete");
		System.exit(0);
	}
}
