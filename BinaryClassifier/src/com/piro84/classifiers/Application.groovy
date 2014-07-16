package com.piro84.classifiers


class Application {	
	static void main(String[] args){
		IBinaryClassifier binC = new BinaryClassifier()
		GenericInstance instance = new GenericInstance()
		instance.dateFormat = "HH:mm:ss"
		instance.definitions=[
				"time":["type":"DATE"], 
				"number": ["type":"NUMERIC"], 
				"nominal": ["type":"NOMINAL", "labels": ["no","yes"]],
				"class" : ["type":"NOMINAL", "labels": ["off","on"]]
				]
	
		instance.values=["03:00:00",0, "yes" ,""]
		
		println binC.classify("resources\\test.arff", instance)	
	}	
	
}
