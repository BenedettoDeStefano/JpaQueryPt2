package entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gare_di_atletica")
@Getter
@Setter
@NoArgsConstructor
public class GaraDiAtletica  extends Evento {

	    @OneToMany
	    private Set<Persona> atleti;

	    @ManyToOne
	    private Persona vincitore;

	    public GaraDiAtletica(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
	            int numeroMassimoPartecipanti, Location location, Set<Persona> atleti, Persona vincitore) {
	        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
	        this.atleti = atleti;
	        this.vincitore = vincitore;
	    }
	}
