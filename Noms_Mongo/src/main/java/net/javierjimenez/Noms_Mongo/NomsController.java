package net.javierjimenez.Noms_Mongo;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

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
		
		Document doc = col.find(or(eq("catala", txtNom.getText()), eq("castella", txtNom.getText()))).first();
		
		@SuppressWarnings("unchecked")
		List<java.lang.String> santos = (List<String>) doc.get("sants");
		
		if(santos != null){
			
			System.out.println(Arrays.toString(santos.toArray()));
			
		} else {
			System.out.println("NOPE");
		}
	}
	
	public void buscarNoms(ActionEvent event) {
		
		//MongoCursor<Document> cur = col.find();
		
	}	
}
