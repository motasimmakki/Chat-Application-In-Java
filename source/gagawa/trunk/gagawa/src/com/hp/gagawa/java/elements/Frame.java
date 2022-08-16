/**Generated by the Gagawa TagBuilder Fri Jan 30 21:29:45 PST 2009*/

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

package com.hp.gagawa.java.elements;

import com.hp.gagawa.java.FertileNode;

import com.hp.gagawa.java.Node;
import com.hp.gagawa.java.elements.Text;
import java.util.List;

public class Frame extends FertileNode {

	public Frame(){
		super("frame");
	}


	/**
	 * Appends a child node to the end of this element's DOM tree
	 * @param child node to be appended
	 * @return the node
	 */
	public Frame appendChild(Node child){
		if(this == child){
			throw new Error("Cannot append a node to itself.");
		}
		child.setParent(this);
		children.add(child);
		return this;
	}
	/**
	 * Appends a child node at the given index
	 * @param index insert point
	 * @param child node to be appended
	 * @return the node
	 */
	public Frame appendChild(int index, Node child){
		if(this == child){
			throw new Error("Cannot append a node to itself.");
		}
		child.setParent(this);
		children.add(index, child);
		return this;
	}
	/**
	 * Appends a list of children in the order given in the list
	 * @param children nodes to be appended
	 * @return the node
	 */
	public Frame appendChild(List<Node> children){
		if(children != null){;
			for(Node child: children){
				appendChild(child);
			}
		}
		return this;
	}
	/**
	 * Appends the given children in the order given
	 * @param children nodes to be appended
	 * @return the node
	 */
	public Frame appendChild(Node... children){
		for(int i = 0; i < children.length; i++){
			appendChild(children[i]);
		}
		return this;
	}
	/**
	 * Convenience method which appends a text node to this element
	 * @param text the text to be appended
	 * @return the node
	 */
	public Frame appendText(String text){
		return appendChild(new Text(text));
	}
	/**
	 * Removes the child node
	 * @param child node to be removed
	 * @return the node
	 */
	public Frame removeChild(Node child){
		children.remove(child);
		return this;
	}
	/**
	 * Removes all child nodes
	 * @return the node
	 */
	public Frame removeChildren(){
		children.clear();
		return this;
	}
	public Frame setFrameborder(String value){setAttribute("frameborder", value); return this;}
	public String getFrameborder(){return getAttribute("frameborder");}
	public boolean removeFrameborder(){return removeAttribute("frameborder");}

	public Frame setLongdesc(String value){setAttribute("longdesc", value); return this;}
	public String getLongdesc(){return getAttribute("longdesc");}
	public boolean removeLongdesc(){return removeAttribute("longdesc");}

	public Frame setMarginheight(String value){setAttribute("marginheight", value); return this;}
	public String getMarginheight(){return getAttribute("marginheight");}
	public boolean removeMarginheight(){return removeAttribute("marginheight");}

	public Frame setMarginwidth(String value){setAttribute("marginwidth", value); return this;}
	public String getMarginwidth(){return getAttribute("marginwidth");}
	public boolean removeMarginwidth(){return removeAttribute("marginwidth");}

	public Frame setName(String value){setAttribute("name", value); return this;}
	public String getName(){return getAttribute("name");}
	public boolean removeName(){return removeAttribute("name");}

	public Frame setNoresize(String value){setAttribute("noresize", value); return this;}
	public String getNoresize(){return getAttribute("noresize");}
	public boolean removeNoresize(){return removeAttribute("noresize");}

	public Frame setScrolling(String value){setAttribute("scrolling", value); return this;}
	public String getScrolling(){return getAttribute("scrolling");}
	public boolean removeScrolling(){return removeAttribute("scrolling");}

	public Frame setSrc(String value){setAttribute("src", value); return this;}
	public String getSrc(){return getAttribute("src");}
	public boolean removeSrc(){return removeAttribute("src");}

	public Frame setId(String value){setAttribute("id", value); return this;}
	public String getId(){return getAttribute("id");}
	public boolean removeId(){return removeAttribute("id");}

	public Frame setCSSClass(String value){setAttribute("class", value); return this;}
	public String getCSSClass(){return getAttribute("class");}
	public boolean removeCSSClass(){return removeAttribute("class");}

	public Frame setTitle(String value){setAttribute("title", value); return this;}
	public String getTitle(){return getAttribute("title");}
	public boolean removeTitle(){return removeAttribute("title");}

	public Frame setStyle(String value){setAttribute("style", value); return this;}
	public String getStyle(){return getAttribute("style");}
	public boolean removeStyle(){return removeAttribute("style");}

}
