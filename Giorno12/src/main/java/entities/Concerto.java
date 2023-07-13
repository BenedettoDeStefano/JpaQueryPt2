package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "concerti")
@Getter
@Setter
@NoArgsConstructor
public class Concerto extends Evento {

	@Enumerated(EnumType.STRING)
	private GenereConcerto genere;

	private boolean inStreaming;

	public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Location location, GenereConcerto genere, boolean inStreaming) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
		this.genere = genere;
		this.inStreaming = inStreaming;
	}
}
