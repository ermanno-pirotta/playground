package com.piro84.classifiers;

/**
 * Represents a model of an instance to be classified.
 *
 * @author Ermanno
 *
 */
class GenericInstance {
	
	/**
	 * Maps the name of the attributes with the {@link InstanceType}.
	 * ["name":Data]
	 */
	def definitions = [:]
	
	def dateFormat = "yyyy-MM-dd";
	
	/**
	 * Maps the name of the attributes with their values
	 */
	def values = [:]
}
