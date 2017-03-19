package edu.ilstu;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author Christopher Runyan
 */
public class Driver{
	public static void main(String[] args){
		// create menu with options
		Scanner kb=new Scanner(System.in);
		DriverHelper dHelp=new DriverHelper();
		DataReader dReader=new DataReader();
		 LinkedList<Student> student = new LinkedList<Student>();
		String input="";
		
		System.out.println("Welcome to the Student Data Integration and Viewer Program (SDIVP).");
		while(!input.equalsIgnoreCase("E")){
			dHelp.printMenu();
			input=kb.nextLine();
			while(!dHelp.validateInput(input)){
				System.out.println("Invalid input.");
				dHelp.printMenu();
				input=kb.nextLine();
			}
			if(!input.equalsIgnoreCase("E")){
				if(input.equalsIgnoreCase("A")){
					dHelp.setdReader(dReader);
					dHelp.optionA(kb,student);
				
				}
				else if(input.equalsIgnoreCase("S")){
					dHelp.optionS(kb,student);
				}
				else if(input.equalsIgnoreCase("G")){
					dHelp.optionG(kb, student);
				}
			}
		}
		
		kb.close();
	}
}
