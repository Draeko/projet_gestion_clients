package principal;

public class CoupleChampsValeurs {
	String Champ;
	String Valeur;
	
	public CoupleChampsValeurs(String champ, String valeur){
		this.Champ = champ;
		this.Valeur = valeur;
	}
	
	public String getChamp() {
		return this.Champ;
	}
	
	public String getValeur() {
		return this.Valeur;
	}
	
	public void setChamp(String value) {
		this.Champ = value;
	}
	
	public void setValeur(String value) {
		this.Valeur = value;
	}

}
