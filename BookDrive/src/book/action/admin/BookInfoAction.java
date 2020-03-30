package book.action.admin;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import book.svc.admin.BookInfoService;
import log.action.admin.ActionForward;
import vo.admin.ActionVoid;
import vo.admin.BookRentalInfo;
public class BookInfoAction implements ActionVoid {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<BookRentalInfo> bookRentalInfoList = null;
		BookRentalInfo bookRentalInfo = null;
		ActionForward forward = null;
		String bookNum = request.getParameter("booknum") != null ? request.getParameter("booknum") : "";
//		System.out.println("bookNum : " + bookNum);
		BookInfoService bookInfoService = new BookInfoService();
//		bookRentalInfo = bookInfoService.getBookInfo(bookNum);
		bookRentalInfoList = bookInfoService.getBookInfo(bookNum);
		Gson gson = new Gson(); //JSON 형태로 변환하기 위해 GSON을 사용함(라이브러리 추가 필요)
		String a = gson.toJson(bookRentalInfoList); //ValueObject -> JSON으로 변환하기 위해 toJson 메소드를 사용하고 결과를 String에 넣음
		System.out.println(a);
		
		JSONArray objarray = new JSONArray(a);
//		JSONObject obj = (JSONObject) objarray.get(0); //JSON형태의 String을 JSONObject 객체를 생성할 때 파라미터로 넣음
//		System.out.println(obj);
//		String ab[] = new String[obj.length()]; //JSON의 key 이름를 각각 put해야 하므로 ValueObject 내의 변수만큼 String 배열 생성
//		ab = JSONObject.getNames(obj); //String 배열에 JSON의 key 이름을 넣음
		
//		for (int i = 0; i < ab.length; i++) {
//			obj.put(ab[0], obj.get(ab[0])); //JSONObject에 key와 value를 넣음(put이용)
//		}
		//obj를 리턴하여 프론트로 보냄
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(objarray);
	}
}