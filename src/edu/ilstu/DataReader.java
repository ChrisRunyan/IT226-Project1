package edu.ilstu;

/**
 * @author Kevin Haas
 *
 */
public class DataReader {

	private Student[] student = new Student[300];
	private int studentArraySize=0;
	private Course course=null;
	private String courseName="";
	private String courseYear="";
	private String courseSeason="";

	/*
	 * method that formats every line from a .csv in specific formats.
	 * 
	 * line1 is the first line or header line. lines are an array of Strings.
	 * each string is an individual students information.
	 */
	public void formatData(String line1, String[] lines) {
		String[] splice = line1.split(",");
		
		course=new Course(courseName, courseYear, courseSeason);

		for (int i = 0; i < studentArraySize; i++) {
			student[i]=new Student();
			
			if (splice[0].equalsIgnoreCase("student name")) {

				formatFullNameFirst(line1, lines[i], i);

			} else if (splice[0].equalsIgnoreCase("student id")) {

				formatIDFirst(line1, lines[i], i);

			} else if (splice[0].equalsIgnoreCase("first name"))

				formatFirstNameFirst(line1, lines[i], i);

		}

	}

	/*
	 * private method that formats the .csv if it has the student ID first
	 * 
	 * line1 is the header line line is the individual students line studentNum
	 * is the number of the student in the array of Students()
	 */
	private void formatIDFirst(String line1, String line, int studentNum) {
		// splice of line 1
		String[] splice1 = line1.split(",");

		// splice of the individual student's line
		String[] splice = line.split(",");

		// setting student info
		student[studentNum].setULID(splice[0]);
		student[studentNum].setLastName(splice[1]);
		student[studentNum].setFirstName(splice[2]);

		//initializing an array of assignment names
		String assignmentNames[] = new String[50];
		
		//assigns all the assignment names to an array
		//splice.length-3 because it has to take every header name besides "student name", "student id" and "letter grade"
		for (int j = 0; j < (splice1.length - 3); j++) {
			assignmentNames[j] = splice1[j + 2];
		}
		
		//initializes an array of assignment names
		String assignmentGrades[] = new String[50];
		
		//assigns all the grades into an array
		//the array number in assignmentGrades[] corresponds to the array number in assignmentNames[]
		//splice.length-4 because it has to take every line string besides "firstname", "lastname", "student id" and "letter grade"
		for (int j = 0; j < (splice.length - 4) ; j++){
			assignmentGrades[j] = splice[j+3];
		}

		student[studentNum].addCourse(course);
		
		//sends both arrays to get processed by a "setAssignmentGrades()" method
		student[studentNum].addGrades(assignmentNames, assignmentGrades, (splice.length-4));
		
		//sends final letter grade to get processed
		student[studentNum].addLetterGrade(splice[splice.length-1]);
	}

	/*
	 * private method that formats the .csv if it starts with fisrt name
	 * 
	 * line1 is the header line line is the individual students line studentNum
	 * is the number of the student in the array of Students()
	 */

	private void formatFirstNameFirst(String line1, String line, int studentNum) {


		// splice of line 1
		String[] splice1 = line1.split(",");

		// splice of the individual student's line
		String[] splice = line.split(",");

		// setting student info
		student[studentNum].setFirstName(splice[0]);
		student[studentNum].setLastName(splice[1]);
		student[studentNum].setULID(splice[2]);

		//initializing an array of assignment names
		String assignmentNames[] = new String[50];
		
		//assigns all the assignment names to an array
		//splice.length-4 because it has to take every header name besides "first name", "last name", "student id" and "letter grade"
		for (int j = 0; j < (splice1.length - 4); j++) {
			assignmentNames[j] = splice1[j + 3];
		}
		
		//initializes an array of assignment names
		String assignmentGrades[] = new String[50];
		
		//assigns all the grades into an array
		//the array number in assignmentGrades[] corresponds to the array number in assignmentNames[]
		//splice.length-4 because it has to take every line string besides "firstname", "lastname", "student id" and "letter grade"
		for (int j = 0; j < (splice.length - 4) ; j++){
			assignmentGrades[j] = splice[j+3];
		}

		student[studentNum].addCourse(course);
		
		//sends both arrays to get processed by a "setAssignmentGrades()" method
		student[studentNum].addGrades(assignmentNames, assignmentGrades, (splice.length-4));
		
		//sends final letter grade to get processed
		student[studentNum].addLetterGrade(splice[splice.length-1]);
	
	
	}

	/*
	 * private method that formats the .csv if it starts with the full name
	 * 
	 * line1 is the header line line is the individual students line studentNum
	 * is the number of the student in the array of Students()
	 */

	private void formatFullNameFirst(String line1, String line, int studentNum) {

		// splice of line 1
		String[] splice1 = line1.split(",");

		// splice of the individual student's line
		String[] splice = line.split(",");

		// setting student info
		student[studentNum].setLastName(splice[0]);
		student[studentNum].setFirstName(splice[1]);
		student[studentNum].setULID(splice[2]);

		//initializing an array of assignment names
		String assignmentNames[] = new String[50];
		
		//assigns all the assignment names to an array
		//splice.length-3 because it has to take every header name besides "student name", "student id" and "letter grade"
		for (int j = 0; j < (splice1.length - 3); j++) {
			assignmentNames[j] = splice1[j + 2];
		}
		
		//initializes an array of assignment names
		String assignmentGrades[] = new String[50];
		
		//assigns all the grades into an array
		//the array number in assignmentGrades[] corresponds to the array number in assignmentNames[]
		//splice.length-4 because it has to take every line string besides "firstname", "lastname", "student id" and "letter grade"
		for (int j = 0; j < (splice.length - 4) ; j++){
			assignmentGrades[j] = splice[j+3];
		}
		
		student[studentNum].addCourse(course);
		
		//sends both arrays to get processed by a "setAssignmentGrades()" method
		student[studentNum].addGrades(assignmentNames, assignmentGrades, (splice.length-4));
		
		//sends final letter grade to get processed
		student[studentNum].addLetterGrade(splice[splice.length-1]);
	
	}
	
	public Student[] returnStudent(){
		return student;
	}
	
	public void setStudentArraySize(int size){
		studentArraySize=size;
	}
	
	public Course returnCourse(){
		return course;
	}
	
	public void setCourse(Course course){
		this.course=course;
	}
	
	public void setCourseName(String courseName){
		this.courseName=courseName;
	}
	
	public void setCourseYear(String courseYear){
		this.courseYear=courseYear;
	}
	
	public void setCourseSeason(String courseSeason){
		this.courseSeason=courseSeason;
	}
}
