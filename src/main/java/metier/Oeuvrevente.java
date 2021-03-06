package metier;

import java.io.Serializable;


/**
 * The persistent class for the oeuvrevente database table.
 * 
 */

public class Oeuvrevente implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idOeuvrevente;
	private String etatOeuvrevente;
	private int prixOeuvrevente;
	private String titreOeuvrevente;
	private Proprietaire proprietaire;


	public Oeuvrevente(int idOeuvrevente, String etatOeuvrevente, int prixOeuvrevente, String titreOeuvrevente,
			Proprietaire proprietaire) {
		super();
		this.idOeuvrevente = idOeuvrevente;
		this.etatOeuvrevente = etatOeuvrevente;
		this.prixOeuvrevente = prixOeuvrevente;
		this.titreOeuvrevente = titreOeuvrevente;
		this.proprietaire = proprietaire;
	}

	public Oeuvrevente() {
		proprietaire = new Proprietaire();

	}

	public int getIdOeuvrevente() {
		return this.idOeuvrevente;
	}

	public void setIdOeuvrevente(int idOeuvrevente) {
		this.idOeuvrevente = idOeuvrevente;
	}

	public String getEtatOeuvrevente() {
		return this.etatOeuvrevente;
	}

	public void setEtatOeuvrevente(String etatOeuvrevente) {
		this.etatOeuvrevente = etatOeuvrevente;
	}

	public int getPrixOeuvrevente() {
		return this.prixOeuvrevente;
	}

	public void setPrixOeuvrevente(int prixOeuvrevente) {
		this.prixOeuvrevente = prixOeuvrevente;
	}

	public String getTitreOeuvrevente() {
		return this.titreOeuvrevente;
	}

	public void setTitreOeuvrevente(String titreOeuvrevente) {
		this.titreOeuvrevente = titreOeuvrevente;
	}

	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}
}