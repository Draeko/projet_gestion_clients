package principal;

import java.sql.Date;
import java.sql.Timestamp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Personne extends DAO<Personne> {
	private final SimpleStringProperty nom;
	private final SimpleStringProperty prenom;
	private final SimpleObjectProperty<Date> dateNaissance;
	private final SimpleObjectProperty<Civilite> civilite;
	private final SimpleStringProperty email;
	private final SimpleStringProperty motDePasse;
	private final SimpleObjectProperty<Timestamp> dateCreation;

	public Personne() {
		this.nom = new SimpleStringProperty();
		this.prenom = new SimpleStringProperty();
		this.dateNaissance = new SimpleObjectProperty<Date>(new Date(0));
		this.civilite = new SimpleObjectProperty<Civilite>();
		this.email = new SimpleStringProperty();
		this.motDePasse = new SimpleStringProperty();
		this.dateCreation = new SimpleObjectProperty<Timestamp>(new Timestamp(
				new java.util.Date().getTime()));
	}

	public Personne(String nom, String prenom, Date dateNaissance,
			Civilite civilite, String email, String motDePasse) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.dateNaissance = new SimpleObjectProperty<Date>(dateNaissance);
		this.civilite = new SimpleObjectProperty<Civilite>(civilite);
		this.email = new SimpleStringProperty(email);
		this.motDePasse = new SimpleStringProperty(motDePasse);
		this.dateCreation = new SimpleObjectProperty<Timestamp>(new Timestamp(
				new java.util.Date().getTime()));

		super.ajouter(this);
	}

	/**
	 * @param email
	 *            - Identifiant de la personne à récupérer dans la BDD
	 * @return Retourne un objet Personne rempli avec les données de la BDD
	 */
	public Personne recuperer(String email) {
		return super.recuperer(this, email);
	}
	
	public ObservableList<Personne> recupererTout() {
		return super.recupererTout(this);
	}
	
	// Get & Set /////////////////////////////////////////////////
	public String getNom() {
		return nom.get();
	}

	public void setNom(String value) {
		nom.set(value);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String value) {
		prenom.set(value);
	}

	public Date getDateNaissance() {
		return dateNaissance.get();
	}

	public void setDateNaissance(Object value) {
		dateNaissance.set((Date) value);
	}

	public Civilite getCivilite() {
		return civilite.get();
	}

	public void setCivilite(String value) {
		civilite.set(Civilite.valueOf(value));
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String value) {
		email.set(value);
	}

	public String getMotDePasse() {
		return motDePasse.get();
	}

	public void setMotDePasse(String value) {
		motDePasse.set(value);
	}

	public Timestamp getDateCreation() {
		return dateCreation.get();
	}

	public void setDateCreation(Object value) {
		dateCreation.set((Timestamp) value);
	}
	// Fin Get & Set /////////////////////////////////////////////

}
