package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;








import users.BankInfo;
import users.CardInfo;
import users.LoginInfo;
import users.UserInfo;
import custcomConnection.CustomMongoConnection;

public class CustomMongoOperations {
	
	DBCollection coll;
	DBCollection coll2;
	DBCollection coll3;
	DBCollection coll4;
	public CustomMongoOperations(){
		
		CustomMongoConnection cust1= new CustomMongoConnection();
		coll=cust1.getMongoCollection1();
		coll2=cust1.getMongoCollection2();
		coll3=cust1.getMongoCollection3();
		coll4=cust1.getMongoCollection4();
	}
	
	public String getNewId(){
		
		
		String newUserId=null;
		String user_id=null;
		//BasicDBObject searchQuery = new BasicDBObject();
		DBCursor curr=coll.find().sort(new BasicDBObject("userid", -1)).limit(1);
		UserInfo user = new UserInfo();	
		
	      while(curr.hasNext()) {
		   System.out.println("inside cursor");
		
			DBObject obj = curr.next();
			
			user_id = obj.get("userid").toString();
			user.setUser_id(user_id);
			System.out.println("NEXT User ID="+user.getUser_id());
		  }
	      
	  newUserId= user_id.substring(1);
	  long tempLong= Long.parseLong(newUserId);
	  newUserId="u"+(tempLong+1);
	  System.out.println("New ID generated"+newUserId);
	  return newUserId;				      

	}
	
	public void addUser(UserInfo user){
		
		
		BasicDBObject doc = new BasicDBObject(); 
		doc.put("userid", user.getUser_id()); 
		doc.put("email",user.getEmail() ); 
		doc.put("password", user.getPassword()); 
		doc.put("created_at", user.getCreated_at()); 
		doc.put("updated_at",user.getUpdated_at()); 
		coll.insert(doc);
		
			
	}
	
	public UserInfo getUser(String matchuserid){
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userid", matchuserid);
		DBCursor cursor = coll.find(searchQuery);
		UserInfo user = new UserInfo();	
			
		      while(cursor.hasNext()) {
			   System.out.println("inside cursor");
			
				DBObject obj = cursor.next();
				
				String user_id = obj.get("userid").toString();
				String email = obj.get("email").toString();
				String password = obj.get("password").toString();
				String created_at = obj.get("created_at").toString();
				String updated_at = obj.get("updated_at").toString();

			
				user.setUser_id(user_id);
				user.setEmail(email);
				user.setPassword(password);
				user.setCreated_at(created_at);
				user.setUpdated_at(updated_at);
				
				System.out.println("User ID="+user.getUser_id());
				System.out.println("Email="+user.getEmail());
				System.out.println("Password="+user.getPassword());
				System.out.println("Created at"+user.getCreated_at());
				System.out.println("Updated at="+ user.getUpdated_at());
		      }
		      
		return user;				      
	}
	public UserInfo updateUser(UserInfo tempUser){
		  BasicDBObject updateQuery = new BasicDBObject();
		  updateQuery.append("$set", new BasicDBObject().append("email", tempUser.getEmail())
				  .append("password", tempUser.getPassword())
				  .append("updated_at", tempUser.getUpdated_at()));
	 
		  BasicDBObject searchQuery3 = new BasicDBObject();
		  searchQuery3.append("userid", tempUser.getUser_id());
	 
		  coll.updateMulti(searchQuery3, updateQuery);
		  
		  
		  return getUser(tempUser.getUser_id());
	}
	/*-------------user end-----------------*/
	
	public String getNewCardId(){
		
		
		String newCardId=null;
		String card_id=null;
		//BasicDBObject searchQuery = new BasicDBObject();
		DBCursor curr=coll2.find().sort(new BasicDBObject("card_id", -1)).limit(1);
		CardInfo card = new CardInfo();	
		
	      while(curr.hasNext()) {
		   System.out.println("inside cursor");
		
			DBObject obj = curr.next();
			
			card_id = obj.get("card_id").toString();
			card.setCard_id(card_id);
			System.out.println("NEXT Card ID="+card.getCard_id());
		  }
	      
	  newCardId= card_id.substring(1);
	  long tempLong= Long.parseLong(newCardId);
	  newCardId="c"+(tempLong+1);
	  System.out.println("New ID generated"+newCardId);
	  return newCardId;				      

	}
	
