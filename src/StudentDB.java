import java.util.*;

/**
 *  Structure:
 *  
 *  The Student, Subject, and SubjectStudent classes are modeled after a relational database.
 *  
 *  As such, Student and Subject instances do not contain references to each other, and
 *  SubjectStudent objects represent relations between a subject and a partaking student.
 *  In addition, SubjectStudent objects contain the student's mark in the subject.
 *  
 *  To ease working with the objects however, Student and Subject objects are assigned a List of
 *  related SubjectStudent objects.  Thus for instance the Student class is able to implement methods
 *  that access the subjects the student partakes in and her/his grades.
 */

interface StudentIterator {
	void apply(Student student);
}

interface SubjectIterator {
	void apply(Subject subject);
}

// Aufgabe 5 part 1
public class StudentDB {
	private static StudentDB instance;
	
	private static Student[] students;
	private static Subject[] subjects;
	private static SubjectStudent[] subjectStudents;
	private static int lastStudentID;
	private static int lastSubjectID;
	private static int lastSubjectStudentID;
	
	// Aufgabe 5 part 2
	public static StudentDB getInstance(int capacity) throws Exception {
		if (instance == null) {
			instance = new StudentDB();
			students = new Student[capacity];
			subjects = new Subject[1000];
			subjectStudents = new SubjectStudent[1000000];
			lastStudentID = 0;
			lastSubjectID = 0;
			lastSubjectStudentID = 0;
		} else {
			throw new Exception("Database already initialized.");
		}
		return instance;
	}
	
	// Aufgabe 9
	public static StudentDB getInstance() {
		if (instance == null) {
			try {
				return getInstance(1000000);
			} catch (Exception e) { }
		}
		return instance;
	}
	
	private StudentDB() {}
	
	public Student[] getStudents() {
		return students;
	}
	
	public Subject[] getSubjects() {
		return subjects;
	}
	
	public SubjectStudent[] getSubjectMarks() {
		return subjectStudents;
	}
	
	// Student methods
	
	// Aufgabe 5 part 4
	public Student addStudent(String lastname, String firstname, Date birthDate, Address address) {
		Student student = new Student(lastStudentID + 1, lastname, firstname, birthDate, address);
		students[lastStudentID] = student;
		lastStudentID += 1;
		return student;
	}
	
	public Student getStudent(int id) {
		return students[id - 1];
	}
	
	// Aufgabe 6
	public void removeStudent(int id) {
		students[id - 1] = null;
	}
	
	public void iterateStudents(StudentIterator iterator) {
		for (Student s : students) {
			if (s != null) {
				iterator.apply(s);
			}
		}
	}
	
	// Aufgabe 5 part 3
	public void printStudents() {
		iterateStudents(s -> { s.printToStdout(); System.out.println(); });
	}
	
	// Aufgabe 7
	public int countStudents() {
		int i = 0;
		for (Student s : students) {
			if (s != null) {
				i += 1;
			}
		}
		return i;
	}
	
	// Subject methods
	
	public Subject addSubject(String name) {
		Subject subject = new Subject(lastSubjectID + 1, name);
		subjects[lastSubjectID] = subject;
		lastSubjectID += 1;
		return subject;
	}
	
	public Subject getSubject(int id) {
		return subjects[id - 1];
	}
	
	public void removeSubject(int id) {
		subjects[id - 1] = null;
	}
	
	public void iterateSubjects(SubjectIterator iterator) {
		for (Subject s : subjects) {
			if (s != null) {
				iterator.apply(s);
			}
		}
	}
	
	// SubjectStudent methods
	
	public void addStudentToSubject(int studentID, int subjectID) {
		Student student = students[studentID - 1];
		Subject subject = subjects[subjectID - 1];
		
		if (student.getAllSubjects().contains(subject)) {
			// XXX error
		}
		
		SubjectStudent subjectStudent = new SubjectStudent(lastSubjectStudentID + 1, subject, student);
		subjectStudents[lastSubjectStudentID] = subjectStudent;
		lastSubjectStudentID += 1;
		
		student.addSubject(subjectStudent);
		subject.addStudent(subjectStudent);
	}
	
	// Statistics
	
	// Aufgabe 8a
	public double getAverageMark() {
		double total = 0;
		double count = 0;
		for (SubjectStudent ss : subjectStudents) {
			if (ss != null) {
				total += ss.getMark();
				count += 1;
			}
		}
		return total / count;
	}
	
	// Aufgabe 8b
	public double getAverageStudentAge() {
		double total = 0;
		double count = 0;
		for (Student s : students) {
			if (s != null) {
				total += s.getAge();
				count += 1;
			}
		}
		return total / count;
	}
	
	// Aufgabe 8c
	public Student getOldestStudent() {
		Student oldest = null;
		for (Student s : students) {
			if (s != null) {
				if (oldest == null || s.getAge() > oldest.getAge()) {
					oldest = s;
				}
			}
		}
		return oldest;
	}
	
	// Aufgabe 8d
	public Student getStudentWithBestAverage() {
		double highestAverage = -1;
		Student highestAverageStudent = null;
		for (Student s : students) {
			if (s != null) {
				double averageMark = s.getAverageMark();
				if (averageMark > highestAverage) {
					highestAverageStudent = s;
					highestAverage = s.getAverageMark();
				}
			}
		}
		return highestAverageStudent;
	}
}