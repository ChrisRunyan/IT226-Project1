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
 * @author Christopher Runyan, Hoang Le
 */
public class DriverHelper {
	DataReader dReader=new DataReader();
	int gradesSize=0;
	
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
	
	void printArray(int[] returnArray){
		for(int i=0; i<5; i++){
			switch(i){
			case 0:
				System.out.println("Number of A's: "+returnArray[i]);
				break;
			case 1:
				System.out.println("Number of B's: "+returnArray[i]);
				break;
			case 2:
				System.out.println("Number of C's: "+returnArray[i]);
				break;
			case 3:
				System.out.println("Number of D's: "+returnArray[i]);
				break;
			case 4:
				System.out.println("Number of F's: "+returnArray[i]);
			}
		}
		System.out.println("");
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
		int sizeTemp=0;
		
		while(!validFileName){
			System.out.print("Enter name of input file (course-semester-year): ");
			inputFileName=kb.nextLine();
			try{
				reader=new Scanner(new BufferedReader(new FileReader(inputFileName+".csv")));
				courseTemp=inputFileName;
				boolean fileAdded=false;
				
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
				
				for(int i=0; i<student.size(); i++){
					if(student.get(i).course.courseName.equals(courseName)&&student.get(i).course.courseSeason.equals(courseSeason)&&student.get(i).course.courseYear.equals(courseYear)){
						fileAdded=true;
					}
				}
				
				if(!fileAdded){
					validFileName=true;
				}
				else{
					System.out.println("File already added. Try again.");
				}
			}
			catch(FileNotFoundException e){
				System.out.println("Invalid filename entered. Try again.");
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
		
		System.out.println("Student data successfully added.");
		Student[] std = new Student[300];
		
		sizeTemp=student.size();
		
		std = dReader.returnStudentArray();
		int j = 0;
		while(std[j] != null){
			student.add(std[j]);
			j= j+ 1;
		}
		
		this.gradesSize=student.get(0).course.returnCurrentSize();
		
		System.out.println("There are " + (student.size()) + " students in the repository; "+(student.size()-sizeTemp)+" were just added.\n");
	
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
			if(!validStudentID){
				System.out.println("Invalid student ID entered. Try again.");
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
        
        for(int i=0; i<gradesSize; i++){
        	int counter=0;
        	if(student.get(indexOfStudent).course.grades[i].studentID.equals(student.get(indexOfStudent).ulid)&&(counter<1)){
        		sb.append(student.get(indexOfStudent).course.grades[i].gradeTitle);
                sb.append(',');
                sb.append(student.get(indexOfStudent).course.grades[i].grade);
                sb.append('\n');
                sb.append("");
    	        sb.append(',');
    	        sb.append("");
    	        sb.append(',');
    	        sb.append("");
    	        sb.append(',');
    	        sb.append("");
    	        sb.append(',');
        	}
        	else if(student.get(indexOfStudent).course.grades[i].studentID.equals(student.get(indexOfStudent).ulid)&&(counter==1)){
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
    	        sb.append(student.get(indexOfStudent).course.grades[i].grade);
    	        sb.append('\n');
        	}
        	counter++;
        }
        
        writer.write(sb.toString());
	    System.out.println("Student information successfully printed to "+outputFileName+".csv.\n");
	    writer.close();
	}
	
	public void optionG(Scanner kb,LinkedList<Student> student){
		String courseS="";
		String season="";
		String year="";
		boolean validCourse=false;
		boolean validSeason=false;
		boolean validYear=false;
		int[] returnArray=new int[5];
		
		while(!validCourse){
			System.out.print("Enter course to print data from (type \"none\" to skip this input): ");
			courseS=kb.nextLine();
			for(int i=0; i<student.size(); i++){
				if(student.get(i).course.courseName.equals(courseS)){
					validCourse=true;
				}
				else if(courseS.equalsIgnoreCase("none")){
					validCourse=true;
				}
			}
			if(!validCourse){
				System.out.println("Invalid course name entered. Try again.");
			}
		}
		
		while(!validSeason){
			System.out.print("Enter semester to print data from (type \"none\" to skip this input): ");
			season=kb.nextLine();
			for(int i=0; i<student.size(); i++){
				if(student.get(i).course.courseSeason.equals(season)){
					validSeason=true;
				}
				else if(season.equalsIgnoreCase("none")){
					validSeason=true;
				}
			}
			if(!validSeason){
				System.out.println("Invalid course season entered. Try again.");
			}
		}
		
		while(!validYear){
			System.out.print("Enter year to print data from (type \"none\" to skip this input): ");
			year=kb.nextLine();
			for(int i=0; i<student.size(); i++){
				if(student.get(i).course.courseYear.equals(year)){
					validYear=true;
				}
				else if(year.equalsIgnoreCase("none")){
					validYear=true;
				}
			}
			if(!validYear){
				System.out.println("Invalid course year entered. Try again.");
			}
		}
		
		for(int i=0; i<5; i++){
			returnArray[i]=0;
		}
		
		if(!courseS.equalsIgnoreCase("none")&&season.equalsIgnoreCase("none")&&year.equalsIgnoreCase("none")){
			System.out.println("Student information for "+courseS+" throughout all semesters and years:");
			for(int k=0; k<student.size(); k++){
				if(student.get(k).course.courseName.equals(courseS)){
					if(student.get(k).returnLetterGrade()=='A'||student.get(k).course.letterGrade=='a'){
						returnArray[0]++;
					}
					else if(student.get(k).returnLetterGrade()=='B'||student.get(k).course.letterGrade=='b'){
						returnArray[1]++;
					}
					else if(student.get(k).course.letterGrade=='C'||student.get(k).course.letterGrade=='c'){
						returnArray[2]++;
					}
					else if(student.get(k).course.letterGrade=='D'||student.get(k).course.letterGrade=='d'){
						returnArray[3]++;
					}
					else if(student.get(k).course.letterGrade=='F'||student.get(k).course.letterGrade=='f'){
						returnArray[4]++;
					}
				}
			}
			printArray(returnArray);
		}
		else if(courseS.equalsIgnoreCase("none")&&!season.equalsIgnoreCase("none")&&!year.equalsIgnoreCase("none")){
			System.out.println("Student information for all courses in "+season+" "+year+":");
			for(int k=0; k<student.size(); k++){
				if(student.get(k).course.courseSeason.equals(season)&&student.get(k).course.courseYear.equals(year)){
					if(student.get(k).returnLetterGrade()=='A'||student.get(k).course.letterGrade=='a'){
						returnArray[0]++;
					}
					else if(student.get(k).returnLetterGrade()=='B'||student.get(k).course.letterGrade=='b'){
						returnArray[1]++;
					}
					else if(student.get(k).course.letterGrade=='C'||student.get(k).course.letterGrade=='c'){
						returnArray[2]++;
					}
					else if(student.get(k).course.letterGrade=='D'||student.get(k).course.letterGrade=='d'){
						returnArray[3]++;
					}
					else if(student.get(k).course.letterGrade=='F'||student.get(k).course.letterGrade=='f'){
						returnArray[4]++;
					}
				}
			}
			printArray(returnArray);
		}
		else if(!courseS.equalsIgnoreCase("none")&&!season.equalsIgnoreCase("none")&&!year.equalsIgnoreCase("none")){
			System.out.println("Student information for "+courseS+" in "+season+" "+year+":");
			for(int k=0; k<student.size(); k++){
				if(student.get(k).course.courseSeason.equals(season)&&student.get(k).course.courseYear.equals(year)&&student.get(k).course.courseName.equals(courseS)){
					if(student.get(k).returnLetterGrade()=='A'||student.get(k).course.letterGrade=='a'){
						returnArray[0]++;
					}
					else if(student.get(k).returnLetterGrade()=='B'||student.get(k).course.letterGrade=='b'){
						returnArray[1]++;
					}
					else if(student.get(k).course.letterGrade=='C'||student.get(k).course.letterGrade=='c'){
						returnArray[2]++;
					}
					else if(student.get(k).course.letterGrade=='D'||student.get(k).course.letterGrade=='d'){
						returnArray[3]++;
					}
					else if(student.get(k).course.letterGrade=='F'||student.get(k).course.letterGrade=='f'){
						returnArray[4]++;
					}
				}
			}
			printArray(returnArray);
		}
		else{
			System.out.println("Not enough information entered.\n");
		}
	}
}