	public CardInfo addCard(CardInfo card, String user_id){
		
		
		BasicDBObject doc = new BasicDBObject(); 
		CardInfo new_card= new CardInfo();
		
		doc.put("user_id", user_id);
		doc.put("card_id",card.getCard_id() ); 
		doc.put("card_name", card.getCard_name()); 
		doc.put("card_no", card.getCard_no()); 
		doc.put("expiration_date",card.getExpiration_date());
		
		
		coll2.insert(doc);
		
		
		new_card.setCard_id(card.getCard_id());
		new_card.setCard_name(card.getCard_name());
		new_card.setCard_no(card.getCard_no());
		new_card.setExpiration_date(card.getExpiration_date());
		
			
		return new_card;
	}
	
	public ArrayList<CardInfo> getCardDetails(String matchuserid) throws IOException {

		
		ArrayList<CardInfo> cardDetailsList = new ArrayList<CardInfo>();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("user_id", matchuserid);
		DBCursor cursor = coll2.find(searchQuery);
		
			System.out.println("outside cursor");
		      while(cursor.hasNext()) {
			   System.out.println("inside cursor");
			
				DBObject obj = cursor.next();
				CardInfo card = new CardInfo();	
				String card_id = obj.get("card_id").toString();
				String card_name = obj.get("card_name").toString();
				String card_no = obj.get("card_no").toString();
				String expiration_date = obj.get("expiration_date").toString();
								
				card.setCard_id(card_id);
				card.setCard_name(card_name);
				card.setCard_no(card_no);
				card.setExpiration_date(expiration_date);
				
				cardDetailsList.add(card);
				
				
				System.out.println("Card ID="+card.getCard_id());
				System.out.println("Card Name="+card.getCard_name());
				System.out.println("Card Number="+card.getCard_no());
				System.out.println("Expiration Date="+card.getExpiration_date());
				
				
		      }
		      System.out.println("Print ALl values=");
		      ListIterator<CardInfo> it= cardDetailsList.listIterator();
		      while(it.hasNext()){
		    	System.out.println(it.next()+" ");  
		      }
	      System.out.println(cardDetailsList);
	return cardDetailsList;
 	}
	
	
	
	
	public void deleteCard(String user_id, String card_id) throws IOException {
		// TODO Auto-generated method stub
		
		BasicDBObject document = new BasicDBObject();
		document.put("card_id", card_id);
		coll2.remove(document);
		
				
	}

	public String getNewLoginId() {
		// TODO Auto-generated method stub
		String newLoginId=null;
		String login_id=null;
		//BasicDBObject searchQuery = new BasicDBObject();
		DBCursor curr=coll3.find().sort(new BasicDBObject("login_id", -1)).limit(1);
		LoginInfo login = new LoginInfo();	
		
	      while(curr.hasNext()) {
		   System.out.println("inside cursor");
		
			DBObject obj = curr.next();
			
			login_id = obj.get("login_id").toString();
			login.setLogin_id(login_id);
			System.out.println("NEXT Login ID="+login.getLogin_id());
		  }
	      
	  newLoginId= login_id;
	  long tempLong= Long.parseLong(newLoginId);
	  tempLong=tempLong+1;
	  
	  newLoginId= Long.toString(tempLong);
	  System.out.println("New ID generated"+newLoginId);
	  return newLoginId;		
		
	}

