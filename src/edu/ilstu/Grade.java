package edu.ilstu;

/**
 * 
 * @author Christopher Runyan
 */
public class Grade{
	public String gradeTitle;
	public double grade;
	public Student student;
	
	public Grade(){
		this.gradeTitle=null;
		this.grade=0.0;
	}
	
	public Grade(String gradeTitle, double grade, Student student){
		this.gradeTitle=gradeTitle;
		this.grade=grade;
		this.student=student;
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
