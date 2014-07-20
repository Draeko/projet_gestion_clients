package principal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class DAO<T> {

	private Connection connexion = BDD.getInstance();

	/**
	 * @param obj
	 * @param param : utilisation WHERE clé_primaire = param
	 * @return
	 */
	protected T recuperer(T obj, Object param) {
		ResultSet collection = obtenirData("t_"
				+ obj.getClass().getSimpleName(), param);
		if (collection != null) {
			for (Method methode : obj.getClass().getDeclaredMethods()) {
				if (methode.getName().startsWith("set")) {
					try {
						methode.invoke(
								obj,
								collection.getObject(methode.getName()
										.replace("set", "").toLowerCase()));
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Identifiant inconnu : "
					+ obj.getClass().getSimpleName() + "(" + param.toString()
					+ ")");
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	protected ObservableList<T> recupererTout(T obj) {
		ObservableList<T> listeObjet = FXCollections.observableArrayList();
		String nomTable = "t_" + obj.getClass().getSimpleName();
		try {
			Statement statement = this.connexion.createStatement();
			ResultSet collection = statement.executeQuery("select * from obtenir_nom_clef_primaire('"+nomTable+"');");
			collection.next();
			String requete = "SELECT "+ collection.getObject(1) + " FROM " + nomTable + ";";
			collection = statement.executeQuery(requete);
			
			while(collection.next()) {
				listeObjet.add(recuperer((T) obj.getClass().newInstance(), collection.getObject(1)));
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return listeObjet;
	}

	private ResultSet obtenirData(String nomTable, Object param) {
		Boolean nonVide = false;
		ResultSet collection = null;
		try {
			Statement statement = this.connexion.createStatement();
			collection = statement
					.executeQuery("SELECT * FROM recuperer_ligne("
							+ "'" + nomTable + "', "
							+ "(select * from obtenir_nom_clef_primaire("
								+ "'"+ nomTable + "')), "
							+ "'" + param.toString()+ "', " 
							+ "NULL::" + nomTable + ");");
			if (collection.isBeforeFirst()) {
				nonVide = true;
				collection.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (nonVide)
			return collection;
		else
			return null;
	}

	protected void ajouter(T obj) {
		String nomTable = "t_" + obj.getClass().getSimpleName();
		String insertValues = "";
		String insertChamps = "";
		List<CoupleChampsValeurs> listeCouple = new ArrayList<>();
		for (Method methode : obj.getClass().getDeclaredMethods()) {
			if (methode.getName().startsWith("get")) {
				try {
					String champ = methode.getName().replace("get", "");
					String valeur = "";
					if (methode.getReturnType().getSimpleName()
							.equals("String")) {
						valeur = methode.invoke(obj, null).toString();
					}
					if (methode.getReturnType().getSimpleName()
							.equals("Timestamp")) {
						valeur = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
								.format(methode.invoke(obj, null));
					}
					if (methode.getReturnType().getSimpleName().equals("Date")) {
						valeur = new SimpleDateFormat("yyyy-MM-dd")
								.format(methode.invoke(obj, null));
					}
					listeCouple.add(new CoupleChampsValeurs(champ, valeur));
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		for (CoupleChampsValeurs couple : listeCouple) {
			insertChamps += couple.getChamp() + ",";
			insertValues += "'" + couple.getValeur() + "',";
		}
		insertChamps = insertChamps.substring(0, insertChamps.length() - 1);
		insertValues = insertValues.substring(0, insertValues.length() - 1);

		try {
			Statement statement = this.connexion.createStatement();
			String requete = "INSERT INTO " + nomTable + "("
					+ insertChamps + ") VALUES(" + insertValues + ");";
			statement.executeQuery(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public T mettreAJour(T objet, Object[] params) {
		
		return objet;
	}
	
	/* 
	 * 
	 * public T mettreAJour(T obj);
	 * 
	 * public T supprimmer(T obj);
	 */

}
