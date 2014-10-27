package users;

import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;


import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;







import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

import org.apache.http.auth.AuthScope;


import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;




import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;






import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.validation.Valid;

import com.mongodb.*;

import dao.CustomMongoOperations;

import java.io.IOException;
import java.net.*;

@RestController
public class UserController {

  //  private final AtomicLong counter = new AtomicLong();
//    private final long new_id= counter.incrementAndGet();
	private String ig_gen;
	private long id_no=12344;
	private long lib_card_id=34566;
	private long web_login_id=45677;
	private long bank_login_id=5001233;
   // private final List li= new ArrayList();
    Map<String, UserInfo> userData = new HashMap<String, UserInfo>();
Map<String, List<Map<String,CardInfo>>> cardData = new HashMap<String, List<Map<String,CardInfo>>>();
Map<String, List<Map<String,LoginInfo>>> loginData = new HashMap<String, List<Map<String,LoginInfo>>>();

Map<String, List<Map<String,BankInfo>>> bankData = new HashMap<String, List<Map<String,BankInfo>>>();


/*--------------------Add new User--------------------------*/

    @RequestMapping(value="/users", method=RequestMethod.POST)
	@ResponseBody
   public UserInfo update(@Valid @RequestBody UserInfo userinfo) throws UnknownHostException, MongoException{


//id_no= id_no+1;
//String user_id= "u"+Long.toString(id_no);	
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
String created_at= sdf.format(date);
String updated_at= sdf.format(date);

// find new Id

CustomMongoOperations op1= new CustomMongoOperations();
String user_id=op1.getNewId();

UserInfo new_user=new UserInfo(user_id, userinfo.getEmail(), userinfo.getPassword(), created_at, updated_at);
    	userData.put(user_id,new_user);

    	
    	
    	
   // start persisting UserInfo Object
    	
    	op1.addUser(new_user);
    	
    	
   //end persisting object 	
	return new_user;
}

/*------------------Get one user----------------------------*/
    @RequestMapping(value="/users/{user_id}", 			method=RequestMethod.GET)
   public UserInfo view(@PathVariable("user_id") String user_id){
	System.out.println(user_id);
	
	// start getting a user info
	CustomMongoOperations op1= new CustomMongoOperations();
	UserInfo user= op1.getUser(user_id);
	
	// end getting a user info
	return user;
	
	

/*Iterator it = li.listIterator();

while (it.hasNext()) {
		System.out.println(it.next());
	}
*/
/*	while (it.hasNext()) {
		UserInfo temp= (UserInfo)it.next();
		if(temp.getUser_id().equals(user_id)){
			System.out.println("match found");
			curr_user=new UserInfo(
				temp.getUser_id(), 									temp.getEmail(),
				temp.getPassword(),
				temp.getCreated_at(),
				temp.getUpdated_at());

		 	break;   	
		}
		System.out.println(it.next());
	}
*/
		/*	System.out.println("no match found");

	return curr_user;*/
}
/*--------------------Update User-----------------------------*/
    @RequestMapping(value="/users/{user_id}", 			method=RequestMethod.PUT)
@ResponseBody

public UserInfo edit(@Valid @RequestBody UserInfo userinfo,@Valid @PathVariable("user_id") String user_id){
	System.out.println(user_id);
	UserInfo up_user= new UserInfo();
	up_user.setUser_id(user_id);
	up_user.setEmail(userinfo.getEmail());
	up_user.setPassword(userinfo.getPassword());
	
	
	Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
	String updated_at= sdf.format(date);
	up_user.setUpdated_at(updated_at);
	//userData.put(user_id, up_user);
	//return userData.get(user_id);
	
	
	// start updating user
	
	CustomMongoOperations op1= new CustomMongoOperations();
	UserInfo user= op1.updateUser(up_user);
	
	
	return user;
	
// end upadating user


}


/*----------------------------End of User Module-------*/


/*---------------------Add a Library Card---------------------*/



@RequestMapping(value="/users/{user_id}/idcards", method=RequestMethod.POST)
@ResponseBody

