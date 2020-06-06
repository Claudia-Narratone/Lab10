package it.polito.tdp.bar.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.w3c.dom.events.EventException;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	//status del mondo
	private List<Tavolo> tavoli;
	
	//parametri della simulazione
	private int NUM_EVENTI=2000;
	private int T_MIN_ARRIVO_MAX=10;
	private int NUM_PERSONE_MAX=10;
	private int DURATA_MIN=60;
	private int DURATA_MAX=120;
	private double TOLLERANZA_MAX=0.9;
	private double OCCUPAZIONE_MIN=0.5;
	
	//valori output
	private Statistica stat;
	
	//coda degli eventi
	private PriorityQueue<Event> queue;
	
	public void caricaTavoli() {
			
			this.tavoli=new ArrayList<Tavolo>();
			
			aggiungiTavolo(2, 10);
			aggiungiTavolo(4, 8);
			aggiungiTavolo(4, 6);
			aggiungiTavolo(5, 4);
			
			// ordina i tavoli dal più piccolo al più grande, così facilito le ricerche
					Collections.sort(this.tavoli, new Comparator<Tavolo>() {
						@Override
						public int compare(Tavolo o1, Tavolo o2) {
							return o1.getnPosti() - o2.getnPosti();
						}
					});
			
		}
	
	public void caricaEventi() {
		Duration arrivo=Duration.ofMinutes(0); //non ci sono orari dichiarati, parto a contare da zero
		
		for(int i=0; i<NUM_EVENTI; i++) {
			
			int numPersone=(int)(Math.random()*NUM_PERSONE_MAX+1);
			
			Duration durata=Duration.ofMinutes(DURATA_MIN+(int)(Math.random()*(DURATA_MAX-DURATA_MIN)));
			
			double tolleranza=Math.random()*TOLLERANZA_MAX;
			
			Event e=new Event(arrivo, EventType.ARRIVO_GRUPPO_CLIENTI, numPersone, durata, tolleranza, null);
			
			queue.add(e);
			
			arrivo=arrivo.plusMinutes(1+(int)(Math.random()*T_MIN_ARRIVO_MAX));
					
		}
	}
	

	public void init() {
		caricaTavoli();
		
		this.queue=new PriorityQueue<Event>();
		caricaEventi();
		
		this.stat=new Statistica();
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e=queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		
		switch (e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			stat.addTotClienti(e.getNumPersone());
			
			Tavolo trovato=null;
			for(Tavolo t:tavoli) {
				if(!t.isOccupato() && t.getnPosti()>=e.getNumPersone() && e.getNumPersone()>=(t.getnPosti()*OCCUPAZIONE_MIN)) {
					trovato=t;
					break;
				}
			}
			
			if(trovato!=null) {
				//tavolo libero -> si siedono a un tavolo
				System.out.format("Sedute %d persone a tavolo da %d\n", e.getNumPersone(), trovato.getnPosti());
				trovato.setOccupato(true);
				stat.addClientiSoddisfatti(e.getNumPersone());
				//genera evento futuro di un tavolo che viene liberato
				queue.add(new Event(e.getTime().plus(e.getDurata()), EventType.TAVOLO_LIBERATO, e.getNumPersone(), e.getDurata(), e.getTolleranza(), trovato));
			
			}else {
				//no tavolo disponibile, accettano il bancone?
				double bancone=Math.random();
				if(bancone<=e.getTolleranza()) {
					//accettano
					stat.addClientiSoddisfatti(e.getNumPersone());
				}else {
					//non accettano
					stat.addClientiInsoddisfatti(e.getNumPersone());
				}
			}
			break;
			
		case TAVOLO_LIBERATO:
			e.getTavolo().setOccupato(false);
			break;
		}
		
	}
	
	private void aggiungiTavolo(int num, int nPosti) {
		for(int i=0; i<=num; i++) {
			Tavolo tavolo=new Tavolo(nPosti, false);
			tavoli.add(tavolo);
		}	
	}

	public Statistica getStatistica() {
		return stat;
	}
}
