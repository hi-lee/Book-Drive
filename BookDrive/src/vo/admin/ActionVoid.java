package vo.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.action.admin.ActionForward;

public interface ActionVoid {
	void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
