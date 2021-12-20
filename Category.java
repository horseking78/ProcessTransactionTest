package com.wang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Category {

	// category manager
	static  HashMap <String, Category> categories = new HashMap<String, Category>();
	private List<Transaction> transacs = new ArrayList<Transaction>();

	// total money
	private float totalMoney;
	// return total money
	public float getTotalMoney() {
		return totalMoney;
	}

	// category name
	private String category_name = "";

	// constructor
	public Category(String name) {
		this.category_name = name;
		this.totalMoney = 0;
	}

	// get Category object by name, if it doesn't exist, then initialize one
	static Category getCategory(String name) {
		if(categories.containsKey(name)) {
			return categories.get(name);
		} else {
			Category category = new Category(name);
			categories.put(name, category);
			return category;
		}
	}

	// get the max expense category
	static Category getMaxCategory() {
		float max_vlaue = 0;
		String max_cat_name = "";
		for(String cat_name : categories.keySet()) {
			// get Category object by name
			Category cat = categories.get(cat_name);
			// get the max expense category
			if(cat.isExpense()) {
				// because expense money is minus value, use the Math.abs function
				if(Math.abs(cat.getTotalMoney()) > max_vlaue) {
					max_vlaue = Math.abs(cat.getTotalMoney());
					max_cat_name = cat_name;
				}
			}
		}
		return categories.get(max_cat_name);
	}

	// relate Transaction with Category
	public void addTransac(Transaction thisTransac) {
		totalMoney = totalMoney + thisTransac.getMoney();
		transacs.add(thisTransac);
	}

	// get Category info
	public String printInfo() {
		return totalMoney * -1 + "@" + this.category_name;
	}

	// judge if Category is expense Category
	public boolean isExpense() {
		if(this.totalMoney < 0) {
			return true;
		} else {
			return false;
		}
	}
}
