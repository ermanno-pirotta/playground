package com.piro84.classifiers

import weka.associations.LabeledItemSet;
import weka.classifiers.AbstractClassifier
import weka.classifiers.functions.Logistic
import weka.core.Instances
import weka.core.converters.ConverterUtils.DataSource
import weka.core.json.JSONInstances;
import weka.core.json.JSONNode;

class BinaryClassifier implements IBinaryClassifier {

	@Override
	double classify(String testFilePath, GenericInstance instance) {
		Instances trainData = loadTrainDataSet(testFilePath)
		trainData.setClassIndex(trainData.numAttributes() - 1)
		
		AbstractClassifier classifier = buildClassifier(trainData)
		Instances unlabeled = convertToInstance(instance)
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1)
		
		return classifier.classifyInstance(unlabeled)
		//unlabeled.instance(0).setClassValue(assignedClass)		
		//unlabeled.classAttribute()
	}
	
	def loadTrainDataSet(path){
		return DataSource.read("$path");
	}	

	def convertToInstance(GenericInstance instance){
		def factory = InstanceFactory.getInstance()
		def model = factory.createStructure(instance)
		def data = factory.populateData(instance, model)				
		
		return data
	}	
	
	/**
	 * Builds a multinomial logistic regression model with a ridge estimator.
	 * 
	 * @param data
	 * @return
	 */
	def buildClassifier(Instances data){
		Logistic classifier = new Logistic()
//		classifier.setOptions("-R 1.0E-8")
		classifier.buildClassifier(data)
		return classifier
	}
}
