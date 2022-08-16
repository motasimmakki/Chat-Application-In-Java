<?php

/*
 * (c) Copyright 2009 Hewlett-Packard Development Company, L.P.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * http://code.google.com/p/gagawa/
 *
 * VERSION:
 *   1.1-beta
 * 
 * AUTHORS:
 *   Mark Kolich
 *   Chris Friedrich
 * 
 */
 
class GagawaUtil {

	/**
	 * Checks if a variable is really "empty".  Code borrowed from PHP.net at
	 * http://us3.php.net/manual/en/function.empty.php#90767 because we were
	 * previously using empty() to see if a variable is empty or not.  But
	 * empty() dosen't work for attributes that have a value of "0", so we need
	 * something more robust here.
	 *
	 *   an unset variable -> empty
	 *   null -> empty
	 *   0 -> NOT empty
	 *   "0" -> NOT empty
	 *   false -> empty
	 *   true -> NOT empty
	 *   'string value' -> NOT empty
	 *   "    " (white space) -> empty
	 *   array() (empty array) -> empty
	 *
	 * There are two optional parameters:
	 *
	 *   allow_false: setting this to true will make the function consider a
	 *   boolean value of false as NOT empty. This parameter is false by default.
	 *
	 *   allow_ws: setting this to true will make the function consider a string
	 *   with nothing but white space as NOT empty. This parameter is false by
	 *   default.
	 */
	public static function gagawaIsEmpty ( $var, $allow_false = false,
											$allow_ws = false ) {
	
	    if ( !isset($var) || is_null($var) ||
	    	 ($allow_ws == false && !is_object($var) && trim($var) == "" && !is_bool($var)) ||
	    	 ($allow_false === false && is_bool($var) && $var === false) ||
	    	 (is_array($var) && empty($var)) ) {   
	        return true;
	    }
	    else {
	        return false;
	    }
	     
 	}
 	
}


class Attribute {

	private $name_;
	private $value_;

	public function __construct ( $name = NULL, $value = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($name) ||
			GagawaUtil::gagawaIsEmpty($value)){
			throw new Exception( "Attributes must have a name " .
						"and a value!" );
		}

		$this->name_ = $name;
		$this->value_ = $value;

	}

	public function write ( ) {
		return " " . $this->name_ .
			"=\"" . $this->value_ .
			"\"";
	}

	public function getName ( ) {
		return $this->name_;
	}

	public function setName ( $name = NULL ) {
		
		if(GagawaUtil::gagawaIsEmpty($name)){
			throw new Exception( "Attribute names cannot be " .
									"empty!" );
		}
		
		$this->name_ = $name;
		
	}

	public function getValue ( ) {
		return $this->value_;
	}

	public function setValue ( $value ) {
		
		if(GagawaUtil::gagawaIsEmpty($value)){
			throw new Exception( "Attribute values cannot be " .
									"empty!" );
		}
		
		$this->value_ = $value;
		
	}

} /* Attribute */


class Node {

	protected $tag_;
	protected $attributes_;
	protected $parent_;

	/**
	 * This Node constructor can only be called from
	 * classes that extend Node.
	 */
	protected function __construct ( $tag = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($tag)){
			throw new Exception( "Node's must have a tag " .
						"type!" );
		}

		$this->parent_ = NULL;
		$this->tag_ = $tag;
		$this->attributes_ = array();

	}

	/**
	 * Returns the parent node of this node, if
	 * a parent exists.  If no parent exists,
	 * this function returns NULL.
	 */
	public function getParent ( ) {
		return $this->parent_;
	}

	/**
	 * Sets the parent of this Node.  Note that this
	 * function is protected and can only be called by
	 * classes that extend Node.  The parent cannot
	 * be NULL; this function will throw an Exception
	 * if the parent node is empty.
	 */
	protected function setParent ( $parent = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($parent)){
			throw new Exception( "Parent cannot be NULL!" );
		}

		$this->parent_ = $parent;

	}

	/**
	 * Given a name and value pair, sets an attribute on this Node.
	 * The name and value cannot be empty; if so, this function
	 * will throw an Exception.  Note if the attribute already exists
	 * and the caller wants to set an attribute of the same name,
	 * this function will not create a new Attribute, but rather
	 * update the value of the existing named attribute.
	 */
	public function setAttribute ( $name = NULL, $value = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($name) ||
			GagawaUtil::gagawaIsEmpty($value)){
			throw new Exception("Attributes must have " .
						"a name and a value!");

		}

		foreach ( $this->attributes_ as $attribute ) {
			if($attribute->getName()===$name){
				$attribute->setValue( $value );
				return;
			}
		}

		$this->attributes_[] = new Attribute( $name, $value );
		return $this;

	}

	/**
	 * Fetch and attribute by name from this Node.  The attribute
	 * name cannot be NULL; if so, this function will throw an
	 * Exception.
	 */
	public function getAttribute ( $name = NULL ) {

		$returnAttr = NULL;

		if(GagawaUtil::gagawaIsEmpty($name)){
			throw new Exception("Attribute name cannot " .
						"be empty!");
		}

		foreach ( $this->attributes_ as $attribute ) {
			if($attribute->getName()===$name){
				$returnAttr = $attribute->getValue();
				break;
			}
		}

		return $returnAttr;

	}

	/**
	 * Removes an attribute from this Node, by name.  The name
	 * of the attribute to remove cannot be NULL; if so, this
	 * function will throw an Exception.
	 */
	public function removeAttribute ( $name = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($name)){
			throw new Exception("Attribute name cannot " .
						"be empty!");
		}

		for ( $i = 0; $i < count($this->attributes_); $i++ ) {
			$attribute = $this->attributes_[$i];
			if($attribute->getName()===$name){
				unset( $this->attributes_[$i] );
				return true;
			}
		}

		// Couldn't find the attribute, so
		// it wasn't removed.
		return false;

	}

	public function write ( ) {
		$buffer = $this->writeOpen();
		$buffer .= $this->writeClose();
		return $buffer;
	}

	protected function writeOpen ( ) {
		$buffer = "<";
		$buffer .= $this->tag_;
		foreach ( $this->attributes_ as $attribute ) {
			$buffer .= $attribute->write(); 
		}
		$buffer .= ">";
		return $buffer;
	}

	protected function writeClose ( ) {
		return "</" . $this->tag_ . ">";
	}

} /* Node */


class FertileNode extends Node {

	private $children_;

	/**
	 * Create a new FertileNode with the given tag.  The
	 * tag cannot be NULL.
	 */
	public function __construct ( $tag = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($tag)){
			throw new Exception( "FertileNode's must have a tag " .
						"type!" );
		}

