package net.javierjimenez.Noms_Mongo;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class NomsController implements Initializable {

	@FXML
	private TextField txtNom;
	@FXML
	private TextField txtSant;
	@FXML
	private Button btnSant;
	@FXML
	private Button btnNom;
	@FXML
	private Label diaSant;
	@FXML
	private ListView<String> llistaNoms;
	
	private MongoClient client;
	
	private MongoCollection<Document> col;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		client = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds017231.mlab.com:17231/noms_mongodb"));

		MongoDatabase db = client.getDatabase("noms_mongodb");
		
		col = db.getCollection("noms");
	}
	
	public void buscarSant(ActionEvent event) {
		
	}
	
	public void buscarNoms(ActionEvent event) {
		
	}	
}
