package DAO;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Concerto;
import entities.Evento;
import entities.GenereConcerto;

public class EventoDAO {

	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Elemento salvato");
	}

	public Evento findById(UUID id) {
		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void deleteById(UUID id) {
		Evento found = em.find(Evento.class, id);

		if (found != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(found);
			t.commit();

			System.out.println("Elemento eliminato");
		} else {
			System.out.println("Errore");
		}
	}

	public void refresh(UUID id) {
		Evento found = em.find(Evento.class, id);

		found.setTitolo("WWDC Apple I.n.c");

		System.out.println("Pre Refresh");
		System.out.println(found);

		em.refresh(found);

		System.out.println("Pos Refresh");
		System.out.println(found);
	}

	// ****************************************************

	public List<Concerto> getConcertiInStreaming(boolean inStreaming) {
		String jpql = "SELECT c FROM Concerto c WHERE c.inStreaming = :inStreaming";
		TypedQuery<Concerto> query = em.createQuery(jpql, Concerto.class);
		query.setParameter("inStreaming", inStreaming);
		return query.getResultList();
	}

	public List<Concerto> getConcertiPerGenere(List<GenereConcerto> generiConcerto) {
		String jpql = "SELECT c FROM Concerto c WHERE c.genere IN :generiConcerto";
		TypedQuery<Concerto> query = em.createQuery(jpql, Concerto.class);
		query.setParameter("generiConcerto", generiConcerto);
		return query.getResultList();
	}



}
