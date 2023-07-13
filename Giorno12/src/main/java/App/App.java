package App;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import DAO.EventoDAO;
import DAO.LocationDAO;
import DAO.PartecipazioneDAO;
import DAO.PersonaDAO;
import entities.Concerto;
import entities.Evento;
import entities.GaraDiAtletica;
import entities.GenereConcerto;
import entities.Location;
import entities.Partecipazione;
import entities.PartitaDiCalcio;
import entities.Persona;
import entities.Sesso;
import entities.StatoPartecipazione;
import entities.TipoEvento;
import util.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		// Creo location
		Location location1 = new Location("Mergellina", "Napoli");
		Location location2 = new Location("Duomo", "Milano");

		// Creo persona
		Persona persona1 = new Persona("Benedetto", "De Stefano", "bds@gmail.com", LocalDate.of(1997, 2, 20),
				Sesso.MASCHIO);
		Persona persona2 = new Persona("Giuseppe", "Petrucci", "gsp@gmail.com", LocalDate.of(1995, 2, 17),
				Sesso.MASCHIO);

		// Creo evento
		Evento Evento1 = new Evento("Mobile World Congress", LocalDate.now(), "Fiera internazionale",
				TipoEvento.PUBBLICO, 10000, location1);
		Evento Evento2 = new Evento("Festa della liquirizia", LocalDate.now(), "Sagra", TipoEvento.PRIVATO, 4000,
				location2);

		// Creo partecipazione
		Partecipazione participazione1 = new Partecipazione(persona1, Evento1, StatoPartecipazione.CONFERMATA);
		Partecipazione participazione2 = new Partecipazione(persona2, Evento2, StatoPartecipazione.DA_CONFERMARE);

		// Creo Concerto
		Concerto concerto1 = new Concerto("Queen The Dark Side", LocalDate.now(), "Concerto musicale",
				TipoEvento.PRIVATO, 2000, location1, GenereConcerto.ROCK, true);
		Concerto concerto2 = new Concerto("Rihanna", LocalDate.now(), "Concerto musicale",
				TipoEvento.PRIVATO, 2000, location1, GenereConcerto.POP, true);


		// Creo partita di calcio
		PartitaDiCalcio partitaDiCalcio = new PartitaDiCalcio("Partita di calcio", LocalDate.now(), "Partita sportiva",
				TipoEvento.PUBBLICO, 5000, location1, "Napoli", "Milan", "Squadra vincitrice", 2, 1);

		// Creo Atleta
		Set<Persona> setAtleti = new HashSet<Persona>();
		setAtleti.add(persona1);
		setAtleti.add(persona2);

		GaraDiAtletica garaDiAtletica = new GaraDiAtletica("Gara di nuoto", LocalDate.now(), "Gara sportiva",
				TipoEvento.PUBBLICO, 1000, location2, setAtleti, persona1);

		// Creo location DAO
		LocationDAO locationDAO = new LocationDAO(em);
		locationDAO.save(location1);
		locationDAO.save(location2);

		// Creo persona DAO
		PersonaDAO personaDAO = new PersonaDAO(em);
		personaDAO.save(persona1);
		personaDAO.save(persona2);

		// Creo evento DAO
		EventoDAO eventoDAO = new EventoDAO(em);
		eventoDAO.save(Evento1);
		eventoDAO.save(Evento2);
		// ***********************************************
		eventoDAO.save(concerto1);
		eventoDAO.save(concerto2);
		eventoDAO.save(partitaDiCalcio);
		eventoDAO.save(garaDiAtletica);


		PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);
		partecipazioneDAO.save(participazione1);
		partecipazioneDAO.save(participazione2);

		// Cerca persona
		Persona findPersona = personaDAO.findById(persona1.getId());
		System.out.println("Persona trovata by ID: " + findPersona);

		// Cerca evento
		Evento findEvento = eventoDAO.findById(Evento1.getId());
		System.out.println("Event trovato by ID: " + findEvento);

		persona1.addPartecipazione(participazione1);
		persona2.addPartecipazione(participazione2);

		List<Concerto> concertiInStreaming = eventoDAO.getConcertiInStreaming(true);
		System.out.println("Concerti in streaming:");
		for (Concerto concerto : concertiInStreaming) {
			System.out.println(concerto);
		}

		List<GenereConcerto> generiConcerto = Arrays.asList(GenereConcerto.CLASSICO, GenereConcerto.POP,
				GenereConcerto.ROCK);
		List<Concerto> concertiPerGenere = eventoDAO.getConcertiPerGenere(generiConcerto);
		System.out.println("Concerti per genere:");
		for (Concerto concerto : concertiPerGenere) {
			System.out.println(concerto);
		}

		em.close();
		emf.close();

	}

}
