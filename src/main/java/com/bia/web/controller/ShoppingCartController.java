package com.bia.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bia.web.model.Bill;
import com.bia.web.model.BillDetail;
import com.bia.web.model.Product;
import com.bia.web.model.User;
import com.bia.web.repository.BillRepository;
import com.bia.web.repository.ProductRepository;
import com.bia.web.repository.UserRepository;
import com.bia.web.service.BillService;
import com.bia.web.service.ProductService;
import com.bia.web.service.UserService;
import com.bia.web.utils.NumberTools;


public class ShoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		 BillRepository billRepo;
		 BillService billService;
		 ProductRepository productRepo;
		 ProductService productService;
		 UserRepository userRepo;
		 UserService userService;
    
    public ShoppingCartController() {
    	 billRepo = new BillRepository();
         billService = new BillService(billRepo);
         productRepo = new ProductRepository();
         productService = new ProductService(productRepo); 
         userRepo = new UserRepository();
         userService = new UserService(userRepo);
    }
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			deleteBillDetail(request, response);
			showBillDetails(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/shop/shopping-cart.jsp");
		dispatcher.forward(request, response);
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			try {
				updateQuantity(request, response);								  
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			response.sendRedirect(request.getContextPath()+"/Shoppingcart");
	}
	private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int quantity = NumberTools.parseIntWithFallback(request.getParameter("quantity"));
		
		if(quantity>0) {
			int billId = NumberTools.parseIntWithFallback(request.getParameter("billId"));
			int productId = NumberTools.parseIntWithFallback(request.getParameter("productId"));
			
			Bill bill = billService.getById(billId);
			
			Product product = productService.getById(productId);
			
			BillDetail billDetail = new BillDetail();
			billDetail.setBill(bill);
			billDetail.setProduct(product);
			billDetail.setProductQuantity(quantity);
			
			billService.updateBillDetail(billDetail);
		}						
	}
	private void deleteBillDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int productId = NumberTools.parseIntWithFallback(request.getParameter("removeID"));
		
		billService.deleteBillDetail(productId);
		
		
		
	}

	private void showBillDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		List<BillDetail> billDetails = new ArrayList<>();
		
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("EMAIL");
		if(email != null) {
			User user = userService.getUserByEmail(email);
			
			Bill bill = billService.getCurrentBill(user);
			
			int billId = bill.getId();
			
			bill = billService.getById(billId);
			
			billDetails = bill.getBillDetails();
			
			
			Product product = null;
			
			for(int i = 0; i < billDetails.size(); i++) {
				int productId = billDetails.get(i).getProduct().getId();
				product = productService.getById(productId);
				billDetails.get(i).setProduct(product);
			}
			
			request.setAttribute("BILLDETAIL_LIST", billDetails);
					
			double total = billService.getTotal(billId);
			
			request.setAttribute("TOTAL", total);
		}
		
	}
	
	
}	