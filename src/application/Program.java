package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		System.out.println("Enter File path");
		String strPath = sc.nextLine();
		
		File sourceFile = new File(strPath);
		
		String sourceFolder = sourceFile.getParent();
		
		boolean success = new File(sourceFolder + "\\out").mkdir();
		
		String outFile = sourceFolder + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			String line = br.readLine();
			while(line != null) {
				String[] item = line.split(",");
				list.add(new Product(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2])));
				
				line = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
				for(Product prod : list) {
					bw.write(prod.getName() + "," + String.format("%.2f", prod.subTotal()));
					bw.newLine();
				}
				
				System.out.println(outFile + " CREATED!");
				
			}	
			
			catch(IOException e) {
				System.out.println("Trouble writing file " + e.getMessage());
			}
		}
		
		catch(IOException e) {
			System.out.println("Trouble reading file: " + e.getMessage());
		}
		
		sc.close();
	}

}
