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
 * AUTHORS:
 *   Mark Kolich
 *   Chris Friedrich
 * 
 */

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

?>