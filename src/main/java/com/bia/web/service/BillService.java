package com.bia.web.service;

import java.sql.SQLException;
import java.util.Collection;

import com.bia.web.model.Bill;
import com.bia.web.model.BillDetail;
import com.bia.web.model.Product;
import com.bia.web.repository.BillRepository;
import com.bia.web.repository.ProductRepository;

public class BillService implements IBillService {
	
	BillRepository billRepository;
	
	ProductRepository productRepo = new ProductRepository();
	ProductService productService = new ProductService(productRepo);
	
	public BillService(BillRepository billRepository) {
		super();
		this.billRepository = billRepository;
	}

	@Override
	public void add(Bill object) throws SQLException {
		billRepository.add(object);
	}

	@Override
	public Collection<Bill> getAll() throws SQLException {
		
		return billRepository.getAll();
	}

	@Override
	public Bill getById(int id) throws SQLException {
		return billRepository.getById(id);
	}

	@Override
	public void update(Bill object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void addBillDetail(BillDetail billDetail) throws SQLException {
		Product product = productService.getById(billDetail.getProduct().getId());
		double price = product.getPrice();
		billDetail.setPrice(price);
		billRepository.addBillDetail(billDetail);
	}
	
	public void updateBillDetail(BillDetail billDetail) {
		billRepository.updateBillDetail(billDetail);;
	}

}