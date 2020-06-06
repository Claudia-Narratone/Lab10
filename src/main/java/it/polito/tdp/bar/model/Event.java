package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event>{

	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}
	
	private Duration time;
	private EventType type;
	private int numPersone;
	private Duration durata;
	private double tolleranza;
	private Tavolo tavolo;
	
	
	public Event(Duration time, EventType type, int numPersone, Duration durata, double tolleranza, Tavolo tavolo) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo = tavolo;
	}
	
	
	public Duration getTime() {
		return time;
	}
	public void setTime(Duration time) {
		this.time = time;
	}
	public int getNumPersone() {
		return numPersone;
	}
	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}
	public Duration getDurata() {
		return durata;
	}
	public void setDurata(Duration durata) {
		this.durata = durata;
	}
	public double getTolleranza() {
		return tolleranza;
	}
	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}
	public Tavolo getTavolo() {
		return tavolo;
	}
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}


	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", numPersone=" + numPersone + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + ", tavolo=" + tavolo + "]";
	}
	
	
}
