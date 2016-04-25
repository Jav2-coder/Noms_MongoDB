package net.javierjimenez.Noms_Mongo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
		client = new MongoClient(new MongoClientURI("mongodb://admin:admin@ds017231.mlab.com:17231/noms_mongodb"));

		MongoDatabase db = client.getDatabase("noms_mongodb");

		col = db.getCollection("noms");
	}

	@SuppressWarnings("unchecked")
	public void buscarSant(ActionEvent event) {

		diaSant.setText(" ");

		Document doc = col.find(or(eq("catala", txtNom.getText()), eq("castella", txtNom.getText()))).first();

		if (doc == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Problema de Búsqueda");
			alert.setHeaderText("ALERTA: Problema en la búsqueda");
			alert.setContentText("Los datos escritos son erróneos o\nno existe dicho nombre.\nRehaga la búsqueda.");
			alert.showAndWait();
		} else {
			List<java.lang.String> sants = (List<String>) doc.get("sants");

			String valor = "";

			for (String data : sants) {

				valor = valor + "- " + data + " -\n";
			}
			diaSant.setText(valor);
		}
	}

	public void buscarNoms(ActionEvent event) {

		List<Document> noms = new ArrayList<Document>();

		ObservableList<String> lista_nombres = FXCollections.observableArrayList();

		MongoCursor<Document> cur = col.find(in("sants", txtSant.getText())).iterator();

		while (cur.hasNext()) {
			noms.add(cur.next());
		}

		for (int i = 0; i < noms.size(); i++) {

			String cat = (String) noms.get(i).get("catala");
			String cast = (String) noms.get(i).get("castella");

			if (cast == null) {
				cast = "No existe";
			} else if (cat == null) {
				cat = "No existeix";
			}
			lista_nombres.add("Catala: " + cat + " - Castella: " + cast);
		}

		if (lista_nombres.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Problema de Búsqueda");
			alert.setHeaderText("ALERTA: Problema en la búsqueda");
			alert.setContentText("Los datos escritos son erróneos o\nno existe dicho Santo.\nRehaga la búsqueda.");
			alert.showAndWait();
		} else {

			llistaNoms.setItems(lista_nombres);
		}
	}
}
