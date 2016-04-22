package net.javierjimenez.Noms_Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MongoClient h = new MongoClient(new MongoClientURI("mongodb://admin:admin@ds017231.mlab.com:17231/noms_mongodb"));
        
        MongoDatabase i = h.getDatabase("noms_mongodb");
        
        MongoCollection<Document> j = i.getCollection("noms");
        
        h.close();
        
        System.out.print("fin");
    }
}
