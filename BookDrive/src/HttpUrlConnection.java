import static db.JdbcUtil.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpUrlConnection {
	public static void main(String[] args) throws Exception {
//		Connection con = getConnection();
//		insertDAO inDAO = insertDAO.getInstance();
//		inDAO.setConnection(con);
//		inDAO.setinserturl();
		
		addrToCoord("9788961373470");
    }
     
    public static String addrToCoord(String isbn){
         
        String url = "https://dapi.kakao.com/v3/search/book?target=isbn&query="+isbn;
        String json = "";
        try{
            json = getJSONData(url, isbn);
        }catch(Exception e){
             
            e.printStackTrace();
        }
        return json;
    }
 
    private static String getJSONData(String apiUrl, String isbn) throws Exception {
        String jsonString = new String();
        String buf;
        String apikey = "7c28f9da096eaa302f600c9900820d6e"; //apikey
         
        URL url = new URL(apiUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        String auth = "KakaoAK "+apikey;
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Requested-With", "curl");
        conn.setRequestProperty("Authorization", auth);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        while ((buf = br.readLine()) != null) {
            jsonString += buf;
        }
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonString);
            JSONArray memberArray = (JSONArray) jsonObj.get("documents");
//            System.out.println(jsonObj.get("thumbnail"));
            System.out.println("=====Members=====");
            for(int i=0 ; i < memberArray.size(); i++){
                JSONObject tempObj = (JSONObject) memberArray.get(i);
                System.out.println(""+(i+1)+"번째 url : "+tempObj.get("thumbnail"));
                System.out.println("----------------------------");
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonString;
    }
}
