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
	public char letterGrade;
	
	public String getUlid() {
		return ulid;
	}

	public void setUlid(String ulid) {
		this.ulid = ulid;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Student(){
		this.firstName=null;
		this.lastName=null;
		this.ulid=null;
		this.letterGrade=' ';
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
		this.letterGrade=letterGrade.charAt(0);
	
		this.course.setLetterGrade(letterGradeC);
	}
	
	public char returnLetterGrade(){
		return letterGrade;
	}
	
	public void addCourse(Course course){
		this.course=course;
	}
	
	public String returnStudentName(){
		return ("First Name: "+firstName+"\nLast Name: "+lastName+"\nULID: "+ulid);
	}
	
	public String returnStudentCourseSummary(){
		return course.returnCourseSummary();
	}
	public String toString1(){
		return "Student Id"+","+ "Course"+ "," + "Semester" + ","+  "Year"+ ","+
				"Assignment name" + "," + "Points" ;
	}
}
