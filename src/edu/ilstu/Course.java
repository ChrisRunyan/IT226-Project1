package edu.ilstu;

/**
 * 
 * @author Christopher Runyan
 */
public class Course{
	public Grade[] grades;
	public String courseName;
	public String courseYear;
	public String courseSeason;
	public int currentSize;
	public char letterGrade;
	
	public Course(String courseName, String courseYear, String courseSeason){
		this.courseName=courseName;
		this.courseYear=courseYear;
		this.courseSeason=courseSeason;
		this.grades=new Grade[50];
		currentSize=0;
	}
	
	public void addGrades(String gradeTitle, double grade, String studentID){
		this.grades[currentSize]=new Grade(gradeTitle, grade, studentID);
	}
	
	public void setLetterGrade(char letterGrade){
		this.letterGrade=letterGrade;
	}

	public String returnCourseSummary(){
		String toReturn="";
		toReturn=("Course Name: "+courseName+"\nCourse Time: "+courseSeason+courseYear+"\nGrade Summary:");
		for(int i=0; i<currentSize; i++){
			toReturn+=(grades[i].gradeTitle+": "+grades[i].grade);
		}
		return toReturn;
	}
}
