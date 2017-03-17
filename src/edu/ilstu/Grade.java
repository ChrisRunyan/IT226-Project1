package edu.ilstu;

/**
 * 
 * @author Christopher Runyan
 */
public class Grade{
	public String gradeTitle;
	public double grade;
	public String studentID;
	
	public Grade(){
		this.gradeTitle=null;
		this.grade=0.0;
	}
	
	public Grade(String gradeTitle, double grade, String studentID){
		this.gradeTitle=gradeTitle;
		this.grade=grade;
		this.studentID=studentID;
	}
	
	public double returnGrade(String gradeTitle){
		return grade;
	}
	
	public double returnGrade(Student student){
		return grade;
	}
	
	public String returnGradeTitle(Student student){
		return gradeTitle;
	}
}
