package com.commodity.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.commodity.commons.Actortype;

@Document
public class User {

	private String id;
	private String name;
	private Actortype type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Actortype getType() {
		return type;
	}
	public void setType(Actortype type) {
		this.type = type;
	}
	
	
}
