package com.piro84.carmodel.tests;

import java.util.ArrayList;
import java.util.List;

import com.piro84.entities.carmodel.Component;
import com.piro84.entities.carmodel.SubComponent;



@org.springframework.stereotype.Component
public class CarDefaultProvider {
	
	
	public Component getSteer(){
		Component steer = new Component();
		steer.setName("steer");
		steer.setElectricalComponents(createSubComponents("electrical",2));
		steer.setMechanicalComponents(createSubComponents("mechanical",2));
		
		return steer;
	}

	private List<SubComponent> createSubComponents(String model,int n) {
		List<SubComponent> subComponents = new ArrayList<SubComponent>(n);
		for(int i=0;i<n;i++){
			subComponents.add(createSubComponent(model));
		}
		return null;
	}
	
	private SubComponent createSubComponent(String model){
		SubComponent subComponent=new SubComponent();
		subComponent.setModel(model);
		return subComponent;
	}
	
}
