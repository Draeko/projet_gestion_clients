package principal;

public enum Civilite {
	Monsieur("Monsieur"), Madame("Madame");
	
	private String titre;
	
	Civilite(String titre) {
		this.titre = titre;
	}
	
	public String toString(){
		return titre;
	}
}
