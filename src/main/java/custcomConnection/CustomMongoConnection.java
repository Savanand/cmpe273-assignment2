package custcomConnection;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class CustomMongoConnection {

	
		public DBCollection getMongoCollection1(){
			
			String textUri = "mongodb://aniketSavanand:aniket123@ds049130.mongolab.com:49130/asg2";
			MongoClientURI uri = new MongoClientURI(textUri);
			MongoClient mongo=null;
			try {
				mongo = new MongoClient(uri);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB db = mongo.getDB("asg2");
		DBCollection coll = db.getCollection("collection1");
		
		return coll;
			
		}
		
		public DBCollection getMongoCollection2(){
			
			String textUri = "mongodb://aniketSavanand:aniket123@ds049130.mongolab.com:49130/asg2";
			MongoClientURI uri = new MongoClientURI(textUri);
			MongoClient mongo=null;
			try {
				mongo = new MongoClient(uri);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB db = mongo.getDB("asg2");
		DBCollection coll = db.getCollection("collection2");
		
		return coll;
			
		}
		
		public DBCollection getMongoCollection3(){
			
			String textUri = "mongodb://aniketSavanand:aniket123@ds049130.mongolab.com:49130/asg2";
			MongoClientURI uri = new MongoClientURI(textUri);
			MongoClient mongo=null;
			try {
				mongo = new MongoClient(uri);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB db = mongo.getDB("asg2");
		DBCollection coll = db.getCollection("collection3");
		
		return coll;
			
		}
		
		public DBCollection getMongoCollection4(){
			
			String textUri = "mongodb://aniketSavanand:aniket123@ds049130.mongolab.com:49130/asg2";
			MongoClientURI uri = new MongoClientURI(textUri);
			MongoClient mongo=null;
			try {
				mongo = new MongoClient(uri);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB db = mongo.getDB("asg2");
		DBCollection coll = db.getCollection("collection4");
		
		return coll;
			
		}
}
