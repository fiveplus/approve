package com.approve.struts.approve.bo;

import java.util.ArrayList;
import java.util.List;

public class Item {
	private String name;
	private String type;
	private AdditionalParameters additionalParameters;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setAdditionalParameters(AdditionalParameters additionalParameters) {
		this.additionalParameters = additionalParameters;
	}
	public AdditionalParameters getAdditionalParameters() {
		return additionalParameters;
	}

	
	
	

	

}
