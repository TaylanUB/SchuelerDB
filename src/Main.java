import java.util.*;
import java.text.*;

public class Main {
	public static void main(String[] args){
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = formatter.parse("2000-01-01");
			Address address = new Address("Musterstr. 5", "35321", "Laubach");
			
			// Aufgabe 1d bzw. Aufgabe 2 / part 2
			// (Aufgabe 1d impliziert public Attribute, welche wir nicht haben.)
			{
				Student jane = new Student(1, "Doe", "Jane", birthDate, address);

				Student john = new Student(2, "Doe", "John", birthDate, address);

				System.out.println(jane.getName());
				jane.printToStdout();

				System.out.println(john.getName());
				john.printToStdout();

				// Aufgabe 4
				Student[] students = new Student[100];
				for (int i = 0; i < 100; ++i) {
					students[i] = new Student(i, "Clone", "Rei", birthDate, address);
					students[i].printToStdout();
				}
			}
			System.out.println("\n\n\n\n");

			StudentDB db = StudentDB.getInstance();
			Student jane = db.addStudent("Doe", "Jane", birthDate, address);
			Student john = db.addStudent("Doe", "John", birthDate, address);
			Subject maths = db.addSubject("Maths");
			Subject compsci = db.addSubject("CompSci");
			db.addStudentToSubject(jane.getID(), maths.getID());
			db.addStudentToSubject(jane.getID(), compsci.getID());
			db.addStudentToSubject(john.getID(), maths.getID());
			db.addStudentToSubject(john.getID(), compsci.getID());
			jane.setMarkForSubject(maths, 5);
			jane.setMarkForSubject(compsci, 4);
			john.setMarkForSubject(maths, 3);
			john.setMarkForSubject(compsci, 2);

			db.printStudents();
			System.out.println(jane.getAverageMark());
			System.out.println(john.getAverageMark());
			System.out.println(maths.getAverageMark());
			System.out.println(compsci.getAverageMark());
			System.out.println(db.getAverageMark());
			System.out.println(db.getStudentWithBestAverage().getName());
		} catch(Exception exc){}
	}
}