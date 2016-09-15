package com.demo.model;

import java.io.Serializable;

public class CalculatorVO implements Serializable 
{
	
	private static final long serialVersionUID = 1L;

	
	private Integer n1;
	private Integer n2;
	private String op;
	

	@Override
	public String toString() {
		return "BossVO [n1=" + n1 + ", n2=" + n2
				+ ", op=" + op + "]";
	}

	
	public Integer getN1() {
		return n1;
	}


	public void setN1(Integer n1) {
		this.n1 = n1;
	}


	public Integer getN2() {
		return n2;
	}


	public void setN2(Integer n2) {
		this.n2 = n2;
	}


	public String getOp() {
		return op;
	}


	public void setOp(String op) {
		this.op = op;
	}



	
	




}