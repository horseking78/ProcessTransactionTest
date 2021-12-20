package com.wang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	// Transaction date
	private Date trans_date;

	// Transaction money
	private float money;

	// Transaction Category
	private Category category;

	// constructor
	public Transaction(String transc_line) {
		String[] items = transc_line.split(",");
		if(items.length == 3) {
			// get Transaction date
			Date trans_date = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				trans_date = sdf.parse(items[0]);
			} catch(Exception ex ) {
				System.out.println("Date is wrong with line : " + transc_line);
			}

			// get Transaction money
			float money = 0;
			try {
				money = Float.parseFloat(items[1]);
			} catch(Exception ex ) {
				System.out.println("Money should be number in line : " + transc_line);
			}

			this.trans_date = trans_date;
			this.money = money;
			this.setCategory(items[2]);
		} else {
			System.out.println("Some thing is wrong with line : " + transc_line);
		}
	}

	// set category by category name
	private void setCategory(String category_name) {
		this.category = Category.getCategory(category_name);
		this.category.addTransac(this);
	}

	public Date getTrans_date() {
		return trans_date;
	}

	public float getMoney() {
		return money;
	}

	// judge if transaction includes expense Category
	public boolean isExpense() {
		if(this.money < 0) {
			return true;
		} else {
			return false;
		}
	}
}
