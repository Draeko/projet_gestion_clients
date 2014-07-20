/*
 * Exemple de newInstance
 * result.add(typeClasse.getDeclaredConstructor(ResultSet.class).newInstance(collection));
 * 
 */

package principal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BDD {

	private static Connection connexion;
	private static String url = "jdbc:postgresql://127.0.0.1:5432/gestion_client";
	private static String user = "postgres";
	private static String pwd = "pcKC5sty";

	public static Connection getInstance() {
		if (connexion == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connexion = DriverManager.getConnection(url, user, pwd);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connexion;
	}

//	@SuppressWarnings("unchecked")
//	public <T> ObservableList<T> ObtenirT(Class<T> typeClasse, String requete) {
//		ObservableList<T> result = FXCollections.observableArrayList();
//		try {
//			Statement statement = BDD.connexion.createStatement();
//			ResultSet collection = statement.executeQuery(requete);
//			while (collection.next()) {
//				result.add((T) typeClasse.getDeclaredMethod("RemplirObjet",
//						ResultSet.class).invoke(typeClasse.newInstance(),
//						collection));
//			}
//		} catch (IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException | NoSuchMethodException
//				| SecurityException | InstantiationException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	public <T> T RemplirObjet2(T objet, ResultSet data) {
//		for (Field attribut : objet.getClass().getDeclaredFields()) {
//			try {
//				switch (attribut.getType().getSimpleName()) {
//				case "String":
//					attribut.set(objet,
//							data.getString(attribut.getName().toLowerCase()));
//					break;
//				case "Date":
//					attribut.set(objet,
//							data.getTimestamp(attribut.getName().toLowerCase()));
//					break;
//				case "Civilite":
//					attribut.set(objet, Civilite.valueOf(data
//							.getString(attribut.getName().toLowerCase())));
//					break;
//				default:
//					break;
//				}
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return objet;
//	}
//	
//	public <T> T RemplirObjet(T objet, ResultSet data) {
//		for (Method methode : objet.getClass().getDeclaredMethods()) {
//			if (methode.getName().startsWith("set")) {
//				try {
//					methode.invoke(
//							objet,
//							data.getString(methode.getName()
//									.replace("set", "").toLowerCase()));
//				} catch (IllegalAccessException | IllegalArgumentException
//						| InvocationTargetException | SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return objet;
//	}
}
