package book.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import book.dao.admin.BookDAO;

public class BookImageInsertService {
	public void selectISBN() {
		// TODO Auto-generated method stub
//		String[] isbnArray = new String[1000]; //배열 정보 변경
		ArrayList<String> isbnList = new ArrayList<>();
		int resultCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		System.out.println("컨넥션성공");
//		isbnArray = bookDAO.getISBNArray(); //ISBN 번호를 가져온다(배열, 쿼리문 정보 변경)
		isbnList = bookDAO.getISBNArray(); //ISBN 번호를 가져온다(쿼리문 정보 변경)
		//ISBN을 가져온 후 처리
		for (int i = 0; i < isbnList.size(); i++) {
//			resultCount = bookDAO.insertImageURL(isbnArray[i], addrToCord(isbnArray[i]));
			resultCount = bookDAO.insertImageURL(isbnList.get(i), addrToCord(isbnList.get(i)));
			if ((i % 100) == 0) System.out.println();
			if (resultCount > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		}
		
		if (con != null) close(con);
	}
	
	public String addrToCord(String isbn) {
        
        String url = "https://dapi.kakao.com/v3/search/book?target=isbn&query="+isbn;
        String json = "";
        if (!isbn.equals("")) {
	        try {
	            json = getJSONData(url, isbn);
	        } catch(Exception e){
	            e.printStackTrace();
	        }
        }
        return json;
    }
	
	private String getJSONData(String apiUrl, String isbn) throws Exception {
        String jsonString = new String();
        String buf;
        String apikey = "7c28f9da096eaa302f600c9900820d6e"; //apikey
        String imageURL = "";
        URL url = new URL(apiUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        String auth = "KakaoAK "+apikey;
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(2000);
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
            if (memberArray != null) {
	            for(int i=0 ; i < memberArray.size(); i++){
	                JSONObject tempObj = (JSONObject) memberArray.get(i);
	                imageURL = (String) tempObj.get("thumbnail");
	            }
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imageURL;
    }
}