		parent::__construct( $tag );
		$this->children_ = array();

	}

	/**
	 * Add's a child to this FertileNode.  The child to
	 * add cannot be null.
	 */	
	public function appendChild ( $childNode = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($childNode)){
			throw new Exception( "You cannot append an empty " .
						"child node!" );
		}

		$childNode->setParent( $this );
		$this->children_[] = $childNode;
		return $this;
		
	}

	/**
	 * Removes the first instance of child from this
	 * FertileNode.  Once the first instance of the child
	 * is removed, this function will return.  It returns
	 * true if a child was removed and false if no child
	 * was removed.
	 */
	public function removeChild ( $childNode = NULL ) {

		if(GagawaUtil::gagawaIsEmpty($childNode)){
			throw new Exception( "You cannot remove an empty " .
						"child node!" );
		}

		for ( $i = 0; $i < count($this->children_); $i++ ) {
			$child = $this->children_[$i];
			if($child===$childNode){
				unset( $this->children_[$i] );
				return true;
			}
		}

		return false;

	}

	/**
	 * Removes all children attached to this FertileNode.
	 */
	public function removeChildren ( ) {
		unset( $this->children_ );
		$this->children_ = array();
	}

	/**
	 * Returns an array of all children attached to
	 * this FertileNode.
	 */
	public function getChildren ( ) {
		return $this->children_;
	}

	/**
	 * Gets a child of this FertileNode at given
	 * index.  If no index is passed in, getChild()
	 * will return the child at index zero (0).
	 */
	public function getChild ( $index = 0 ) {
		return $this->children_[$index];
	}

	/* @Override */
	public function write ( ) {
		
		$buffer = $this->writeOpen();		
		foreach ( $this->children_ as $child ) {
			$buffer .= $child->write();		
		}		
		$buffer .= $this->writeClose();
		
		return $buffer;
	
	}

} /* FertileNode */


class Comment extends FertileNode {

	public function __construct(){
		parent::__construct("!--");
	}

}


class Doctype extends Node {

	public function __construct(){
		parent::__construct("!DOCTYPE");
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


}


class A extends FertileNode {

	public function __construct(){
		parent::__construct("a");
	}

	public function setCharset( $value ){ $this->setAttribute( "charset", $value ); return $this; }
	public function getCharset(){ return $this->getAttribute( "charset" ); }
	public function removeCharset(){ return $this->removeAttribute( "charset" ); }

	public function setCoords( $value ){ $this->setAttribute( "coords", $value ); return $this; }
	public function getCoords(){ return $this->getAttribute( "coords" ); }
	public function removeCoords(){ return $this->removeAttribute( "coords" ); }

	public function setHref( $value ){ $this->setAttribute( "href", $value ); return $this; }
	public function getHref(){ return $this->getAttribute( "href" ); }
	public function removeHref(){ return $this->removeAttribute( "href" ); }

