package com.piro84.personmodel.tests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.piro84.entities.personmodel.Address;
import com.piro84.entities.personmodel.AddressField;

@Component
public class PersonDefaultProvider {

	public String getName(){
		return "Pluto";
	}
	
	
	public List<Address> getAddresses(int n,String namePrefix){
		List<Address>  addresses= new ArrayList<Address>(n);
		
		for(int i=0;i<n;i++){
			addresses.add(getAddress(namePrefix));
		}
		return addresses;
	}
	
	public Address getAddress(String name){
		Address address = new Address();
		address.setName(name);
		address.setCity(getAddressField("city","city field"));
		address.setCountry(getAddressField("country", "country field"));
		return address;
	}
	
	public AddressField getAddressField(String name,String desc){
		AddressField field = new AddressField();
		field.setName(name);
		field.setDescription(desc);
		return field;
	}
}
