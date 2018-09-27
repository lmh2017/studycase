package org.study.proxy;

public class TestMain {
	
	public static void main(String[] args) {
		Cat cat = new Cat();
		
		AnimalProxy a1 = new AnimalProxy();
		Animal catImpl = (Animal)a1.bind(cat);
		catImpl.eat();
		
		Dog dog = new Dog();
		Animal dogImpl = (Animal)a1.bind(dog);
		dogImpl.eat();
	}

}
