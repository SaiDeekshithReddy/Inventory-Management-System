package com.virtusa.demo.controllers;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.demo.bean.Bill;
import com.virtusa.demo.bean.BillingItem;
import com.virtusa.demo.bean.Shelf;
import com.virtusa.demo.repository.BillRepo;
import com.virtusa.demo.repository.ShelfRepo;

@Controller
public class BillController {
	
	@Autowired
	ShelfRepo shelfRepo;
	
	@Autowired
	BillRepo billRepo;
	
	//Request for entering items that are needed to be billed
	@RequestMapping("billing")
	public ModelAndView billing() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("billing.jsp");
		return mv;
	}
	
	//billing of the entered items
	@RequestMapping("bill")
	public ModelAndView bill(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String stringId=null;
		int i=0;
		BillingItem billingItem;
		ArrayList<BillingItem> billingList = new ArrayList<BillingItem>();
		while((stringId = request.getParameter("product_id"+i))!= null && !stringId.trim().equals("") ) {
			billingItem = new BillingItem();
			try {
				long productId = Long.parseLong(stringId);
				long count = Long.parseLong(request.getParameter("product_count"+i));
				Shelf shelf = shelfRepo.findById(productId).orElse(null);
				//handling exception if the item is not found
				if(shelf == null) {
					mv.addObject("billing_error", "item not found in shelf, id = "+productId);
					mv.setViewName("billing.jsp");
					return mv;
				}
				//handling exception if the quantity of item is less than required in shelf
				if(shelf.getCount() < count) {
					mv.addObject("billing_error", "item count is less than entered in shelf, id = "+productId);
					mv.setViewName("billing.jsp");
					return mv;
				}
				long cost = shelf.getCost();
				String productName = shelf.getProductName();
				billingItem.setProductId(productId);
				billingItem.setProductName(productName);
				billingItem.setCost(cost);
				billingItem.setCount(count);
				billingList.add(billingItem);
				i++;
			}
			catch(Exception e) {
				mv.addObject("billing_error", "invalid data at "+i+" item");
				mv.setViewName("billing.jsp");
				return mv;
			}
		}
		//updating the items in database 
		for(int j=0;j<billingList.size();j++) {
			billingItem = billingList.get(j);
			shelfRepo.reduceCount(billingItem.getProductId(),billingItem.getCount());
		}
		Date date = new Date();
		Bill bill = new Bill();
		bill.setDate(date);
		bill.setItems(billingList);
		billRepo.save(bill);
		mv.addObject("billing_list", (ArrayList<BillingItem>) billingList);
		mv.setViewName("bill.jsp");
		return mv;
	}
	
	//displaying all the previous bills
	@RequestMapping("all_bills")
	public ModelAndView allBills() {
		ArrayList<Bill> list = (ArrayList<Bill>)billRepo.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("bills_list", list);
		mv.setViewName("all_bills.jsp");
		return mv;
	}
	
	//displaying the selected bill
	@RequestMapping("display_bill")
	public ModelAndView displayBill(HttpServletRequest request) {
		Long billId = Long.parseLong(request.getParameter("button"));
		Bill bill = billRepo.findById(billId).orElse(null);
		System.out.println(bill.getBillId()+" "+bill.getDate());
		ModelAndView mv = new ModelAndView();
		mv.addObject("bill", bill);
		mv.setViewName("display_bill.jsp");
		return mv;
	}
}
