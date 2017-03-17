package edu.ilstu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Christopher Runyan
 */
public class DriverHelper {
	DataReader dReader=new DataReader();
	
	public void printMenu(){
		System.out.print("Choices:\n\"A\": Add data (You will need to enter an input filename)"
				+ "\n\"S\": Save data (You will need to enter a student ID and output filename)"
				+ "\n\"G\": Return data (Returns student, grade, and course specific information)"
				+ "\n\"E\": Exit the program"
				+ "\n\nEnter choice here: ");
	}
	
	public boolean validateInput(String input){
		boolean valid=false;
		
		if(input.length()==1){
			if(input.equalsIgnoreCase("A")||input.equalsIgnoreCase("S")||input.equalsIgnoreCase("G")||input.equalsIgnoreCase("E")){
				valid=true;
			}
		}
		
		return valid;
	}
	
	public void optionA(Scanner kb){
		String inputFileName="";
		String firstLine="";
		String[] lines=new String[300];
		Student[] studentArray=new Student[300];
		int counter=0;
		
		System.out.print("Enter name of input file (course-semester-year.csv): ");
		inputFileName=kb.nextLine();
		try{
			Scanner reader=new Scanner(new BufferedReader(new FileReader(inputFileName)));
			//**If invalid filename, prompt until valid filename entered
			
			String courseTemp=inputFileName.substring(0, inputFileName.length()-4);
			String courseName="";
			String courseYear="";
			String courseSeason="";
			for(int i=0; i<courseTemp.length(); i++){
				if(courseTemp.charAt(i)=='-'){
					courseName=courseTemp.substring(0, i);
					courseTemp=courseTemp.substring(i+1);
					i=courseTemp.length();
				}
			}
			for(int i=0; i<courseTemp.length(); i++){
				if(courseTemp.charAt(i)=='-'){
					courseSeason=courseTemp.substring(0, i);
					courseYear=courseTemp.substring(i+1);
				}
			}
			
			firstLine=reader.nextLine();
			while(reader.hasNextLine()){
				lines[counter]=reader.nextLine();
				counter++;
			}
			dReader.setStudentArraySize(counter);
			dReader.setCourseName(courseName);
			dReader.setCourseYear(courseYear);
			dReader.setCourseSeason(courseSeason);
			dReader.formatData(firstLine, lines);
			studentArray=dReader.returnStudent();
			//**add each student in studentArray to linked list using for loop, use counter to stop
			reader.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void optionS(Scanner kb){
		String studentID="";
		String outputFileName="";
		
		System.out.print("Enter student ID for student to save data from: ");
		studentID=kb.nextLine();
		//**validate student ID is associated with a real one, if not prompt until valid one is entered
		System.out.print("Enter the name of the text file the student's information should be saved to (firstname-lastname): ");
		outputFileName=kb.nextLine();
		
		try{
			PrintWriter writer=new PrintWriter(outputFileName+".txt");
			//**print student data from student id
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void optionG(Scanner kb){
		String studentID="";
		String gradeName="";
		String course="";
		
		System.out.print("Enter student ID for student to print data from: ");
		studentID=kb.nextLine();
		//**verify student is valid, if not prompt until valid entered
		System.out.print("Enter course for student to print data from: ");
		course=kb.nextLine();
		//**verify student is associated with that course, if not, prompt until valid entered
		System.out.print("Enter name of assignment for grade information to print data from: ");
		gradeName=kb.nextLine();
		//**verify gradeName is valid for student and course, if not prompt until valid entered
		
		//print data for student, gradeName, and course
	}
}
