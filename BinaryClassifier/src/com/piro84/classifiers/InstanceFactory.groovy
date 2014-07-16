package com.piro84.classifiers;

import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instance
import weka.core.Instances

/**
 * Creates {@link Instances}  from the given 
 * @author Ermanno
 *
 */
@Singleton
class InstanceFactory {
	
	Instances createStructure(GenericInstance instance){
		def attributeList = []
		
		for(definition in instance.definitions){
			def val = definition?.value
			
			switch(val?.type){
				case "NUMERIC":
					attributeList.add(new Attribute(definition.key))
					continue
				case "NOMINAL":
					//in case of nominal values, I need to add all the labels					
					attributeList.add(new Attribute(definition.key, val.labels))
					continue
				case "STRING":
					attributeList.add(new Attribute(definition.key, (ArrayList<String>) null))
					continue
				case "DATE":
					attributeList.add(new Attribute(definition.key, instance.dateFormat))
					continue
				case "RELATIONAL":
					/*currently not implemented*/
					continue
			}
				
		}		
		
		return new Instances("dataset",attributeList,0)
	}
	
	def populateData(GenericInstance genInstance, Instances data){
		Instance di = new DenseInstance(data.numAttributes())
//		def values = [data.numAttributes()] as double[]
		double[] values = new double[data.numAttributes()]
		
		genInstance.definitions?.eachWithIndex() { definition, i -> 
			def val = definition.value
			def instanceValue = genInstance.values[i]
			switch(val?.type){
				case "NUMERIC":
					values[i] = instanceValue
					break
				case "NOMINAL":
					values[i] = data.attribute(i).indexOfValue("$instanceValue")
					break
				case "STRING":
					values[i] = data.attribute(i).addStringValue("$instanceValue")
					break
				case "DATE":					
					values[i] = data.attribute(i).parseDate("$instanceValue")
					break
				case "RELATIONAL":
					/*currently not implemented*/
					break
			}
		}
		// add the data to the instances
		data.add(new DenseInstance(1.0, values))
		
		return data
	}
}
