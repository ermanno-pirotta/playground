package com.piro84.model1.tests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.piro84.model1.Embeddable1;
import com.piro84.model1.Embeddable2;

@Component("model1.entitydefaultprovider")
public class EntityDefaultProvider {

	public String getName(){
		return "Pluto";
	}
	
	
	public List<Embeddable1> getAddresses(int n,String namePrefix){
		List<Embeddable1>  addresses= new ArrayList<Embeddable1>(n);
		
		for(int i=0;i<n;i++){
			addresses.add(getAddress(namePrefix));
		}
		return addresses;
	}
	
	public Embeddable1 getAddress(String name){
		Embeddable1 address = new Embeddable1();
		address.setName(name);
		address.setEmbeddable1(getAddressField("city","city field"));
		address.setEmbeddable2(getAddressField("country", "country field"));
		return address;
	}
	
	public Embeddable2 getAddressField(String name,String desc){
		Embeddable2 field = new Embeddable2();
		field.setName(name);
		field.setDescription(desc);
		return field;
	}
}
