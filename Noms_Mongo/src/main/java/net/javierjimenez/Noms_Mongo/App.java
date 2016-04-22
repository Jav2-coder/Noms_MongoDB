package net.javierjimenez.Noms_Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/noms.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/noms.css").toExternalForm());
			primaryStage.setTitle("Un altre cop noms!");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

/*public class App {
	public static void main(String[] args) {
		MongoClient h = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds017231.mlab.com:17231/noms_mongodb"));

		MongoDatabase i = h.getDatabase("noms_mongodb");

		MongoCollection<Document> j = i.getCollection("noms");

		h.close();

		System.out.print("fin");
	}
}*/
