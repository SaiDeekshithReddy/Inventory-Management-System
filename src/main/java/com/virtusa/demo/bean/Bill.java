package com.virtusa.demo.bean;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seq",initialValue=1, allocationSize=1)
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq")
	private long billId;
	private Date date;
	@Lob
	private ArrayList<BillingItem> items;
	public long getBillId() {
		return billId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<BillingItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<BillingItem> items) {
		this.items = items;
	}
	
}
