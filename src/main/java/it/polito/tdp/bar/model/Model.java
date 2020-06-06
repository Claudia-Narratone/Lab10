package it.polito.tdp.bar.model;

public class Model {
	
	private Simulator simulator;
	
	public Model() {
		this.simulator=new Simulator();
	}

	public Statistica simula() {
		simulator.init();
		simulator.run();
		return simulator.getStatistica();
	}
}
