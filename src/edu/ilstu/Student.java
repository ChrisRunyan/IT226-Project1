package edu.ilstu;

/**
 * 
 * @author Christopher Runyan
 */
public class Student{
	public String firstName;
	public String lastName;
	public String ulid;
	public Course course;
	
	public Student(){
		this.firstName=null;
		this.lastName=null;
		this.ulid=null;
	}
	
	public Student (String firstName, String lastName, String ulid){
		this.firstName=firstName;
		this.lastName=lastName;
		this.ulid=ulid;
	}
	
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	
	public void setULID(String ulid){
		this.ulid=ulid;
	}
	
	public void addGrades(String[] assignmentNames, String[] assignmentGrades, int assignmentGradesSize){
		double[] assignmentGradesD=new double[50];
		
		for(int i=0; i<assignmentGradesSize; i++){
			assignmentGradesD[i]=Double.parseDouble(assignmentGrades[i]);
		}
		
		for(int i=0; i<assignmentGradesSize; i++){
			this.course.addGrades(assignmentNames[i], assignmentGradesD[i], ulid);
		}
	}
	
	public void addLetterGrade(String letterGrade){
		char letterGradeC=letterGrade.charAt(0);
		
		this.course.setLetterGrade(letterGradeC);
	}
	
	public void addCourse(Course course){
		this.course=course;
	}
	
	public void printStudentName(){
		System.out.println("First Name: "+firstName+"\nLast Name: "+lastName+"\nULID: "+ulid);
	}
	
	public void printStudentCourseSummary(){
		course.printCourseSummary();
	}
}
