package principal;

import javafx.beans.property.SimpleStringProperty;

public class CodePostal extends DAO<CodePostal> {
	private final SimpleStringProperty code;
	
	public CodePostal() {
		code = new SimpleStringProperty();
	}
	
	public CodePostal recuperer(String code) {
		return super.recuperer(this, code);
	}
	
	public String getCode() {
		return code.get();
	}
	
	public void setCode(String value) {
		code.set(value);
	}
}
