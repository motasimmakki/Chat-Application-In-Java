/**
(c) Copyright 2009 Hewlett-Packard Development Company, L.P.

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
THE SOFTWARE.
*/

package com.hp.gagawa.builder.php;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * The PHPBuilder builds the PHP class for each tag.
 * @author kolichko
 *
 */
public class PHPBuilder {
	
	private static File gagawaPHP = new File( "src/com/hp/gagawa/php/Gagawa.php" );
	private static final String PHP_START = "<?php";
	private static final String PHP_END = "?>";
	
	public static void main ( String [] args ) {
		
		String myTag = null;
		
		if ( args.length > 0 ) {
			myTag = args[0];
		}
		
		DOMParser parser = new DOMParser();
		
		try {
			
			if( gagawaPHP.exists() ) {
				if ( gagawaPHP.delete() ) {
					System.out.println( "Deleted existing Gagawa.php library at " +
										gagawaPHP.getCanonicalPath() );
				}
			}
			
			// Start out the master Gagawa.php file with a <?php declaration.
			append( gagawaPHP, PHP_START + "\n" );
			
			// Copy the contents of node/*.php to Gagawa.php.
			copyToPHP( new File( "src/com/hp/gagawa/php/util/GagawaUtil.php" ), gagawaPHP );
			copyToPHP( new File( "src/com/hp/gagawa/php/nodes/Attribute.php" ), gagawaPHP );
			copyToPHP( new File( "src/com/hp/gagawa/php/nodes/Node.php" ), gagawaPHP );
			copyToPHP( new File( "src/com/hp/gagawa/php/nodes/FertileNode.php" ), gagawaPHP );
			
			File xml = new File("builder/com/hp/gagawa/builder/tags.xml");
			parser.parse(xml.getCanonicalPath());
			Document doc = parser.getDocument();
			
			NodeList nodes = doc.getElementsByTagName("tag");
			
			for(int i = 0; i < nodes.getLength(); i++){
				
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
			
			// End the master Gagawa.php file with a ?> declaration.
			append( gagawaPHP, PHP_END );
						
		} catch (SAXException e1) {
			e1.printStackTrace( System.err );
		} catch (IOException e1) {
			e1.printStackTrace( System.err );
		}
		System.out.println("Complete!");
		System.exit(0);
	}
	
	
	private static void copyToPHP ( File from, File to )
			throws IOException {
		
		String line;
		BufferedReader reader = new BufferedReader( new FileReader( from ) );
		PrintWriter writer = new PrintWriter( new FileWriter( to, true ) );
		
		while ( ( line = reader.readLine() ) != null ) {
			
			// Skip lines that start with <?php and ?>
			if( line.startsWith(PHP_START) ||
					line.startsWith(PHP_END) ) {
				continue;
			}
			
			writer.write( line + "\n" );
			
		} /* while */
		
		reader.close();
		writer.close();
		
	}
	
	
	private static void append ( File file, String toAppend )
			throws IOException {
		
		PrintWriter writer = new PrintWriter( new FileWriter( file, true ) );
		writer.write( toAppend );
		writer.close();
		
	}


}


