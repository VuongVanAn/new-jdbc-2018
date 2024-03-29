package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.iCategoryService;
import com.laptrinhjavaweb.service.iUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {
	
	@Inject
	private iCategoryService categoryService;
	
	@Inject
	private iUserService userService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String action = request.getParameter("action");
		if(action != null && action.equals("login")){
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message != null && alert != null){
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert",alert);
			}
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		}else if(action != null && action.equals("logout")){
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		}else{
			request.setAttribute("categories",categoryService.findAll());
			request.getRequestDispatcher("/view/web/webhome.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("login")){
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if(model != null){
				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
				if(model.getRole().getCode().equals("USER")){
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				}else if(model.getRole().getCode().equals("ADMIN")){
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			}else{
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}

}
