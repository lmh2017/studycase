package org.study.proxy;

public class Dog implements Animal{
	
	private String name;
	private String food;
	
	public Dog() {
		this.name = "Tom";
		this.food = "bone";
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println(name+" is eating "+food);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}
	
	

}