	public LoginInfo addLogin(LoginInfo login, String user_id) {
		// TODO Auto-generated method stub
		BasicDBObject doc = new BasicDBObject(); 
		LoginInfo new_login= new LoginInfo();
		
		doc.put("user_id", user_id);
		doc.put("login_id",login.getLogin_id() ); 
		doc.put("login", login.getLogin()); 
		doc.put("password", login.getPassword()); 
		doc.put("url",login.getUrl());
		
		
		coll3.insert(doc);
		
		
		new_login.setLogin_id(login.getLogin_id());
		new_login.setLogin(login.getLogin());
		new_login.setPassword(login.getPassword());
		new_login.setUrl(login.getUrl());
		
			
		return new_login;
	} 
	
public ArrayList<LoginInfo> getLoginDetails(String matchuserid) throws IOException {

		
		ArrayList<LoginInfo> loginDetailsList = new ArrayList<LoginInfo>();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("user_id", matchuserid);
		DBCursor cursor = coll3.find(searchQuery);
		
			System.out.println("outside cursor");
		      while(cursor.hasNext()) {
			   System.out.println("inside cursor");
			
				DBObject obj = cursor.next();
				LoginInfo login_ = new LoginInfo();	
				String login_id = obj.get("login_id").toString();
				String login = obj.get("login").toString();
				String password = obj.get("password").toString();
				String url = obj.get("url").toString();
								
				login_.setLogin_id(login_id);
				login_.setLogin(login);
				login_.setPassword(password);
				login_.setUrl(url);
				
				loginDetailsList.add(login_);
				
				
				System.out.println("Login ID="+login_.getLogin_id());
				System.out.println("Login ="+login_.getLogin());
				System.out.println("Password="+login_.getPassword());
				System.out.println("URL="+login_.getUrl());
				
				
		      }
		      System.out.println("Print ALl values=");
		      ListIterator<LoginInfo> it= loginDetailsList.listIterator();
		      while(it.hasNext()){
		    	System.out.println(it.next()+" ");  
		      }
	      System.out.println(loginDetailsList);
	return loginDetailsList;
 	}
	
	
	
	
	public void deleteLogin(String user_id, String login_id) throws IOException {
		// TODO Auto-generated method stub
		
		BasicDBObject document = new BasicDBObject();
		document.put("login_id", login_id);
		coll3.remove(document);
		
				
	}

	
	public String getNewBankId() {
		// TODO Auto-generated method stub
		String newBankId=null;
		String bank_id=null;
		//BasicDBObject searchQuery = new BasicDBObject();
		DBCursor curr=coll4.find().sort(new BasicDBObject("ba_id", -1)).limit(1);
		BankInfo bank = new BankInfo();	
		
	      while(curr.hasNext()) {
		   System.out.println("inside cursor");
		
			DBObject obj = curr.next();
			
			bank_id = obj.get("ba_id").toString();
			bank.setBa_id(bank_id);
			System.out.println("NEXT Bank ID="+bank.getBa_id());
		  }
	      
	  newBankId= bank_id;
	  long tempLong= Long.parseLong(newBankId);
	  tempLong=tempLong+1;
	  
	  newBankId= Long.toString(tempLong);
	  System.out.println("New ID generated"+newBankId);
	  return newBankId;		
		
	}
	public BankInfo addBank(BankInfo bank, String user_id) {
		// TODO Auto-generated method stub
		BasicDBObject doc = new BasicDBObject(); 
		BankInfo new_bank= new BankInfo();
		
		doc.put("user_id", user_id);
		doc.put("ba_id",bank.getBa_id() ); 
		doc.put("account_name", bank.getAccount_name()); 
		doc.put("account_number", bank.getAccount_number()); 
		doc.put("routing_number",bank.getRouting_number());
		
		
		coll4.insert(doc);
		
		
		new_bank.setBa_id(bank.getBa_id());
		new_bank.setAccount_name(bank.getAccount_name());
		new_bank.setAccount_number(bank.getAccount_number());
		new_bank.setRouting_number(bank.getRouting_number());
		
			
		return new_bank;
	} 
	
public ArrayList<BankInfo> getBankDetails(String matchuserid) throws IOException {

		
		ArrayList<BankInfo> bankDetailsList = new ArrayList<BankInfo>();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("user_id", matchuserid);
		DBCursor cursor = coll4.find(searchQuery);
		
			System.out.println("outside cursor");
		      while(cursor.hasNext()) {
			   System.out.println("inside cursor");
			
				DBObject obj = cursor.next();
				BankInfo bank_ = new BankInfo();	
				String bank_id = obj.get("ba_id").toString();
				String account_name = obj.get("account_name").toString();
				String account_number = obj.get("account_number").toString();
				String routing_number = obj.get("routing_number").toString();
								
				bank_.setBa_id(bank_id);
				bank_.setAccount_name(account_name);
				bank_.setAccount_number(account_number);
				bank_.setRouting_number(routing_number);
				
				bankDetailsList.add(bank_);
				
				
				System.out.println("Bank ID="+bank_.getBa_id());
				System.out.println("Accout Name ="+bank_.getAccount_name());
				System.out.println("Account Number="+bank_.getAccount_number());
				System.out.println("ROuting Number="+bank_.getRouting_number());
				
				
		      }
		      System.out.println("Print ALl values=");
		      ListIterator<BankInfo> it= bankDetailsList.listIterator();
		      while(it.hasNext()){
		    	System.out.println(it.next()+" ");  
		      }
	      System.out.println(bankDetailsList);
	return bankDetailsList;
 	}
	
	
	
	
	public void deleteBank(String user_id, String bank_id) throws IOException {
		// TODO Auto-generated method stub
		
		BasicDBObject document = new BasicDBObject();
		document.put("ba_id", bank_id);
		System.out.println("Just before deleteing bank");
		coll4.remove(document);
		
				
	}
}
