package com.wang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class HelloWorld {
	public static void main(String[] args) {
//		// total income value
//		float totalIncome = 0;
//		// total expense value
//		float totalExpense = 0;
//		// total saving
//		float totalSaving = 0;

		File file = new File("D://test.data");
		Collection<Transaction> collection = new ArrayList<Transaction>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine())!=null) {
				//System.out.println(line);
				Transaction transac  =  new Transaction(line);
				collection.add(transac);

//				// calculate the total transaction money
//				if(!transac.isExpense()) {
//					totalIncome = totalIncome + transac.getMoney();
//				} else {
//					totalExpense = totalExpense + transac.getMoney();
//				}	
				
			}
		} catch(Exception ex) {
			System.out.println("There is error reading file.");
			System.out.println(ex.toString());
		}

		Double totalIncome = collection.stream().filter(t -> t.getMoney() > 0).mapToDouble(t -> t.getMoney()).sum();
		Double totalExpense = collection.stream().filter(t -> t.getMoney() < 0).mapToDouble(t -> t.getMoney()).sum();
		Double totalSaving = totalIncome + totalExpense;

//		totalSaving = totalIncome + totalExpense;
		System.out.println("Total Income:" + totalIncome);
		System.out.println("Total Expenses:" + totalExpense * -1);
		System.out.println("Total Savings:" + totalSaving);

//		Category cat = Category.getMaxCategory();
//		System.out.println("Top Expense Category: " + cat.printInfo());
		
		Category.getCategories().stream().sorted().limit(1).forEach(a -> System.out.print("Top Expense Category:" + a.printInfo()));
	}
}
