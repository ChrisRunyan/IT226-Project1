package edu.ilstu;

import java.util.LinkedList;

/**
 * 
 * @author Hoang Le
 *
 * @param <E>
 */
public class StudentDataLinkedList<E> {
	public LinkedList<E> list = new LinkedList<E>();
	
	public StudentDataLinkedList(LinkedList<E> list){
		this.list = list;
	}
	
	public void printStudentData(){
		for(int i= 0; i< list.size(); i++){
			Student a = null;
			a = (Student) list.get(i);
			a.printStudentName();
			a.printStudentCourseSummary();
		}
	}

	public void printCourseData(){
		for(int i= 0; i< list.size(); i++){
			Course a = null;
			a = (Course) list.get(i);
			a.printCourseSummary();
		}
	}
}
