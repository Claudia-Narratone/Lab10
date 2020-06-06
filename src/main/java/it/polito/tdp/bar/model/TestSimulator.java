package it.polito.tdp.bar.model;

public class TestSimulator {

	public static void main(String[] args) {
		
		Simulator simulator=new Simulator();
		simulator.init();
		simulator.run();
		
		System.out.println(simulator.getStatistica());

	}

}