   public CardInfo addCard(@Valid @RequestBody CardInfo cardinfo, @Valid @PathVariable("user_id") String user_id){


//lib_card_id= lib_card_id+1;
//String card_id= "c"+Long.toString(lib_card_id);	
/*Calendar date = Calendar.getInstance();
Date date = new Date();
date.add(Calendar.YEAR,1);
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
String expiration_date=sdf.format(date);

*/
	String card_id=null;
	CustomMongoOperations op1= new CustomMongoOperations();
	card_id=op1.getNewCardId();

	
CardInfo card=new CardInfo(card_id, cardinfo.getCard_name(), cardinfo.getCard_no(), cardinfo.getExpiration_date());
/*
int flag=0;
Iterator it = cardData.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if(pairs.getKey().equals(user_id)){
		List<Map<String, CardInfo>> temp_li=(List<Map<String,CardInfo>>)cardData.get(user_id);
		Map<String, CardInfo> map_new_card= new HashMap<String, CardInfo>();
		map_new_card.put(card_id, new_card);
		temp_li.add(map_new_card);
		flag=1;
		break;
	}
        
    }

if(flag==0){
	List<Map<String,CardInfo>> li= new ArrayList<Map<String,CardInfo>>();
	Map<String, CardInfo> map_new_card= new HashMap<String, CardInfo>();
	map_new_card.put(card_id, new_card);
	li.add(map_new_card);
	cardData.put(user_id,li);
}
*/

	//start add new card

CardInfo newCard= op1.addCard(card, user_id);


	//end add new card
	return newCard;
}


/*------------------List all Cards--------------------*/



@RequestMapping(value="/users/{user_id}/idcards", method=RequestMethod.GET)
   public ArrayList<CardInfo> viewAllCards(@PathVariable("user_id") String user_id){

	System.out.println(user_id);
	// start retrieve all cards
	
	CustomMongoOperations op1= new CustomMongoOperations();
	ArrayList<CardInfo> licards=new ArrayList<CardInfo>();
	try {
		licards = op1.getCardDetails(user_id);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//End retrieve all cards
	return licards;
	
	
}
/*------------------Delete a card-------------------*/
@RequestMapping(value="/users/{user_id}/idcards/{card_id}", method=RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteCard(@PathVariable("user_id") String user_id, @PathVariable("card_id") String card_id){

//	System.out.println(user_id);
/*	int flag=0;
Iterator it = cardData.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if(pairs.getKey().equals(user_id)){
		List<Map<String,CardInfo>> temp_li=(List<Map<String,CardInfo>>)cardData.get(user_id);
		
		Iterator it_list = temp_li.listIterator();

		while (it_list.hasNext()) {
			if(((Map<String,CardInfo>)it_list.next()).containsKey(card_id)){
				it_list.remove();
			}		
		}
		break;
	}
        
    }
	*/
	//ArrayList<CardInfo> licards=new ArrayList<CardInfo>();
	
	
	// start delete card
	CustomMongoOperations op1= new CustomMongoOperations();
	try {
		op1.deleteCard(user_id, card_id);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// end delete card
	
	
	
}

/*----------------------------End of Library Module-------*/


/*----------------Add login data------------*/



@RequestMapping(value="/users/{user_id}/weblogins", method=RequestMethod.POST)
@ResponseBody

   public LoginInfo addLogin(@Valid @RequestBody LoginInfo logininfo, @Valid @PathVariable("user_id") String user_id){


/*web_login_id= web_login_id+1;
String login_id= "l"+Long.toString(web_login_id);	
Calendar date = Calendar.getInstance();
Date date = new Date();
date.add(Calendar.YEAR,1);
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
String expiration_date=sdf.format(date);

*/
String login_id=null;
CustomMongoOperations op1= new CustomMongoOperations();
login_id=op1.getNewLoginId();

LoginInfo login=new LoginInfo(login_id, logininfo.getUrl(), logininfo.getLogin(), logininfo.getPassword());

/*int flag=0;
Iterator it = loginData.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if(pairs.getKey().equals(user_id)){
		List<Map<String, LoginInfo>> temp_li=(List<Map<String,LoginInfo>>)loginData.get(user_id);
		Map<String, LoginInfo> map_new_login= new HashMap<String, LoginInfo>();
		map_new_login.put(login_id, new_login);
		temp_li.add(map_new_login);
		flag=1;
		break;
	}
        
    }

if(flag==0){
	List<Map<String,LoginInfo>> li= new ArrayList<Map<String,LoginInfo>>();
	Map<String, LoginInfo> map_new_login= new HashMap<String, LoginInfo>();
	map_new_login.put(login_id, new_login);
	li.add(map_new_login);
	loginData.put(user_id,li);
}
	*/
LoginInfo newLogin= op1.addLogin(login, user_id);


//end add new card
return newLogin;
	
	
}


/*------------------List all Logins--------------------*/



@RequestMapping(value="/users/{user_id}/weblogins", method=RequestMethod.GET)

   public  ArrayList<LoginInfo> viewAllLogins(@PathVariable("user_id") String user_id){

	System.out.println(user_id);
	// start retrieve all cards
	
	CustomMongoOperations op1= new CustomMongoOperations();
	ArrayList<LoginInfo> licards=new ArrayList<LoginInfo>();
	try {
		licards = op1.getLoginDetails(user_id);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//End retrieve all cards
	return licards;
	
	
}
/*------------------Delete a web login-------------------*/
@RequestMapping(value="/users/{user_id}/weblogins/{login_id}", method=RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteLogin(@PathVariable("user_id") String user_id, @PathVariable("login_id") String login_id){

//	System.out.println(user_id);
	/*int flag=0;
Iterator it = loginData.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if(pairs.getKey().equals(user_id)){
		List<Map<String,LoginInfo>> temp_li=(List<Map<String,LoginInfo>>)loginData.get(user_id);
		
		Iterator it_list = temp_li.listIterator();

		while (it_list.hasNext()) {
			if(((Map<String,LoginInfo>)it_list.next()).containsKey(login_id)){
				it_list.remove();
			}		
		}
		break;
	}
        
    }*/
	// start delete card
		CustomMongoOperations op1= new CustomMongoOperations();
		try {
			op1.deleteLogin(user_id, login_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// end delete card
		

	

}

/*----------------------------End of Web Module-------*/


/*----------------Add Bank data------------*/



@RequestMapping(value="/users/{user_id}/bankaccounts", method=RequestMethod.POST)
@ResponseBody

   public BankInfo addbank(@Valid @RequestBody BankInfo bankinfo, @Valid @PathVariable("user_id") String user_id) throws CertificateException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException{


/*bank_login_id= bank_login_id+1;
String ba_id= "b"+Long.toString(bank_login_id);	
/*Calendar date = Calendar.getInstance();
Date date = new Date();
date.add(Calendar.YEAR,1);
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
String expiration_date=sdf.format(date);

*/
	
	/*------------------part II starts-----------------------*/
	
	
	// Set the Accept header
	/*HttpHeaders requestHeaders = new HttpHeaders();
	requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
	
	HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

	String url = "https://www.routingnumbers.info/api/data.json?rn=121000358";
	BankDetails details=null;
	
	
	//--------------------Making accept certificate-----------------
	HttpComponentsClientHttpRequestFactory requestFactory = 
		      new HttpComponentsClientHttpRequestFactory();
	
		    DefaultHttpClient httpClient = (DefaultHttpClient) requestFactory.getHttpClient();
		    TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
		    @Override
		        public boolean isTrusted(X509Certificate[] certificate, String authType) {
		            return true;
		        }
		    };
		    SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy, ALLOW_ALL_HOSTNAME_VERIFIER);
		    httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 8080, sf));
		 		 
		//----------------------end making accepting certificate-----------
	
	// Create a new RestTemplate instance
	RestTemplate restTemplate = new RestTemplate(requestFactory);

	// Add the Jackson message converter
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	
	ResponseEntity<BankDetails> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, BankDetails.class);
	details = responseEntity.getBody();
	
	if(!details.equals(null)){
	System.out.println("Bank/Customer Name obtained from="+ details.getCustomer_name());
	}
	*/
	
	
	
	
	
	/*------------------part II ends-----------------------*/
	String bank_id=null;
	CustomMongoOperations op1= new CustomMongoOperations();
	bank_id=op1.getNewBankId();

	
	
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> entity = restTemplate.getForEntity("http://www.routingnumbers.info/api/data.json?rn=" + bankinfo.getRouting_number(), String.class);
	JsonParser jsonParser = new JacksonJsonParser();
		Map<String,Object> resbody = jsonParser.parseMap(entity.getBody());
	if((resbody.get("code").toString().equals("200"))) {
		bankinfo.setAccount_name(resbody.get("customer_name").toString());
	}
	
	
	
	
BankInfo bank=new BankInfo(bank_id, bankinfo.getAccount_name(), bankinfo.getRouting_number(), bankinfo.getAccount_number());

/*int flag=0;
Iterator it = bankData.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if(pairs.getKey().equals(user_id)){
		List<Map<String, BankInfo>> temp_li=(List<Map<String,BankInfo>>)bankData.get(user_id);
		Map<String, BankInfo> map_new_bank= new HashMap<String, BankInfo>();
		map_new_bank.put(ba_id, new_bank);
		temp_li.add(map_new_bank);
		flag=1;
		break;
	}
        
    }

if(flag==0){
	List<Map<String,BankInfo>> li= new ArrayList<Map<String,BankInfo>>();
	Map<String, BankInfo> map_new_bank= new HashMap<String, BankInfo>();
	map_new_bank.put(ba_id, new_bank);
	li.add(map_new_bank);
	bankData.put(user_id,li);
}
	return new_bank;
	*/

BankInfo newBank= op1.addBank(bank, user_id);


//end add new card
return newBank;
}


/*------------------List all Banks--------------------*/



@RequestMapping(value="/users/{user_id}/bankaccounts", method=RequestMethod.GET)
@ResponseBody

   public ArrayList<BankInfo> viewAllBanks(@PathVariable("user_id") String user_id){

	System.out.println(user_id);
	CustomMongoOperations op1= new CustomMongoOperations();
	ArrayList<BankInfo> licards=new ArrayList<BankInfo>();
	try {
		licards = op1.getBankDetails(user_id);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//End retrieve all cards
	return licards;
	
}
/*------------------Delete a bank login-------------------*/
@RequestMapping(value="/users/{user_id}/bankaccounts/{ba_id}", method=RequestMethod.DELETE)
   public void deleteBank(@PathVariable("user_id") String user_id, @PathVariable("ba_id") String bank_id){

//	System.out.println(user_id);
	/*int flag=0;
Iterator it = bankData.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        if(pairs.getKey().equals(user_id)){
		List<Map<String,BankInfo>> temp_li=(List<Map<String,BankInfo>>)bankData.get(user_id);
		
		Iterator it_list = temp_li.listIterator();

		while (it_list.hasNext()) {
			if(((Map<String,BankInfo>)it_list.next()).containsKey(ba_id)){
				it_list.remove();
			}		
		}
		break;
	}
        
    }
    */
	// start delete card
			CustomMongoOperations op1= new CustomMongoOperations();
			try {
				op1.deleteBank(user_id, bank_id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// end delete card
			
	
}
/*----------------------------End of Bank Module-------*/






}

