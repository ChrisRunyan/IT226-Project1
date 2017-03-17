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
				+ "\nWARNING: If you do not add data, you cannot save or return data."
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
		String courseTemp="";
		String courseName="";
		String courseYear="";
		String courseSeason="";
		String[] lines=new String[300];
		int counter=0;
		Scanner reader=null;
		boolean validFileName=false;
		
		while(!validFileName){
			System.out.print("Enter name of input file (course-semester-year.csv): ");
			inputFileName=kb.nextLine();
			try{
				reader=new Scanner(new BufferedReader(new FileReader(inputFileName)));
				validFileName=true;
			}
			catch(FileNotFoundException e){
				System.out.println("Invalid filename entered. Try again.");
			}
		}
		
		courseTemp=inputFileName.substring(0, inputFileName.length()-4);
		
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
		
		//**cannot add more than one file at a time to the array: it re-writes everything existing in the file already
		
		System.out.println("Student data successfully added.\n");
		
		//**add print statements saying how many students were just added and how many students now exist in the array
		
		reader.close();
	}
	
	public void optionS(Scanner kb){
		String studentID="";
		String outputFileName="";
		boolean validStudentID=false;
		PrintWriter writer=null;
		int indexOfStudent=0;
		
		while(!validStudentID){
			System.out.print("Enter student ID for student to save data from: ");
			studentID=kb.nextLine();
			
			for(int i=0; i<DataReader.studentArraySize; i++){
				if(DataReader.student[i].ulid.equals(studentID)){
					validStudentID=true;
					indexOfStudent=i;
				}
			}
		}
		
		System.out.print("Enter the name of the .csv file the student's information should be saved to (firstname-lastname): ");
		outputFileName=kb.nextLine();
		
		try{
			writer=new PrintWriter(outputFileName+".csv");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		//**use writer print column headings 
		//**use writer print data for student (use indexOfStudent to statically find student in array)
		
		//**grade summary is not printing to output file?
		
		System.out.println("Student information successfully printed to "+outputFileName+".csv.\n");
		writer.close();
	}
	
	public void optionG(Scanner kb){
		String studentID="";
		String gradeName="";
		String courseS="";
		Course course=null;
		String season="";
		String year="";
		boolean validCourseInput=false;
		boolean validSeasonInput=false;
		boolean validYearInput=false;
		
		while(!validCourseInput){
			System.out.print("Enter course to print data from (type \"none\" to skip this input): ");
			courseS=kb.nextLine();
			
			if(courseS.equalsIgnoreCase("none")){
				validCourseInput=true;
			}
			else{
				for(int i=0; i<DataReader.studentArraySize; i++){
					if(DataReader.student[i].course.courseName.equalsIgnoreCase(courseS)){
						validCourseInput=true;
						course=DataReader.student[i].course;
					}
				}
			}
		}
		
		while(!validSeasonInput){
			System.out.print("Enter to print data from (type \"none\" to skip this input): ");
			season=kb.nextLine();
			
			if(season.equalsIgnoreCase("none")){
				validCourseInput=true;
			}
			else{
				for(int i=0; i<DataReader.studentArraySize; i++){
					if(DataReader.student[i].course.courseSeason.equalsIgnoreCase(season)){
						validCourseInput=true;
					}
				}
			}
		}
		
		while(!validYearInput){
			System.out.print("Enter year to print data from (type \"none\" to skip this input): ");
			year=kb.nextLine();
			
			if(year.equalsIgnoreCase("none")){
				validYearInput=true;
			}
			else{
				for(int i=0; i<DataReader.studentArraySize; i++){
					if(DataReader.student[i].course.courseYear.equalsIgnoreCase(year)){
						validYearInput=true;
					}
				}
			}
		}
		
		if(!courseS.equalsIgnoreCase("none")){
			if(!season.equalsIgnoreCase("none")||!year.equalsIgnoreCase("none")){
				//**return array of integers for grades under following conditions:
				//**if both course name and year/semester entered, only return grades for that course and year/semester
				//**if no course name entered, only print grades for year/semester
				//**if no semester/year entered, only print grades for coursename
			}
		}
		
	}
}
