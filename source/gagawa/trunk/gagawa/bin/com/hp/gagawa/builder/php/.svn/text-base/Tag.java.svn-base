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
THE SOFTWARE.
*/

package com.hp.gagawa.builder.php;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Tag {
	
	private String name_;
	private String capTag_;
	private boolean allowsKids_;
	private ArrayList<String> req_attrs_;
	private ArrayList<String> opt_attrs_;
	private static HashMap<String, String> specialCases_;
	
	private File output_;
	
	public Tag(String tag, boolean allowsKids, ArrayList<String> req, ArrayList<String> opt){
		
		this.output_ = new File("src/com/hp/gagawa/php/Gagawa.php");
		
		if(specialCases_ == null){
			initSpecialCases();
		}
		
		this.name_ = tag;
		this.allowsKids_ = allowsKids;
		this.req_attrs_ = req;
		this.opt_attrs_ = opt;
		
		capTag_ = specialCases_.get(name_);
		
		if(capTag_ == null){
			capTag_ = capitalize(name_);
		}
		
	}
	
	public void writeTagClass() throws IOException {
		
		try {
			
			System.out.println( "Generating PHP class " + name_ );
			
			// The 2nd argument to the FileWriter constructor (true)
			// tells the FileWriter to append to the end of the Elements.php
			// file instead of creating a new one for each tag.
			PrintWriter out = new PrintWriter( new FileWriter( this.output_, true ) );
			
			out.println();
			out.println(String.format("class %s extends %s {",capTag_, (allowsKids_)?"FertileNode":"Node"));
			out.println();
			out.println(writeConstructor());
			out.println();
			
			if(!allowsKids_){
				out.println(writeWriteMethod());
				out.println();
			}
			
			if(req_attrs_.size() > 0){
				for(String attr: req_attrs_){
					out.println(writeAttributeMethods(attr));
				}
			}
			if(opt_attrs_.size() > 0){
				for(String attr: opt_attrs_){
					out.println(writeAttributeMethods(attr));
				}
			}
			out.println("}\n");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String writeConstructor(){
		StringBuffer b = new StringBuffer();
		b.append("\tpublic function __construct");
		b.append("(");
		//write params
		int len = req_attrs_.size();
		int i;
		if(len > 0){
			for(i = 0; i < len-1; i++){
				String attr = req_attrs_.get(i);
				if(specialCases_.get(attr) != null){
					attr = specialCases_.get(attr);
				} 
				b.append("$" + attr + ", ");
			}
			String attr = req_attrs_.get(i);
			if(specialCases_.get(attr) != null){
				attr = specialCases_.get(attr);
			} 
			b.append("$" + attr);
		}
		b.append("){\n");
		b.append(String.format("\t\tparent::__construct(\"%s\");\n",name_));
		//write setters
		if(len > 0){
			for(i = 0; i < len; i++){
				String attr = req_attrs_.get(i);
				if(specialCases_.get(attr) != null){
					attr = specialCases_.get(attr);
				} 
				b.append("\t\t$this->set");
				b.append(capitalize(attr));
				b.append("( $");
				b.append(attr);
				b.append(" );\n");
			}
		}
		b.append("\t}");
		return b.toString();
	}
	
	private String writeWriteMethod(){
		StringBuilder b = new StringBuilder();
		b.append("\t/* @Override */\n");
		b.append("\tpublic function write(){\n");
		b.append("\t\treturn $this->writeOpen();\n");
		b.append("\t}\n");
		return b.toString();
	}
	
	private String writeAttributeMethods(String attr) {
		StringBuilder b = new StringBuilder();
		String attrName = specialCases_.get(attr);
		if(attrName == null){
			attrName = capitalize(attr);
		}
		b.append(String.format("\tpublic function set%s( $value ){ $this->setAttribute( \"%s\", $value ); return $this; }\n", attrName, attr));
		b.append(String.format("\tpublic function get%s(){ return $this->getAttribute( \"%s\" ); }\n", attrName, attr));
		b.append(String.format("\tpublic function remove%s(){ return $this->removeAttribute( \"%s\" ); }\n", attrName, attr));
		return b.toString();
	}
	
	private static String capitalize(String token){
		return token.substring(0,1).toUpperCase() + token.substring(1).toLowerCase();
	}
		
	private static void initSpecialCases(){
		specialCases_ = new HashMap<String, String>();
		specialCases_.put("class", "CSSClass");
		specialCases_.put("xml:lang", "XMLLang");
		specialCases_.put("http-equiv", "HttpEquiv");
		specialCases_.put("accept-charset", "AcceptCharset");
		specialCases_.put("!--", "Comment");
		specialCases_.put("!DOCTYPE", "Doctype");
	}

}
