package com.piro84.classifiers;

interface IBinaryClassifier {
	/**
	 * Classifies the given
	 * 
	 * @param testFilePath the path of the file containing the test instances for the classifier
	 * @param instance, the instance to be classified
	 * @return 1 or 0, according to how the instance was classified
	 */
	double classify(String testFilePath, GenericInstance instance);
}
