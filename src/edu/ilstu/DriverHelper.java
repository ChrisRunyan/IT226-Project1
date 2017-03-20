package edu.ilstu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author Christopher Runyan
 */
public class DriverHelper {
	DataReader dReader=new DataReader();
	
	public DataReader getdReader() {
		return dReader;
	}

	public void setdReader(DataReader dReader) {
		this.dReader = dReader;
	}

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
	
	public void optionA(Scanner kb, LinkedList<Student> student){
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
		Student[] std = new Student[300];
		
		std = dReader.returnStudentArray();
		int j = 0;
		while(std[j] != null){
			student.add(std[j]);
			j= j+ 1;
		}
		
		//**add print statements saying how many students were just added and how many students now exist in the array
		System.out.println("there are : " + (student.size()) + " students in the list");
	
		reader.close();
	}
	
	public void optionS(Scanner kb,LinkedList<Student> student){
		String studentID="";
		String outputFileName="";
		boolean validStudentID=false;
		PrintWriter writer=null;
		int indexOfStudent=0;
		
		while(!validStudentID){
			System.out.print("Enter student ID for student to save data from: ");
			studentID=kb.nextLine();
			for(int i=0; i<student.size(); i++){
				if(student.get(i).getUlid().equals(studentID)){
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
		StringBuilder sb = new StringBuilder();
	 	sb.append("Student Id");
        sb.append(',');
        sb.append("Course");
        sb.append(',');
        sb.append("Semester");
        sb.append(',');
        sb.append("Year");
        sb.append(',');
        sb.append("Assignment name");
        sb.append(',');
        sb.append( "Points");
        sb.append('\n');
        
        sb.append(student.get(indexOfStudent).getUlid());
        sb.append(',');
        sb.append(student.get(indexOfStudent).course.courseName);
        sb.append(',');
        sb.append(student.get(indexOfStudent).course.courseSeason);
        sb.append(',');
        sb.append(student.get(indexOfStudent).course.courseYear);
        sb.append(',');
        sb.append(student.get(indexOfStudent).course.grades[0].gradeTitle);
        sb.append(',');
        sb.append( student.get(indexOfStudent).course.grades[0].grade);
        sb.append('\n');
        
        
        
        for(int i = 1; i< student.get(indexOfStudent).course.grades.length; i++){

 
            	sb.append("");
    	        sb.append(',');
    	        sb.append("");
    	        sb.append(',');
    	        sb.append("");
    	        sb.append(',');
    	        sb.append("");
    	        sb.append(',');
    	        sb.append(student.get(indexOfStudent).course.grades[i].gradeTitle);
    	        sb.append(',');
    	        sb.append( student.get(indexOfStudent).course.grades[i].grade);
    	        sb.append('\n');
        	
        	
        }
        
        writer.write(sb.toString());
	        System.out.println("Student information successfully printed to "+outputFileName+".csv.\n");
	        writer.close();
	}
	
	
	private static void printArray(int[] anArray) {
	      for (int i = 0; i < anArray.length; i++) {
	         if (i > 0) {
	            System.out.print(", ");
	         }
	         System.out.print(anArray[i]);
	      }
	   }
	
	public void optionG(Scanner kb,LinkedList<Student> student){
		String studentID="";
		String gradeName="";
		String courseS="";
		Course course=null;
		String season="";
		String year="";
		
		boolean check = true;
		while(check = true){
			System.out.print("Enter course to print data from (type \"none\" to skip this input): ");
			courseS=kb.nextLine();
			kb.nextLine();
			System.out.print("Enter semester to print data from (type \"none\" to skip this input): ");
			season=kb.nextLine();
			kb.nextLine();
			System.out.print("Enter year to print data from (type \"none\" to skip this input): ");
			year=kb.nextLine();
			kb.nextLine();
			
			if(courseS.equalsIgnoreCase("none") && season.equalsIgnoreCase("none") && year != "none"){
				System.out.println("not enough information ");
				check= false;
			}
			if(courseS.equalsIgnoreCase("none") && year.equalsIgnoreCase("none") && season != "none"){
				System.out.println("not enough information ");
				check= false;
			}
			if(courseS.equalsIgnoreCase("none") && season != "none" && year.equalsIgnoreCase("none")){
				int a= 0;
				int b = 0;
				int c = 0;
				int d = 0;
				int f = 0;
				for(int i = 0 ; i < student.size(); i++){
					if(student.get(i).course.getCourseSeason().equalsIgnoreCase(season)){
						if (student.get(i).course.getLetterGrade() == 'A'){
							a = a + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'B'){
							b = b + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'C'){
							c = c + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'D'){
							d = d + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'F'){
							f = f + 1;
							}
						
					}
				}
				
				int[] array = {a,b,c,d,f};
				printArray(array);
			}
			
			
			if(courseS.equalsIgnoreCase("none") && year != "none" && season.equalsIgnoreCase("none")){
				int a= 0;
				int b = 0;
				int c = 0;
				int d = 0;
				int f = 0;
				for(int i = 0 ; i < student.size(); i++){
					if(student.get(i).course.getCourseYear().equalsIgnoreCase(year)){
						if (student.get(i).course.getLetterGrade() == 'A'){
							a = a + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'B'){
							b = b + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'C'){
							c = c + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'D'){
							d = d + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'F'){
							f = f + 1;
							}
						
					}
				}
				int[] array = {a,b,c,d,f};
				printArray(array);
			}
			if(season.equalsIgnoreCase("none") && year.equalsIgnoreCase("none")){
				if(courseS.equalsIgnoreCase("none")){
					check = false;
				}
				else{
					int a= 0;
					int b = 0;
					int c = 0;
					int d = 0;
					int f = 0;
					for(int i = 0 ; i < student.size(); i++){
						if(student.get(i).course.getCourseName().equalsIgnoreCase(courseS)){
							if (student.get(i).course.getLetterGrade() == 'A'){
								a = a + 1;
								}
							if (student.get(i).course.getLetterGrade() == 'B'){
								b = b + 1;
								}
							if (student.get(i).course.getLetterGrade() == 'C'){
								c = c + 1;
								}
							if (student.get(i).course.getLetterGrade() == 'D'){
								d = d + 1;
								}
							if (student.get(i).course.getLetterGrade() == 'F'){
								f = f + 1;
								}
							
						}
					}
					int[] array = {a,b,c,d,f};
					printArray(array);
				}
				}
			if (courseS!="none" && year != "none" && season != "none"){
				int a= 0;
				int b = 0;
				int c = 0;
				int d = 0;
				int f = 0;
				for(int i = 0 ; i < student.size(); i++){
					if(student.get(i).course.getCourseYear().equalsIgnoreCase(year)
							&& student.get(i).course.getCourseSeason().equalsIgnoreCase(season)
							&& student.get(i).course.getCourseName().equalsIgnoreCase(courseS)){
						if (student.get(i).course.getLetterGrade() == 'A'){
							a = a + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'B'){
							b = b + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'C'){
							c = c + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'D'){
							d = d + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'F'){
							f = f + 1;
							}
						
					}
				}
				int[] array = {a,b,c,d,f};
				printArray(array);
			}
			
			if (courseS.equalsIgnoreCase("none") && year != "none" && season != "none"){
				int a= 0;
				int b = 0;
				int c = 0;
				int d = 0;
				int f = 0;
				for(int i = 0 ; i < student.size(); i++){
					if(student.get(i).course.getCourseYear().equalsIgnoreCase(year)
							&& student.get(i).course.getCourseSeason().equalsIgnoreCase("season")){
						if (student.get(i).course.getLetterGrade() == 'A'){
							a = a + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'B'){
							b = b + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'C'){
							c = c + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'D'){
							d = d + 1;
							}
						if (student.get(i).course.getLetterGrade() == 'F'){
							f = f + 1;
							}
						
					}
				}
				int[] array = {a,b,c,d,f};
				printArray(array);
			}
			
				
		}
	}
		
}
