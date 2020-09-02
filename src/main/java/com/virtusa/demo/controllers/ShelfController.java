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

import com.virtusa.demo.bean.Shelf;
import com.virtusa.demo.bean.WareHouseExpiry;
import com.virtusa.demo.repository.ShelfRepo;
import com.virtusa.demo.repository.WareHouseExpiryRepo;
import com.virtusa.demo.repository.WareHouseRepo;

@Controller
public class ShelfController {
	
	//Creating objects for the respective classes
	@Autowired
	ShelfRepo shelfRepo;
	
	@Autowired
	WareHouseRepo whRepo;
	
	@Autowired
	WareHouseExpiryRepo whExpiryRepo;	
	
	//displaying all the products in the shelf
	@RequestMapping("display_shelf_products")
	public ModelAndView displayShelfProducts(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name") == null)
			return null;
		
		ModelAndView mv = new ModelAndView();
		ArrayList<Shelf> shelflist = (ArrayList<Shelf>) shelfRepo.findAll();
		mv.addObject("shelf_list", (ArrayList<Shelf>) shelflist);
		mv.setViewName("shelf_products.jsp");
		return mv;
	}
	
	//Reading the uploaded csv file of products list
	@PostMapping("load_shelf")
	public ModelAndView loadShelf(@RequestParam("filename") MultipartFile file, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		//Reading each line from the uploaded file and storing them in the form of objects
		//handling the exception if there is any invalid data in the file
		try {
			FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
			Shelf shelf;
			String st; 
			br.readLine();
			long lineCount = 0l;
			ArrayList<Shelf> shelfList = new ArrayList<Shelf>();
			while((st = br.readLine()) != null) {
				try {
					lineCount++;
					String arr[] = st.split(",");
					long id = Long.parseLong(arr[0]);
					long count = Long.parseLong(arr[4]);
					@SuppressWarnings("deprecation")
					Date expireDate = new Date(arr[7]);
					
					shelf = new Shelf();
					shelf.setId(id);
					shelf.setProductName(arr[1]);
					shelf.setProductCategory(arr[2]);
					shelf.setProductDescription(arr[3]);
					shelf.setCount(count);
					shelf.setCost(Long.parseLong(arr[5]));
					shelf.setCurrency(arr[6]);
					shelf.setExpireDate(expireDate);
					shelfList.add(shelf);
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
					String strDate = formatter.format(expireDate);
					WareHouseExpiry whe = whExpiryRepo.find(id, strDate);
					//handling the exception of product not present in warehouse
					if( !whRepo.existsById(id) || whe == null ) {
						mv.setViewName("shelf_left.jsp");
						mv.addObject("shelf_upload_msgerror", "product at line : "+lineCount+", not found in warehouse");
						return mv;
					}
					//handling the exception of quantity of products less than the required
					if(whe.getCount() < count) {
						mv.setViewName("shelf_left.jsp");
						mv.addObject("shelf_upload_msgerror", "product at line : "+lineCount+", is less in quantity in warehouse");
						return mv;
					}
				}
				catch(Exception e) {
					mv.setViewName("shelf_left.jsp");
					mv.addObject("shelf_upload_msgerror", "Invalid data in the file at line :"+lineCount);
					return mv;
				}
				
			}
			//saving all the products in the database
			for(int i=0; i<shelfList.size();i++) {
				shelf = shelfList.get(i);
				long id = shelf.getId();
				long count = shelf.getCount();
				Date expireDate = shelf.getExpireDate(); 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate = formatter.format(expireDate);
				if(shelfRepo.existsById(id)) {
					shelfRepo.updateCount(id, count, expireDate);
				}
				else {	
					shelfRepo.save(shelf);
				}
				whRepo.moveToShelf(id, count);
				WareHouseExpiry whExpiry = whExpiryRepo.find(id, strDate);
				//removing the products from warehouse expire if the quantity is equal to zero 
				if(whExpiry.getCount() == count)
					whExpiryRepo.delete(id, strDate);
				else
					whExpiryRepo.moveToShelf(id, strDate, count);
				
			}
		}
		catch(IOException e) {
			mv.setViewName("shelf_left.jsp");
			mv.addObject("shelf_upload_msgerror", "File upload error");
			return mv;
		}
		mv.setViewName("shelf_left.jsp");
		mv.addObject("shelf_upload_msg", "File uploaded");
		
		return mv;
	}
}
