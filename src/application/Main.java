package application;
	
import java.io.IOException;
import java.sql.Timestamp;

import conteneur.Individu;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import principal.Civilite;
import principal.Personne;


public class Main extends Application {
	private final TableView<Personne> table = new TableView<>();
	
    private final ObservableList<Personne> data = new Personne().recupererTout();
    		
    		
    		/*FXCollections.observableArrayList(
            new Personne().recuperer("jbeauduffe@mail.com"),
            new Personne().recuperer("tespinasse@mail.com")
        );*/
	
	@Override
	public void start(Stage stage) {
		Pane page = null;
		try {
			page = (Pane) FXMLLoader.load(Main.class.getResource("sample.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(page);
        stage.setTitle("Gestion Clients");
        stage.setWidth(1280);
        stage.setHeight(1024);
 
        
       
        generationGrille(scene);
 
        stage.setScene(scene);
        stage.show();
	}
	
	private void generationGrille(Scene scene) {
		final Label label = new Label("Liste des personnes");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        TableColumn nomCol = new TableColumn("Nom");
        TableColumn prenomCol = new TableColumn("Prenom");
        TableColumn dateNaissanceCol = new TableColumn("Date de naissance");
        TableColumn civiliteCol = new TableColumn("Civilite");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn mdpCol = new TableColumn("Mot de passe");
        TableColumn dateCreationCol = new TableColumn("Date de création");
        
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        dateNaissanceCol.setMinWidth(100);
        civiliteCol.setMinWidth(80);
        emailCol.setMinWidth(150);
        mdpCol.setMinWidth(100);
        dateCreationCol.setMinWidth(150);
 
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissanceCol.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        civiliteCol.setCellValueFactory(new PropertyValueFactory<>("civilite"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        mdpCol.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        dateCreationCol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        
        table.setItems(data);
        table.getColumns().addAll(emailCol, civiliteCol, nomCol, prenomCol, dateNaissanceCol, mdpCol, dateCreationCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(250, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Pane) scene.getRoot()).getChildren().addAll(vbox);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
