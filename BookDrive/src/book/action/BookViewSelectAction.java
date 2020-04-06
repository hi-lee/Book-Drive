package book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import action.ActionVoid;
import book.svc.BookCartListSvc;

public class BookViewSelectAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String list[] = request.getParameterValues("arraylist[]"); //ajax로 호출시 []를 붙여줘야함
		String userIndex = request.getParameter("userIndex");
		BookCartListSvc bookCartListSvc = new BookCartListSvc();
		String bookNum[] = bookCartListSvc.selectCart(list, userIndex);
		
		if (bookNum != null) {
			JSONObject obj = new JSONObject();
			obj.put("result", bookNum);
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj);
		} 
	}
}