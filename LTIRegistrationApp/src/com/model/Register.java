package com.model;

public class Register {
	private int regno;
	private String name;
	private String uname;
	private String pass;
	private float bal;
	public Register(int regno, String name, String uname, String pass, float bal) {
		super();
		this.regno = regno;
		this.name = name;
		this.uname = uname;
		this.pass = pass;
		this.bal = bal;
	}
	public int getRegno() {
		return regno;
	}
	public String getName() {
		return name;
	}
	public String getUname() {
		return uname;
	}
	public String getPass() {
		return pass;
	}
	public float getBal() {
		return bal;
	}
	
}
