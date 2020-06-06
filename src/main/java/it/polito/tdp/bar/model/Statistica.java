package it.polito.tdp.bar.model;

public class Statistica {
	
	private int totClienti;
	private int numClientiSoddisfatti;
	private int numClientiInsoddisfatti;
	
	
	public Statistica() {
		super();
		this.totClienti = 0;
		this.numClientiSoddisfatti = 0;
		this.numClientiInsoddisfatti = 0;
	}


	public int getTotClienti() {
		return totClienti;
	}

	public void addTotClienti(int clienti) {
		this.totClienti=totClienti+clienti;
	}

	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}
	
	public void addClientiSoddisfatti(int clienti) {
		this.numClientiSoddisfatti=numClientiSoddisfatti+clienti;
	}


	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}
	
	public void addClientiInsoddisfatti(int clienti) {
		this.numClientiInsoddisfatti=numClientiInsoddisfatti+clienti;
	}


	@Override
	public String toString() {
		return "Statistica [totClienti=" + totClienti + ", numClientiSoddisfatti=" + numClientiSoddisfatti
				+ ", numClientiInsoddisfatti=" + numClientiInsoddisfatti + "]";
	}
	
	
}