	public function setHreflang( $value ){ $this->setAttribute( "hreflang", $value ); return $this; }
	public function getHreflang(){ return $this->getAttribute( "hreflang" ); }
	public function removeHreflang(){ return $this->removeAttribute( "hreflang" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setRel( $value ){ $this->setAttribute( "rel", $value ); return $this; }
	public function getRel(){ return $this->getAttribute( "rel" ); }
	public function removeRel(){ return $this->removeAttribute( "rel" ); }

	public function setRev( $value ){ $this->setAttribute( "rev", $value ); return $this; }
	public function getRev(){ return $this->getAttribute( "rev" ); }
	public function removeRev(){ return $this->removeAttribute( "rev" ); }

	public function setShape( $value ){ $this->setAttribute( "shape", $value ); return $this; }
	public function getShape(){ return $this->getAttribute( "shape" ); }
	public function removeShape(){ return $this->removeAttribute( "shape" ); }

	public function setTarget( $value ){ $this->setAttribute( "target", $value ); return $this; }
	public function getTarget(){ return $this->getAttribute( "target" ); }
	public function removeTarget(){ return $this->removeAttribute( "target" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setTabindex( $value ){ $this->setAttribute( "tabindex", $value ); return $this; }
	public function getTabindex(){ return $this->getAttribute( "tabindex" ); }
	public function removeTabindex(){ return $this->removeAttribute( "tabindex" ); }

	public function setAccesskey( $value ){ $this->setAttribute( "accesskey", $value ); return $this; }
	public function getAccesskey(){ return $this->getAttribute( "accesskey" ); }
	public function removeAccesskey(){ return $this->removeAttribute( "accesskey" ); }

}


class Abbr extends FertileNode {

	public function __construct(){
		parent::__construct("abbr");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Acronym extends FertileNode {

	public function __construct(){
		parent::__construct("acronym");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Address extends FertileNode {

	public function __construct(){
		parent::__construct("address");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Area extends FertileNode {

	public function __construct($alt){
		parent::__construct("area");
		$this->setAlt( $alt );
	}

	public function setAlt( $value ){ $this->setAttribute( "alt", $value ); return $this; }
	public function getAlt(){ return $this->getAttribute( "alt" ); }
	public function removeAlt(){ return $this->removeAttribute( "alt" ); }

	public function setCoords( $value ){ $this->setAttribute( "coords", $value ); return $this; }
	public function getCoords(){ return $this->getAttribute( "coords" ); }
	public function removeCoords(){ return $this->removeAttribute( "coords" ); }

	public function setHref( $value ){ $this->setAttribute( "href", $value ); return $this; }
	public function getHref(){ return $this->getAttribute( "href" ); }
	public function removeHref(){ return $this->removeAttribute( "href" ); }

	public function setNohref( $value ){ $this->setAttribute( "nohref", $value ); return $this; }
	public function getNohref(){ return $this->getAttribute( "nohref" ); }
	public function removeNohref(){ return $this->removeAttribute( "nohref" ); }

	public function setShape( $value ){ $this->setAttribute( "shape", $value ); return $this; }
	public function getShape(){ return $this->getAttribute( "shape" ); }
	public function removeShape(){ return $this->removeAttribute( "shape" ); }

	public function setTarget( $value ){ $this->setAttribute( "target", $value ); return $this; }
	public function getTarget(){ return $this->getAttribute( "target" ); }
	public function removeTarget(){ return $this->removeAttribute( "target" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setTabindex( $value ){ $this->setAttribute( "tabindex", $value ); return $this; }
	public function getTabindex(){ return $this->getAttribute( "tabindex" ); }
	public function removeTabindex(){ return $this->removeAttribute( "tabindex" ); }

	public function setAccesskey( $value ){ $this->setAttribute( "accesskey", $value ); return $this; }
	public function getAccesskey(){ return $this->getAttribute( "accesskey" ); }
	public function removeAccesskey(){ return $this->removeAttribute( "accesskey" ); }

}


class B extends FertileNode {

	public function __construct(){
		parent::__construct("b");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

}


class Base extends FertileNode {

	public function __construct($href){
		parent::__construct("base");
		$this->setHref( $href );
	}

	public function setHref( $value ){ $this->setAttribute( "href", $value ); return $this; }
	public function getHref(){ return $this->getAttribute( "href" ); }
	public function removeHref(){ return $this->removeAttribute( "href" ); }

	public function setTarget( $value ){ $this->setAttribute( "target", $value ); return $this; }
	public function getTarget(){ return $this->getAttribute( "target" ); }
	public function removeTarget(){ return $this->removeAttribute( "target" ); }

}


class Bdo extends FertileNode {

	public function __construct($dir){
		parent::__construct("bdo");
		$this->setDir( $dir );
	}

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Big extends FertileNode {

	public function __construct(){
		parent::__construct("big");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

}


class Blockquote extends FertileNode {

	public function __construct(){
		parent::__construct("blockquote");
	}

	public function setCite( $value ){ $this->setAttribute( "cite", $value ); return $this; }
	public function getCite(){ return $this->getAttribute( "cite" ); }
	public function removeCite(){ return $this->removeAttribute( "cite" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Body extends FertileNode {

	public function __construct(){
		parent::__construct("body");
	}

	public function setAlink( $value ){ $this->setAttribute( "alink", $value ); return $this; }
	public function getAlink(){ return $this->getAttribute( "alink" ); }
	public function removeAlink(){ return $this->removeAttribute( "alink" ); }

	public function setBackground( $value ){ $this->setAttribute( "background", $value ); return $this; }
	public function getBackground(){ return $this->getAttribute( "background" ); }
	public function removeBackground(){ return $this->removeAttribute( "background" ); }

	public function setBgcolor( $value ){ $this->setAttribute( "bgcolor", $value ); return $this; }
	public function getBgcolor(){ return $this->getAttribute( "bgcolor" ); }
	public function removeBgcolor(){ return $this->removeAttribute( "bgcolor" ); }

	public function setLink( $value ){ $this->setAttribute( "link", $value ); return $this; }
	public function getLink(){ return $this->getAttribute( "link" ); }
	public function removeLink(){ return $this->removeAttribute( "link" ); }

	public function setText( $value ){ $this->setAttribute( "text", $value ); return $this; }
	public function getText(){ return $this->getAttribute( "text" ); }
	public function removeText(){ return $this->removeAttribute( "text" ); }

	public function setVlink( $value ){ $this->setAttribute( "vlink", $value ); return $this; }
	public function getVlink(){ return $this->getAttribute( "vlink" ); }
	public function removeVlink(){ return $this->removeAttribute( "vlink" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Br extends Node {

	public function __construct(){
		parent::__construct("br");
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

}


class Button extends FertileNode {

	public function __construct(){
		parent::__construct("button");
	}

	public function setDisabled( $value ){ $this->setAttribute( "disabled", $value ); return $this; }
	public function getDisabled(){ return $this->getAttribute( "disabled" ); }
	public function removeDisabled(){ return $this->removeAttribute( "disabled" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setValue( $value ){ $this->setAttribute( "value", $value ); return $this; }
	public function getValue(){ return $this->getAttribute( "value" ); }
	public function removeValue(){ return $this->removeAttribute( "value" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setAccesskey( $value ){ $this->setAttribute( "accesskey", $value ); return $this; }
	public function getAccesskey(){ return $this->getAttribute( "accesskey" ); }
	public function removeAccesskey(){ return $this->removeAttribute( "accesskey" ); }

	public function setTabindex( $value ){ $this->setAttribute( "tabindex", $value ); return $this; }
	public function getTabindex(){ return $this->getAttribute( "tabindex" ); }
	public function removeTabindex(){ return $this->removeAttribute( "tabindex" ); }

}


class Caption extends FertileNode {

	public function __construct(){
		parent::__construct("caption");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Cite extends FertileNode {

	public function __construct(){
		parent::__construct("cite");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Code extends FertileNode {

	public function __construct(){
		parent::__construct("code");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Col extends Node {

	public function __construct(){
		parent::__construct("col");
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setSpan( $value ){ $this->setAttribute( "span", $value ); return $this; }
	public function getSpan(){ return $this->getAttribute( "span" ); }
	public function removeSpan(){ return $this->removeAttribute( "span" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Colgroup extends FertileNode {

	public function __construct(){
		parent::__construct("colgroup");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setSpan( $value ){ $this->setAttribute( "span", $value ); return $this; }
	public function getSpan(){ return $this->getAttribute( "span" ); }
	public function removeSpan(){ return $this->removeAttribute( "span" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Dd extends FertileNode {

	public function __construct(){
		parent::__construct("dd");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Del extends FertileNode {

	public function __construct(){
		parent::__construct("del");
	}

	public function setCite( $value ){ $this->setAttribute( "cite", $value ); return $this; }
	public function getCite(){ return $this->getAttribute( "cite" ); }
	public function removeCite(){ return $this->removeAttribute( "cite" ); }

	public function setDatetime( $value ){ $this->setAttribute( "datetime", $value ); return $this; }
	public function getDatetime(){ return $this->getAttribute( "datetime" ); }
	public function removeDatetime(){ return $this->removeAttribute( "datetime" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Dfn extends FertileNode {

	public function __construct(){
		parent::__construct("dfn");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Dir extends FertileNode {

	public function __construct(){
		parent::__construct("dir");
	}

	public function setCompact( $value ){ $this->setAttribute( "compact", $value ); return $this; }
	public function getCompact(){ return $this->getAttribute( "compact" ); }
	public function removeCompact(){ return $this->removeAttribute( "compact" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Div extends FertileNode {

	public function __construct(){
		parent::__construct("div");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Dl extends FertileNode {

	public function __construct(){
		parent::__construct("dl");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Dt extends FertileNode {

	public function __construct(){
		parent::__construct("dt");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Em extends FertileNode {

	public function __construct(){
		parent::__construct("em");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Fieldset extends FertileNode {

	public function __construct(){
		parent::__construct("fieldset");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Font extends FertileNode {

	public function __construct(){
		parent::__construct("font");
	}

	public function setColor( $value ){ $this->setAttribute( "color", $value ); return $this; }
	public function getColor(){ return $this->getAttribute( "color" ); }
	public function removeColor(){ return $this->removeAttribute( "color" ); }

	public function setFace( $value ){ $this->setAttribute( "face", $value ); return $this; }
	public function getFace(){ return $this->getAttribute( "face" ); }
	public function removeFace(){ return $this->removeAttribute( "face" ); }

	public function setSize( $value ){ $this->setAttribute( "size", $value ); return $this; }
	public function getSize(){ return $this->getAttribute( "size" ); }
	public function removeSize(){ return $this->removeAttribute( "size" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Form extends FertileNode {

	public function __construct($action){
		parent::__construct("form");
		$this->setAction( $action );
	}

	public function setAction( $value ){ $this->setAttribute( "action", $value ); return $this; }
	public function getAction(){ return $this->getAttribute( "action" ); }
	public function removeAction(){ return $this->removeAttribute( "action" ); }

	public function setAccept( $value ){ $this->setAttribute( "accept", $value ); return $this; }
	public function getAccept(){ return $this->getAttribute( "accept" ); }
	public function removeAccept(){ return $this->removeAttribute( "accept" ); }

	public function setAcceptCharset( $value ){ $this->setAttribute( "accept-charset", $value ); return $this; }
	public function getAcceptCharset(){ return $this->getAttribute( "accept-charset" ); }
	public function removeAcceptCharset(){ return $this->removeAttribute( "accept-charset" ); }

	public function setEnctype( $value ){ $this->setAttribute( "enctype", $value ); return $this; }
	public function getEnctype(){ return $this->getAttribute( "enctype" ); }
	public function removeEnctype(){ return $this->removeAttribute( "enctype" ); }

	public function setMethod( $value ){ $this->setAttribute( "method", $value ); return $this; }
	public function getMethod(){ return $this->getAttribute( "method" ); }
	public function removeMethod(){ return $this->removeAttribute( "method" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setTarget( $value ){ $this->setAttribute( "target", $value ); return $this; }
	public function getTarget(){ return $this->getAttribute( "target" ); }
	public function removeTarget(){ return $this->removeAttribute( "target" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Frame extends FertileNode {

	public function __construct(){
		parent::__construct("frame");
	}

	public function setFrameborder( $value ){ $this->setAttribute( "frameborder", $value ); return $this; }
	public function getFrameborder(){ return $this->getAttribute( "frameborder" ); }
	public function removeFrameborder(){ return $this->removeAttribute( "frameborder" ); }

	public function setLongdesc( $value ){ $this->setAttribute( "longdesc", $value ); return $this; }
	public function getLongdesc(){ return $this->getAttribute( "longdesc" ); }
	public function removeLongdesc(){ return $this->removeAttribute( "longdesc" ); }

	public function setMarginheight( $value ){ $this->setAttribute( "marginheight", $value ); return $this; }
	public function getMarginheight(){ return $this->getAttribute( "marginheight" ); }
	public function removeMarginheight(){ return $this->removeAttribute( "marginheight" ); }

	public function setMarginwidth( $value ){ $this->setAttribute( "marginwidth", $value ); return $this; }
	public function getMarginwidth(){ return $this->getAttribute( "marginwidth" ); }
	public function removeMarginwidth(){ return $this->removeAttribute( "marginwidth" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setNoresize( $value ){ $this->setAttribute( "noresize", $value ); return $this; }
	public function getNoresize(){ return $this->getAttribute( "noresize" ); }
	public function removeNoresize(){ return $this->removeAttribute( "noresize" ); }

	public function setScrolling( $value ){ $this->setAttribute( "scrolling", $value ); return $this; }
	public function getScrolling(){ return $this->getAttribute( "scrolling" ); }
	public function removeScrolling(){ return $this->removeAttribute( "scrolling" ); }

	public function setSrc( $value ){ $this->setAttribute( "src", $value ); return $this; }
	public function getSrc(){ return $this->getAttribute( "src" ); }
	public function removeSrc(){ return $this->removeAttribute( "src" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

}


class Frameset extends FertileNode {

	public function __construct(){
		parent::__construct("frameset");
	}

	public function setCols( $value ){ $this->setAttribute( "cols", $value ); return $this; }
	public function getCols(){ return $this->getAttribute( "cols" ); }
	public function removeCols(){ return $this->removeAttribute( "cols" ); }

	public function setRows( $value ){ $this->setAttribute( "rows", $value ); return $this; }
	public function getRows(){ return $this->getAttribute( "rows" ); }
	public function removeRows(){ return $this->removeAttribute( "rows" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

}


class Head extends FertileNode {

	public function __construct(){
		parent::__construct("head");
	}

	public function setProfile( $value ){ $this->setAttribute( "profile", $value ); return $this; }
	public function getProfile(){ return $this->getAttribute( "profile" ); }
	public function removeProfile(){ return $this->removeAttribute( "profile" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class H1 extends FertileNode {

	public function __construct(){
		parent::__construct("h1");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class H2 extends FertileNode {

	public function __construct(){
		parent::__construct("h2");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class H3 extends FertileNode {

	public function __construct(){
		parent::__construct("h3");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class H4 extends FertileNode {

	public function __construct(){
		parent::__construct("h4");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class H5 extends FertileNode {

	public function __construct(){
		parent::__construct("h5");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class H6 extends FertileNode {

	public function __construct(){
		parent::__construct("h6");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Hr extends Node {

	public function __construct(){
		parent::__construct("hr");
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setNoshade( $value ){ $this->setAttribute( "noshade", $value ); return $this; }
	public function getNoshade(){ return $this->getAttribute( "noshade" ); }
	public function removeNoshade(){ return $this->removeAttribute( "noshade" ); }

	public function setSize( $value ){ $this->setAttribute( "size", $value ); return $this; }
	public function getSize(){ return $this->getAttribute( "size" ); }
	public function removeSize(){ return $this->removeAttribute( "size" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Html extends FertileNode {

	public function __construct($xmlns){
		parent::__construct("html");
		$this->setXmlns( $xmlns );
	}

	public function setXmlns( $value ){ $this->setAttribute( "xmlns", $value ); return $this; }
	public function getXmlns(){ return $this->getAttribute( "xmlns" ); }
	public function removeXmlns(){ return $this->removeAttribute( "xmlns" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class I extends FertileNode {

	public function __construct(){
		parent::__construct("i");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

}


class Iframe extends Node {

	public function __construct(){
		parent::__construct("iframe");
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setFrameborder( $value ){ $this->setAttribute( "frameborder", $value ); return $this; }
	public function getFrameborder(){ return $this->getAttribute( "frameborder" ); }
	public function removeFrameborder(){ return $this->removeAttribute( "frameborder" ); }

	public function setHeight( $value ){ $this->setAttribute( "height", $value ); return $this; }
	public function getHeight(){ return $this->getAttribute( "height" ); }
	public function removeHeight(){ return $this->removeAttribute( "height" ); }

	public function setLongdesc( $value ){ $this->setAttribute( "longdesc", $value ); return $this; }
	public function getLongdesc(){ return $this->getAttribute( "longdesc" ); }
	public function removeLongdesc(){ return $this->removeAttribute( "longdesc" ); }

	public function setMarginheight( $value ){ $this->setAttribute( "marginheight", $value ); return $this; }
	public function getMarginheight(){ return $this->getAttribute( "marginheight" ); }
	public function removeMarginheight(){ return $this->removeAttribute( "marginheight" ); }

	public function setMarginwidth( $value ){ $this->setAttribute( "marginwidth", $value ); return $this; }
	public function getMarginwidth(){ return $this->getAttribute( "marginwidth" ); }
	public function removeMarginwidth(){ return $this->removeAttribute( "marginwidth" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setScrolling( $value ){ $this->setAttribute( "scrolling", $value ); return $this; }
	public function getScrolling(){ return $this->getAttribute( "scrolling" ); }
	public function removeScrolling(){ return $this->removeAttribute( "scrolling" ); }

	public function setSrc( $value ){ $this->setAttribute( "src", $value ); return $this; }
	public function getSrc(){ return $this->getAttribute( "src" ); }
	public function removeSrc(){ return $this->removeAttribute( "src" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

}


class Img extends Node {

	public function __construct($alt, $src){
		parent::__construct("img");
		$this->setAlt( $alt );
		$this->setSrc( $src );
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setAlt( $value ){ $this->setAttribute( "alt", $value ); return $this; }
	public function getAlt(){ return $this->getAttribute( "alt" ); }
	public function removeAlt(){ return $this->removeAttribute( "alt" ); }

	public function setSrc( $value ){ $this->setAttribute( "src", $value ); return $this; }
	public function getSrc(){ return $this->getAttribute( "src" ); }
	public function removeSrc(){ return $this->removeAttribute( "src" ); }

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setBorder( $value ){ $this->setAttribute( "border", $value ); return $this; }
	public function getBorder(){ return $this->getAttribute( "border" ); }
	public function removeBorder(){ return $this->removeAttribute( "border" ); }

	public function setHeight( $value ){ $this->setAttribute( "height", $value ); return $this; }
	public function getHeight(){ return $this->getAttribute( "height" ); }
	public function removeHeight(){ return $this->removeAttribute( "height" ); }

	public function setHspace( $value ){ $this->setAttribute( "hspace", $value ); return $this; }
	public function getHspace(){ return $this->getAttribute( "hspace" ); }
	public function removeHspace(){ return $this->removeAttribute( "hspace" ); }

	public function setIsmap( $value ){ $this->setAttribute( "ismap", $value ); return $this; }
	public function getIsmap(){ return $this->getAttribute( "ismap" ); }
	public function removeIsmap(){ return $this->removeAttribute( "ismap" ); }

	public function setLongdesc( $value ){ $this->setAttribute( "longdesc", $value ); return $this; }
	public function getLongdesc(){ return $this->getAttribute( "longdesc" ); }
	public function removeLongdesc(){ return $this->removeAttribute( "longdesc" ); }

	public function setUsemap( $value ){ $this->setAttribute( "usemap", $value ); return $this; }
	public function getUsemap(){ return $this->getAttribute( "usemap" ); }
	public function removeUsemap(){ return $this->removeAttribute( "usemap" ); }

	public function setVspace( $value ){ $this->setAttribute( "vspace", $value ); return $this; }
	public function getVspace(){ return $this->getAttribute( "vspace" ); }
	public function removeVspace(){ return $this->removeAttribute( "vspace" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Input extends Node {

	public function __construct(){
		parent::__construct("input");
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setAccept( $value ){ $this->setAttribute( "accept", $value ); return $this; }
	public function getAccept(){ return $this->getAttribute( "accept" ); }
	public function removeAccept(){ return $this->removeAttribute( "accept" ); }

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setAlt( $value ){ $this->setAttribute( "alt", $value ); return $this; }
	public function getAlt(){ return $this->getAttribute( "alt" ); }
	public function removeAlt(){ return $this->removeAttribute( "alt" ); }

	public function setChecked( $value ){ $this->setAttribute( "checked", $value ); return $this; }
	public function getChecked(){ return $this->getAttribute( "checked" ); }
	public function removeChecked(){ return $this->removeAttribute( "checked" ); }

	public function setDisabled( $value ){ $this->setAttribute( "disabled", $value ); return $this; }
	public function getDisabled(){ return $this->getAttribute( "disabled" ); }
	public function removeDisabled(){ return $this->removeAttribute( "disabled" ); }

	public function setMaxlength( $value ){ $this->setAttribute( "maxlength", $value ); return $this; }
	public function getMaxlength(){ return $this->getAttribute( "maxlength" ); }
	public function removeMaxlength(){ return $this->removeAttribute( "maxlength" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setReadonly( $value ){ $this->setAttribute( "readonly", $value ); return $this; }
	public function getReadonly(){ return $this->getAttribute( "readonly" ); }
	public function removeReadonly(){ return $this->removeAttribute( "readonly" ); }

	public function setSize( $value ){ $this->setAttribute( "size", $value ); return $this; }
	public function getSize(){ return $this->getAttribute( "size" ); }
	public function removeSize(){ return $this->removeAttribute( "size" ); }

	public function setSrc( $value ){ $this->setAttribute( "src", $value ); return $this; }
	public function getSrc(){ return $this->getAttribute( "src" ); }
	public function removeSrc(){ return $this->removeAttribute( "src" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setValue( $value ){ $this->setAttribute( "value", $value ); return $this; }
	public function getValue(){ return $this->getAttribute( "value" ); }
	public function removeValue(){ return $this->removeAttribute( "value" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Ins extends FertileNode {

	public function __construct(){
		parent::__construct("ins");
	}

	public function setCite( $value ){ $this->setAttribute( "cite", $value ); return $this; }
	public function getCite(){ return $this->getAttribute( "cite" ); }
	public function removeCite(){ return $this->removeAttribute( "cite" ); }

	public function setDatetime( $value ){ $this->setAttribute( "datetime", $value ); return $this; }
	public function getDatetime(){ return $this->getAttribute( "datetime" ); }
	public function removeDatetime(){ return $this->removeAttribute( "datetime" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Kbd extends FertileNode {

	public function __construct(){
		parent::__construct("kbd");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Label extends FertileNode {

	public function __construct(){
		parent::__construct("label");
	}

	public function setFor( $value ){ $this->setAttribute( "for", $value ); return $this; }
	public function getFor(){ return $this->getAttribute( "for" ); }
	public function removeFor(){ return $this->removeAttribute( "for" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Li extends FertileNode {

	public function __construct(){
		parent::__construct("li");
	}

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setValue( $value ){ $this->setAttribute( "value", $value ); return $this; }
	public function getValue(){ return $this->getAttribute( "value" ); }
	public function removeValue(){ return $this->removeAttribute( "value" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Link extends FertileNode {

	public function __construct(){
		parent::__construct("link");
	}

	public function setCharset( $value ){ $this->setAttribute( "charset", $value ); return $this; }
	public function getCharset(){ return $this->getAttribute( "charset" ); }
	public function removeCharset(){ return $this->removeAttribute( "charset" ); }

	public function setHref( $value ){ $this->setAttribute( "href", $value ); return $this; }
	public function getHref(){ return $this->getAttribute( "href" ); }
	public function removeHref(){ return $this->removeAttribute( "href" ); }

	public function setHreflang( $value ){ $this->setAttribute( "hreflang", $value ); return $this; }
	public function getHreflang(){ return $this->getAttribute( "hreflang" ); }
	public function removeHreflang(){ return $this->removeAttribute( "hreflang" ); }

	public function setMedia( $value ){ $this->setAttribute( "media", $value ); return $this; }
	public function getMedia(){ return $this->getAttribute( "media" ); }
	public function removeMedia(){ return $this->removeAttribute( "media" ); }

	public function setRel( $value ){ $this->setAttribute( "rel", $value ); return $this; }
	public function getRel(){ return $this->getAttribute( "rel" ); }
	public function removeRel(){ return $this->removeAttribute( "rel" ); }

	public function setRev( $value ){ $this->setAttribute( "rev", $value ); return $this; }
	public function getRev(){ return $this->getAttribute( "rev" ); }
	public function removeRev(){ return $this->removeAttribute( "rev" ); }

	public function setTarget( $value ){ $this->setAttribute( "target", $value ); return $this; }
	public function getTarget(){ return $this->getAttribute( "target" ); }
	public function removeTarget(){ return $this->removeAttribute( "target" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Map extends FertileNode {

	public function __construct($id){
		parent::__construct("map");
		$this->setId( $id );
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Meta extends FertileNode {

	public function __construct($content){
		parent::__construct("meta");
		$this->setContent( $content );
	}

	public function setContent( $value ){ $this->setAttribute( "content", $value ); return $this; }
	public function getContent(){ return $this->getAttribute( "content" ); }
	public function removeContent(){ return $this->removeAttribute( "content" ); }

	public function setHttpEquiv( $value ){ $this->setAttribute( "http-equiv", $value ); return $this; }
	public function getHttpEquiv(){ return $this->getAttribute( "http-equiv" ); }
	public function removeHttpEquiv(){ return $this->removeAttribute( "http-equiv" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setScheme( $value ){ $this->setAttribute( "scheme", $value ); return $this; }
	public function getScheme(){ return $this->getAttribute( "scheme" ); }
	public function removeScheme(){ return $this->removeAttribute( "scheme" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Noframes extends FertileNode {

	public function __construct(){
		parent::__construct("noframes");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Noscript extends FertileNode {

	public function __construct(){
		parent::__construct("noscript");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Object extends FertileNode {

	public function __construct(){
		parent::__construct("object");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setArchive( $value ){ $this->setAttribute( "archive", $value ); return $this; }
	public function getArchive(){ return $this->getAttribute( "archive" ); }
	public function removeArchive(){ return $this->removeAttribute( "archive" ); }

	public function setBorder( $value ){ $this->setAttribute( "border", $value ); return $this; }
	public function getBorder(){ return $this->getAttribute( "border" ); }
	public function removeBorder(){ return $this->removeAttribute( "border" ); }

	public function setClassid( $value ){ $this->setAttribute( "classid", $value ); return $this; }
	public function getClassid(){ return $this->getAttribute( "classid" ); }
	public function removeClassid(){ return $this->removeAttribute( "classid" ); }

	public function setCodebase( $value ){ $this->setAttribute( "codebase", $value ); return $this; }
	public function getCodebase(){ return $this->getAttribute( "codebase" ); }
	public function removeCodebase(){ return $this->removeAttribute( "codebase" ); }

	public function setCodetype( $value ){ $this->setAttribute( "codetype", $value ); return $this; }
	public function getCodetype(){ return $this->getAttribute( "codetype" ); }
	public function removeCodetype(){ return $this->removeAttribute( "codetype" ); }

	public function setData( $value ){ $this->setAttribute( "data", $value ); return $this; }
	public function getData(){ return $this->getAttribute( "data" ); }
	public function removeData(){ return $this->removeAttribute( "data" ); }

	public function setDeclare( $value ){ $this->setAttribute( "declare", $value ); return $this; }
	public function getDeclare(){ return $this->getAttribute( "declare" ); }
	public function removeDeclare(){ return $this->removeAttribute( "declare" ); }

	public function setHeight( $value ){ $this->setAttribute( "height", $value ); return $this; }
	public function getHeight(){ return $this->getAttribute( "height" ); }
	public function removeHeight(){ return $this->removeAttribute( "height" ); }

	public function setHspace( $value ){ $this->setAttribute( "hspace", $value ); return $this; }
	public function getHspace(){ return $this->getAttribute( "hspace" ); }
	public function removeHspace(){ return $this->removeAttribute( "hspace" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setStandby( $value ){ $this->setAttribute( "standby", $value ); return $this; }
	public function getStandby(){ return $this->getAttribute( "standby" ); }
	public function removeStandby(){ return $this->removeAttribute( "standby" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setUsemap( $value ){ $this->setAttribute( "usemap", $value ); return $this; }
	public function getUsemap(){ return $this->getAttribute( "usemap" ); }
	public function removeUsemap(){ return $this->removeAttribute( "usemap" ); }

	public function setVspace( $value ){ $this->setAttribute( "vspace", $value ); return $this; }
	public function getVspace(){ return $this->getAttribute( "vspace" ); }
	public function removeVspace(){ return $this->removeAttribute( "vspace" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Ol extends FertileNode {

	public function __construct(){
		parent::__construct("ol");
	}

	public function setCompact( $value ){ $this->setAttribute( "compact", $value ); return $this; }
	public function getCompact(){ return $this->getAttribute( "compact" ); }
	public function removeCompact(){ return $this->removeAttribute( "compact" ); }

	public function setStart( $value ){ $this->setAttribute( "start", $value ); return $this; }
	public function getStart(){ return $this->getAttribute( "start" ); }
	public function removeStart(){ return $this->removeAttribute( "start" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Optgroup extends FertileNode {

	public function __construct($label){
		parent::__construct("optgroup");
		$this->setLabel( $label );
	}

	public function setLabel( $value ){ $this->setAttribute( "label", $value ); return $this; }
	public function getLabel(){ return $this->getAttribute( "label" ); }
	public function removeLabel(){ return $this->removeAttribute( "label" ); }

	public function setDisabled( $value ){ $this->setAttribute( "disabled", $value ); return $this; }
	public function getDisabled(){ return $this->getAttribute( "disabled" ); }
	public function removeDisabled(){ return $this->removeAttribute( "disabled" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Option extends FertileNode {

	public function __construct(){
		parent::__construct("option");
	}

	public function setDisabled( $value ){ $this->setAttribute( "disabled", $value ); return $this; }
	public function getDisabled(){ return $this->getAttribute( "disabled" ); }
	public function removeDisabled(){ return $this->removeAttribute( "disabled" ); }

	public function setLabel( $value ){ $this->setAttribute( "label", $value ); return $this; }
	public function getLabel(){ return $this->getAttribute( "label" ); }
	public function removeLabel(){ return $this->removeAttribute( "label" ); }

	public function setSelected( $value ){ $this->setAttribute( "selected", $value ); return $this; }
	public function getSelected(){ return $this->getAttribute( "selected" ); }
	public function removeSelected(){ return $this->removeAttribute( "selected" ); }

	public function setValue( $value ){ $this->setAttribute( "value", $value ); return $this; }
	public function getValue(){ return $this->getAttribute( "value" ); }
	public function removeValue(){ return $this->removeAttribute( "value" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setTabindex( $value ){ $this->setAttribute( "tabindex", $value ); return $this; }
	public function getTabindex(){ return $this->getAttribute( "tabindex" ); }
	public function removeTabindex(){ return $this->removeAttribute( "tabindex" ); }

}


class P extends FertileNode {

	public function __construct(){
		parent::__construct("p");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Param extends Node {

	public function __construct($name){
		parent::__construct("param");
		$this->setName( $name );
	}

	/* @Override */
	public function write(){
		return $this->writeOpen();
	}


	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setValue( $value ){ $this->setAttribute( "value", $value ); return $this; }
	public function getValue(){ return $this->getAttribute( "value" ); }
	public function removeValue(){ return $this->removeAttribute( "value" ); }

	public function setValuetype( $value ){ $this->setAttribute( "valuetype", $value ); return $this; }
	public function getValuetype(){ return $this->getAttribute( "valuetype" ); }
	public function removeValuetype(){ return $this->removeAttribute( "valuetype" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

}


class Pre extends FertileNode {

	public function __construct(){
		parent::__construct("pre");
	}

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setXmlspace( $value ){ $this->setAttribute( "xmlspace", $value ); return $this; }
	public function getXmlspace(){ return $this->getAttribute( "xmlspace" ); }
	public function removeXmlspace(){ return $this->removeAttribute( "xmlspace" ); }

}


class Q extends FertileNode {

	public function __construct(){
		parent::__construct("q");
	}

	public function setCite( $value ){ $this->setAttribute( "cite", $value ); return $this; }
	public function getCite(){ return $this->getAttribute( "cite" ); }
	public function removeCite(){ return $this->removeAttribute( "cite" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class S extends FertileNode {

	public function __construct(){
		parent::__construct("s");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Samp extends FertileNode {

	public function __construct(){
		parent::__construct("samp");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Script extends FertileNode {

	public function __construct($type){
		parent::__construct("script");
		$this->setType( $type );
	}

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setCharset( $value ){ $this->setAttribute( "charset", $value ); return $this; }
	public function getCharset(){ return $this->getAttribute( "charset" ); }
	public function removeCharset(){ return $this->removeAttribute( "charset" ); }

	public function setDefer( $value ){ $this->setAttribute( "defer", $value ); return $this; }
	public function getDefer(){ return $this->getAttribute( "defer" ); }
	public function removeDefer(){ return $this->removeAttribute( "defer" ); }

	public function setLanguage( $value ){ $this->setAttribute( "language", $value ); return $this; }
	public function getLanguage(){ return $this->getAttribute( "language" ); }
	public function removeLanguage(){ return $this->removeAttribute( "language" ); }

	public function setSrc( $value ){ $this->setAttribute( "src", $value ); return $this; }
	public function getSrc(){ return $this->getAttribute( "src" ); }
	public function removeSrc(){ return $this->removeAttribute( "src" ); }

	public function setXmlspace( $value ){ $this->setAttribute( "xmlspace", $value ); return $this; }
	public function getXmlspace(){ return $this->getAttribute( "xmlspace" ); }
	public function removeXmlspace(){ return $this->removeAttribute( "xmlspace" ); }

}


class Select extends FertileNode {

	public function __construct(){
		parent::__construct("select");
	}

	public function setDisabled( $value ){ $this->setAttribute( "disabled", $value ); return $this; }
	public function getDisabled(){ return $this->getAttribute( "disabled" ); }
	public function removeDisabled(){ return $this->removeAttribute( "disabled" ); }

	public function setMultiple( $value ){ $this->setAttribute( "multiple", $value ); return $this; }
	public function getMultiple(){ return $this->getAttribute( "multiple" ); }
	public function removeMultiple(){ return $this->removeAttribute( "multiple" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setSize( $value ){ $this->setAttribute( "size", $value ); return $this; }
	public function getSize(){ return $this->getAttribute( "size" ); }
	public function removeSize(){ return $this->removeAttribute( "size" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setAccesskey( $value ){ $this->setAttribute( "accesskey", $value ); return $this; }
	public function getAccesskey(){ return $this->getAttribute( "accesskey" ); }
	public function removeAccesskey(){ return $this->removeAttribute( "accesskey" ); }

	public function setTabindex( $value ){ $this->setAttribute( "tabindex", $value ); return $this; }
	public function getTabindex(){ return $this->getAttribute( "tabindex" ); }
	public function removeTabindex(){ return $this->removeAttribute( "tabindex" ); }

}


class Small extends FertileNode {

	public function __construct(){
		parent::__construct("small");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

}


class Span extends FertileNode {

	public function __construct(){
		parent::__construct("span");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Strike extends FertileNode {

	public function __construct(){
		parent::__construct("strike");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Strong extends FertileNode {

	public function __construct(){
		parent::__construct("strong");
	}

	public function setD( $value ){ $this->setAttribute( "d", $value ); return $this; }
	public function getD(){ return $this->getAttribute( "d" ); }
	public function removeD(){ return $this->removeAttribute( "d" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Style extends FertileNode {

	public function __construct($type){
		parent::__construct("style");
		$this->setType( $type );
	}

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setMedia( $value ){ $this->setAttribute( "media", $value ); return $this; }
	public function getMedia(){ return $this->getAttribute( "media" ); }
	public function removeMedia(){ return $this->removeAttribute( "media" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXmlspace( $value ){ $this->setAttribute( "xmlspace", $value ); return $this; }
	public function getXmlspace(){ return $this->getAttribute( "xmlspace" ); }
	public function removeXmlspace(){ return $this->removeAttribute( "xmlspace" ); }

}


class Sub extends FertileNode {

	public function __construct(){
		parent::__construct("sub");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Sup extends FertileNode {

	public function __construct(){
		parent::__construct("sup");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Table extends FertileNode {

	public function __construct(){
		parent::__construct("table");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setBgcolor( $value ){ $this->setAttribute( "bgcolor", $value ); return $this; }
	public function getBgcolor(){ return $this->getAttribute( "bgcolor" ); }
	public function removeBgcolor(){ return $this->removeAttribute( "bgcolor" ); }

	public function setBorder( $value ){ $this->setAttribute( "border", $value ); return $this; }
	public function getBorder(){ return $this->getAttribute( "border" ); }
	public function removeBorder(){ return $this->removeAttribute( "border" ); }

	public function setCellpadding( $value ){ $this->setAttribute( "cellpadding", $value ); return $this; }
	public function getCellpadding(){ return $this->getAttribute( "cellpadding" ); }
	public function removeCellpadding(){ return $this->removeAttribute( "cellpadding" ); }

	public function setCellspacing( $value ){ $this->setAttribute( "cellspacing", $value ); return $this; }
	public function getCellspacing(){ return $this->getAttribute( "cellspacing" ); }
	public function removeCellspacing(){ return $this->removeAttribute( "cellspacing" ); }

	public function setFrame( $value ){ $this->setAttribute( "frame", $value ); return $this; }
	public function getFrame(){ return $this->getAttribute( "frame" ); }
	public function removeFrame(){ return $this->removeAttribute( "frame" ); }

	public function setRules( $value ){ $this->setAttribute( "rules", $value ); return $this; }
	public function getRules(){ return $this->getAttribute( "rules" ); }
	public function removeRules(){ return $this->removeAttribute( "rules" ); }

	public function setSummary( $value ){ $this->setAttribute( "summary", $value ); return $this; }
	public function getSummary(){ return $this->getAttribute( "summary" ); }
	public function removeSummary(){ return $this->removeAttribute( "summary" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Tbody extends FertileNode {

	public function __construct(){
		parent::__construct("tbody");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Td extends FertileNode {

	public function __construct(){
		parent::__construct("td");
	}

	public function setAbbr( $value ){ $this->setAttribute( "abbr", $value ); return $this; }
	public function getAbbr(){ return $this->getAttribute( "abbr" ); }
	public function removeAbbr(){ return $this->removeAttribute( "abbr" ); }

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setAxis( $value ){ $this->setAttribute( "axis", $value ); return $this; }
	public function getAxis(){ return $this->getAttribute( "axis" ); }
	public function removeAxis(){ return $this->removeAttribute( "axis" ); }

	public function setBgcolor( $value ){ $this->setAttribute( "bgcolor", $value ); return $this; }
	public function getBgcolor(){ return $this->getAttribute( "bgcolor" ); }
	public function removeBgcolor(){ return $this->removeAttribute( "bgcolor" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setColspan( $value ){ $this->setAttribute( "colspan", $value ); return $this; }
	public function getColspan(){ return $this->getAttribute( "colspan" ); }
	public function removeColspan(){ return $this->removeAttribute( "colspan" ); }

	public function setHeaders( $value ){ $this->setAttribute( "headers", $value ); return $this; }
	public function getHeaders(){ return $this->getAttribute( "headers" ); }
	public function removeHeaders(){ return $this->removeAttribute( "headers" ); }

	public function setHeight( $value ){ $this->setAttribute( "height", $value ); return $this; }
	public function getHeight(){ return $this->getAttribute( "height" ); }
	public function removeHeight(){ return $this->removeAttribute( "height" ); }

	public function setNowrap( $value ){ $this->setAttribute( "nowrap", $value ); return $this; }
	public function getNowrap(){ return $this->getAttribute( "nowrap" ); }
	public function removeNowrap(){ return $this->removeAttribute( "nowrap" ); }

	public function setRowspan( $value ){ $this->setAttribute( "rowspan", $value ); return $this; }
	public function getRowspan(){ return $this->getAttribute( "rowspan" ); }
	public function removeRowspan(){ return $this->removeAttribute( "rowspan" ); }

	public function setScope( $value ){ $this->setAttribute( "scope", $value ); return $this; }
	public function getScope(){ return $this->getAttribute( "scope" ); }
	public function removeScope(){ return $this->removeAttribute( "scope" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Text extends Node {

        public function __construct($text){
                parent::__construct($text);
        }

        /* @Override */
        public function write(){
                return $this->tag_;
        }

}


class Textarea extends FertileNode {

	public function __construct($cols, $rows){
		parent::__construct("textarea");
		$this->setCols( $cols );
		$this->setRows( $rows );
	}

	public function setCols( $value ){ $this->setAttribute( "cols", $value ); return $this; }
	public function getCols(){ return $this->getAttribute( "cols" ); }
	public function removeCols(){ return $this->removeAttribute( "cols" ); }

	public function setRows( $value ){ $this->setAttribute( "rows", $value ); return $this; }
	public function getRows(){ return $this->getAttribute( "rows" ); }
	public function removeRows(){ return $this->removeAttribute( "rows" ); }

	public function setDisabled( $value ){ $this->setAttribute( "disabled", $value ); return $this; }
	public function getDisabled(){ return $this->getAttribute( "disabled" ); }
	public function removeDisabled(){ return $this->removeAttribute( "disabled" ); }

	public function setName( $value ){ $this->setAttribute( "name", $value ); return $this; }
	public function getName(){ return $this->getAttribute( "name" ); }
	public function removeName(){ return $this->removeAttribute( "name" ); }

	public function setReadonly( $value ){ $this->setAttribute( "readonly", $value ); return $this; }
	public function getReadonly(){ return $this->getAttribute( "readonly" ); }
	public function removeReadonly(){ return $this->removeAttribute( "readonly" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

	public function setTabindex( $value ){ $this->setAttribute( "tabindex", $value ); return $this; }
	public function getTabindex(){ return $this->getAttribute( "tabindex" ); }
	public function removeTabindex(){ return $this->removeAttribute( "tabindex" ); }

	public function setAccesskey( $value ){ $this->setAttribute( "accesskey", $value ); return $this; }
	public function getAccesskey(){ return $this->getAttribute( "accesskey" ); }
	public function removeAccesskey(){ return $this->removeAttribute( "accesskey" ); }

}


class Tfoot extends FertileNode {

	public function __construct(){
		parent::__construct("tfoot");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Th extends FertileNode {

	public function __construct(){
		parent::__construct("th");
	}

	public function setAbbr( $value ){ $this->setAttribute( "abbr", $value ); return $this; }
	public function getAbbr(){ return $this->getAttribute( "abbr" ); }
	public function removeAbbr(){ return $this->removeAttribute( "abbr" ); }

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setAxis( $value ){ $this->setAttribute( "axis", $value ); return $this; }
	public function getAxis(){ return $this->getAttribute( "axis" ); }
	public function removeAxis(){ return $this->removeAttribute( "axis" ); }

	public function setBgcolor( $value ){ $this->setAttribute( "bgcolor", $value ); return $this; }
	public function getBgcolor(){ return $this->getAttribute( "bgcolor" ); }
	public function removeBgcolor(){ return $this->removeAttribute( "bgcolor" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setColspan( $value ){ $this->setAttribute( "colspan", $value ); return $this; }
	public function getColspan(){ return $this->getAttribute( "colspan" ); }
	public function removeColspan(){ return $this->removeAttribute( "colspan" ); }

	public function setHeaders( $value ){ $this->setAttribute( "headers", $value ); return $this; }
	public function getHeaders(){ return $this->getAttribute( "headers" ); }
	public function removeHeaders(){ return $this->removeAttribute( "headers" ); }

	public function setHeight( $value ){ $this->setAttribute( "height", $value ); return $this; }
	public function getHeight(){ return $this->getAttribute( "height" ); }
	public function removeHeight(){ return $this->removeAttribute( "height" ); }

	public function setNowrap( $value ){ $this->setAttribute( "nowrap", $value ); return $this; }
	public function getNowrap(){ return $this->getAttribute( "nowrap" ); }
	public function removeNowrap(){ return $this->removeAttribute( "nowrap" ); }

	public function setRowspan( $value ){ $this->setAttribute( "rowspan", $value ); return $this; }
	public function getRowspan(){ return $this->getAttribute( "rowspan" ); }
	public function removeRowspan(){ return $this->removeAttribute( "rowspan" ); }

	public function setScope( $value ){ $this->setAttribute( "scope", $value ); return $this; }
	public function getScope(){ return $this->getAttribute( "scope" ); }
	public function removeScope(){ return $this->removeAttribute( "scope" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setWidth( $value ){ $this->setAttribute( "width", $value ); return $this; }
	public function getWidth(){ return $this->getAttribute( "width" ); }
	public function removeWidth(){ return $this->removeAttribute( "width" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Thead extends FertileNode {

	public function __construct(){
		parent::__construct("thead");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Title extends FertileNode {

	public function __construct(){
		parent::__construct("title");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Tr extends FertileNode {

	public function __construct(){
		parent::__construct("tr");
	}

	public function setAlign( $value ){ $this->setAttribute( "align", $value ); return $this; }
	public function getAlign(){ return $this->getAttribute( "align" ); }
	public function removeAlign(){ return $this->removeAttribute( "align" ); }

	public function setBgcolor( $value ){ $this->setAttribute( "bgcolor", $value ); return $this; }
	public function getBgcolor(){ return $this->getAttribute( "bgcolor" ); }
	public function removeBgcolor(){ return $this->removeAttribute( "bgcolor" ); }

	public function setChar( $value ){ $this->setAttribute( "char", $value ); return $this; }
	public function getChar(){ return $this->getAttribute( "char" ); }
	public function removeChar(){ return $this->removeAttribute( "char" ); }

	public function setCharoff( $value ){ $this->setAttribute( "charoff", $value ); return $this; }
	public function getCharoff(){ return $this->getAttribute( "charoff" ); }
	public function removeCharoff(){ return $this->removeAttribute( "charoff" ); }

	public function setValign( $value ){ $this->setAttribute( "valign", $value ); return $this; }
	public function getValign(){ return $this->getAttribute( "valign" ); }
	public function removeValign(){ return $this->removeAttribute( "valign" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Tt extends FertileNode {

	public function __construct(){
		parent::__construct("tt");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

}


class U extends FertileNode {

	public function __construct(){
		parent::__construct("u");
	}

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}


class Ul extends FertileNode {

	public function __construct(){
		parent::__construct("ul");
	}

	public function setCompact( $value ){ $this->setAttribute( "compact", $value ); return $this; }
	public function getCompact(){ return $this->getAttribute( "compact" ); }
	public function removeCompact(){ return $this->removeAttribute( "compact" ); }

	public function setType( $value ){ $this->setAttribute( "type", $value ); return $this; }
	public function getType(){ return $this->getAttribute( "type" ); }
	public function removeType(){ return $this->removeAttribute( "type" ); }

	public function setId( $value ){ $this->setAttribute( "id", $value ); return $this; }
	public function getId(){ return $this->getAttribute( "id" ); }
	public function removeId(){ return $this->removeAttribute( "id" ); }

	public function setCSSClass( $value ){ $this->setAttribute( "class", $value ); return $this; }
	public function getCSSClass(){ return $this->getAttribute( "class" ); }
	public function removeCSSClass(){ return $this->removeAttribute( "class" ); }

	public function setTitle( $value ){ $this->setAttribute( "title", $value ); return $this; }
	public function getTitle(){ return $this->getAttribute( "title" ); }
	public function removeTitle(){ return $this->removeAttribute( "title" ); }

	public function setStyle( $value ){ $this->setAttribute( "style", $value ); return $this; }
	public function getStyle(){ return $this->getAttribute( "style" ); }
	public function removeStyle(){ return $this->removeAttribute( "style" ); }

	public function setDir( $value ){ $this->setAttribute( "dir", $value ); return $this; }
	public function getDir(){ return $this->getAttribute( "dir" ); }
	public function removeDir(){ return $this->removeAttribute( "dir" ); }

	public function setLang( $value ){ $this->setAttribute( "lang", $value ); return $this; }
	public function getLang(){ return $this->getAttribute( "lang" ); }
	public function removeLang(){ return $this->removeAttribute( "lang" ); }

	public function setXMLLang( $value ){ $this->setAttribute( "xml:lang", $value ); return $this; }
	public function getXMLLang(){ return $this->getAttribute( "xml:lang" ); }
	public function removeXMLLang(){ return $this->removeAttribute( "xml:lang" ); }

}

?>
