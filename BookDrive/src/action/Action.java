package action;

import javax.servlet.http.*;

public interface Action {
	ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception;
}
