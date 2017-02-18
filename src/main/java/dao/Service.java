package dao;

import erreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

public class Service {

	// Mise a jour des caracteristiques d'un adherent
	// Le booleen indique s'il s'agit d'un nouvel adherent, auquel cas on fait
	// une creation

	public void insertAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent();
			mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	// gestion des adherents
	// Consultation d'un adherent par son numero
	// Fabrique et renvoie un objet adherent contenant le resultat de la requete
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {
		String mysql = "select * from adherent where numero_adherent=" + numero;
		List<Adherent> mesAdh = consulterListeAdherents(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	}

	// Consultation des adherents
	// Fabrique et renvoie une liste d'objets adherent contenant le resultat de
	// la requete BDD
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent";
		return consulterListeAdherents(mysql);
	}

	private List<Adherent> consulterListeAdherents(String mysql) throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cree un stage
				Adherent unA = new Adherent();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				// On incremente tous les 3 champs
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// Mise a jour des caracteristiques d'une oeuvre
	// Le booleen indique s'il s'agit d'une nouvelle oeuvre, auquel cas on fait
	// une creation

	public void insertOeuvre(Oeuvrevente uneOeuvre) throws MonException {
		String mysql;
		List<Object> rs;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "select id_proprietaire from proprietaire where nom_proprietaire = '"+uneOeuvre.getProprietaire().getNomProprietaire()+"'";
			rs = DialogueBd.lecture(mysql);
			mysql = "insert into oeuvrevente  (titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire)  " + "values ('"
					+ uneOeuvre.getTitreOeuvrevente() + "','"
                    + "L" + "','"
                    + uneOeuvre.getPrixOeuvrevente() + "','"
					+ Integer.parseInt(rs.get(0).toString())+ "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	// Consultation des oeuvres
	// Fabrique et renvoie une liste d'objets oeuvres contenant le resultat de
	// la requete BDD
	public List<Oeuvrevente> consulterListeOeuvres() throws MonException {
		String mysql = "SELECT  titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
				" FROM `oeuvrevente` as x,`proprietaire` as y " +
				"WHERE x.id_proprietaire = y.id_proprietaire ";
		return consulterListeOeuvres(mysql);
	}

    public List<Oeuvrevente> consulterListeOeuvresDisponibles() throws MonException {
        String mysql = "SELECT  titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
                " FROM `oeuvrevente` as x,`proprietaire` as y " +
                "WHERE x.id_proprietaire = y.id_proprietaire AND x.etat_oeuvrevente = 'L' ";
        return consulterListeOeuvres(mysql);
    }

	private List<Oeuvrevente> consulterListeOeuvres(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvres = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cree un stage
				Oeuvrevente unA = new Oeuvrevente();

				// il faut redecouper la liste pour retrouver les lignes
				unA.setTitreOeuvrevente(rs.get(index + 0).toString());
				unA.setPrixOeuvrevente(Float.parseFloat(rs.get(index + 1).toString()));
				unA.getProprietaire().setNomProprietaire(rs.get(index + 2).toString());
				unA.getProprietaire().setPrenomProprietaire(rs.get(index + 3).toString());

				// On incremente tous les 4 champs
				index = index + 4;
				mesOeuvres.add(unA);
			}
			return mesOeuvres;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

    // Consultation des proprietaire
    // Fabrique et renvoie une liste d'objets proprietaire contenant le resultat de
    // la requete BDD
    public List<Proprietaire> consulterListeProprietaire() throws MonException {
        String mysql = "SELECT * FROM proprietaire ";
        return consulterListeProprietaire(mysql);
    }

    private List<Proprietaire> consulterListeProprietaire(String mysql) throws MonException {
        List<Object> rs;
        List<Proprietaire> mesProprietaires = new ArrayList<Proprietaire>();
        int index = 0;
        try {
            DialogueBd unDialogueBd = DialogueBd.getInstance();
            rs = DialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On cree un stage
                Proprietaire unA = new Proprietaire();

                // il faut redecouper la liste pour retrouver les lignes
                unA.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
                unA.setNomProprietaire(rs.get(index + 1).toString());
                unA.setPrenomProprietaire(rs.get(index + 2).toString());

                // On incremente tous les 3 champs
                index = index + 3;
                mesProprietaires.add(unA);

            }
            return mesProprietaires;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

	// Mise a jour des caracteristiques d'une oeuvre
	// Le booleen indique s'il s'agit d'une nouvelle oeuvre, auquel cas on fait
	// une creation

	public void insertReservation(Reservation uneResa) throws MonException {
		String mysql;
		Object rs1;
		Object rs2;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "select id_oeuvrevente from oeuvrevente where titre_oeuvrevente = '" + uneResa.getOeuvrevente().getTitreOeuvrevente() + "'";
			rs1 = DialogueBd.lecture(mysql);
			mysql = "select id_adherent from adherent where nom_adherent = '" + uneResa.getAdherent().getNomAdherent() + "'";
			rs2 = DialogueBd.lecture(mysql);
			mysql = "insert into reservation (id_oeuvrevente, id_adherent, date_reservation, statut)  " + "values ('"
					+ rs1.toString() + "','"
					+ rs2.toString() + "','"
					+ uneResa.getDate() + "','"
					+ "confirmee" + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	public void supprimerOeuvre(String titre) throws MonException {
		String mysql = "DELETE from oeuvrevente WHERE titre_oeuvrevente='" + titre + "'";
		supprimer(mysql);
	}

	public void supprimerAdherent(String id) throws MonException {
		String mysql = "DELETE from adherent WHERE id_adherent='" + id + "'";
		supprimer(mysql);
	}

	public void supprimer(String mysql) throws MonException {

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}

	}

}
