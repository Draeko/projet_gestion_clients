package principal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.text.SimpleDateFormat;

public class exec {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Personne test = new Personne().recuperer("jbeauduffe@mail.com");
		// Personne test2 = new Personne().recuperer("tespinasse@mail.com");
		// CodePostal cp = new CodePostal().recuperer("76000");
		//
		// afficher(test);
		// afficher(test2);
		// afficher(cp);

		new Personne("Beauduffe", "Julien",
				java.sql.Date.valueOf("1985-05-13"),
				Civilite.Monsieur, "jbeauduffe@mail.com", "machin");
	}

	public static <T> void afficher(T obj) {
		for (Method m : obj.getClass().getDeclaredMethods()) {
			if (m.getName().startsWith("get")) {
				try {
					System.out.println(m.getName().replace("get", "") + " : \t"
							+ m.invoke(obj, null));
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println();
	}
}
