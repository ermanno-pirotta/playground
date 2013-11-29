package com.piro84.model2.tests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.piro84.model2.Embeddable1;
import com.piro84.model2.Embeddable2;

@Component("model2.entitydefaultprovider")
public class EntityDefaultProvider {
	
	
	public Embeddable1 getSteer(){
		Embeddable1 embeddable = new Embeddable1();
		embeddable.setName("steer");
		embeddable.setEmbeddableList2(createSubComponents("electrical",2));
		embeddable.setEmbeddableList1(createSubComponents("mechanical",2));
		
		return embeddable;
	}

	private List<Embeddable2> createSubComponents(String model,int n) {
		List<Embeddable2> subComponents = new ArrayList<Embeddable2>(n);
		for(int i=0;i<n;i++){
			subComponents.add(createSubComponent(model));
		}
		return null;
	}
	
	private Embeddable2 createSubComponent(String model){
		Embeddable2 subComponent=new Embeddable2();
		subComponent.setModel(model);
		return subComponent;
	}
	
}
