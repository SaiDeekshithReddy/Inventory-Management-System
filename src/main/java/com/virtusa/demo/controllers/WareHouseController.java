package com.virtusa.demo.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.demo.bean.WareHouse;
import com.virtusa.demo.bean.WareHouseExpiry;
import com.virtusa.demo.repository.WareHouseExpiryRepo;
import com.virtusa.demo.repository.WareHouseRepo;

@Controller
public class WareHouseController {
	
	//Creating objects for the respective classes
	@Autowired
	WareHouseRepo wareHouserepo;
	
	@Autowired
	WareHouseExpiryRepo wareHouseExpiryRepo;
	
	@Autowired
	SimpleEmailController mail;
	
	//Display all the products in warehouse with expire dates
	@RequestMapping("display_warehouse_expiry")
	public ModelAndView displayWareHouseExpiry(HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("name"));
		if(session.getAttribute("name") == null)
			return null;
		ModelAndView mv = new ModelAndView();
		ArrayList<WareHouseExpiry> wareHouseExpiry = (ArrayList<WareHouseExpiry>) wareHouseExpiryRepo.findAll();
		mv.addObject("warehouse_expiry", (ArrayList<WareHouseExpiry>) wareHouseExpiry);
		mv.setViewName("warehouseexpiry.jsp");
		return mv;
	}
	
	//Display all the warehouse products
	@RequestMapping("display_warehouseproducts")
	public ModelAndView displayWareHouseProducts(HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("name"));
		if(session.getAttribute("name") == null)
			return null;
		ModelAndView mv = new ModelAndView();
		ArrayList<WareHouse> warehouseproductlist = (ArrayList<WareHouse>) wareHouserepo.findAll();
		mv.addObject("warehouseproductlist", (ArrayList<WareHouse>) warehouseproductlist);
		mv.setViewName("warehouseproductpage.jsp");	
		return mv;
	}

	//Reading the uploaded csv file of products list
	@PostMapping("load_warehouse")
	public ModelAndView uploadFile(@RequestParam("filename") MultipartFile file, HttpServletRequest req) throws IOException {
		ModelAndView mv = new ModelAndView();
		FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
		WareHouse wareHouse;
		WareHouseExpiry wareHouseExpiry;
		String st; 
		long lineCount = 0l;
		ArrayList<String[]> textList = new ArrayList<String[]>();
		ArrayList<WareHouse> wareHouseList = new ArrayList<WareHouse>();
		ArrayList<WareHouseExpiry> expiryList = new ArrayList<WareHouseExpiry>();
		textList.add(br.readLine().split(","));
		//Reading each line from the uploaded file and storing them in the form of objects
		//handling the exception if there is any invalid data in the file
		while ((st = br.readLine()) != null) {
			try {
				lineCount++;
				System.out.println(st);
				String arr[] = st.split(",");
				textList.add(arr);
				long id = Long.parseLong(arr[0]);
				long count = Long.parseLong(arr[5]);
				@SuppressWarnings("deprecation")
				Date expireDate = new Date(arr[8]);
				
				wareHouse = new WareHouse();
				wareHouse.setId(id);
				wareHouse.setProductName(arr[1]);
				wareHouse.setVendorCode(Long.parseLong(arr[2]));
				wareHouse.setProductCategory(arr[3]);
				wareHouse.setProductDescription(arr[4]);
				wareHouse.setCount(count);
				wareHouse.setCost(Long.parseLong(arr[6]));
				wareHouse.setCurrency(arr[7]);
				wareHouseList.add(wareHouse);
				
				wareHouseExpiry = new WareHouseExpiry();
				wareHouseExpiry.setProductId(id);
				wareHouseExpiry.setProductName(arr[1]);
				wareHouseExpiry.setExpireDate(expireDate);
				wareHouseExpiry.setCount(count);
				expiryList.add(wareHouseExpiry);
			}
			catch(Exception e) {
				mv.setViewName("warehouseleft.jsp");
				mv.addObject("warehouse_upload_msgerror", "Invalid data in the file at line : "+lineCount);
				return mv;
			}
		}
		//saving the objects in the database
		for(int i=0;i<wareHouseList.size();i++) {
			wareHouse = wareHouseList.get(i);
			wareHouseExpiry = expiryList.get(i);
			Long id = wareHouse.getId();
			Long count = wareHouse.getCount();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = formatter.format(wareHouseExpiry.getExpireDate());
			if(wareHouserepo.existsById(id)) {
				wareHouserepo.updateCount(id, count);
			}else {
				wareHouserepo.save(wareHouse);
			}
			WareHouseExpiry whe = wareHouseExpiryRepo.find(id, strDate);
			if(whe != null){
				wareHouseExpiryRepo.updateCount(id, strDate, count);
			} else {
				wareHouseExpiryRepo.save(wareHouseExpiry);
			}
		}
		
		//sending mail to the distributor after loading products into warehouse
		mail.sendNewMail(textList);
		mv.setViewName("warehouseleft.jsp");
		mv.addObject("warehouse_upload_msg", "File uploaded");
		
		return mv;
	}
	
}
