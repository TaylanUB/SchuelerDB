import java.util.*;

// Aufgabe 1c
public class Student {
	private int id;
	private String firstname;
	private String lastname;
	private Date birthDate;
	private Address address;
	private List<SubjectStudent> subjects;
	
	// Aufgabe 2 part 1
	public Student(int id, String lastname, String firstname, Date birthDate, Address address) {
		if (id < 1) {
			// XXX error
		}
		if (lastname == null || firstname == null) {
			// XXX error
		}
		if (birthDate == null) {
			// XXX error
		}
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthDate = birthDate;
		this.address = address;
		subjects = new ArrayList<SubjectStudent>();
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return firstname + " " + lastname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	// This method for use by StudentDB only.
	// XXX How can we change the visibility from public to something that only StudentDB can access?
	public void addSubject(SubjectStudent ss) {
		subjects.add(ss);
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public int getAge() {
		final double millisecondsPerYear = 1000l * 60l * 60l * 24l * 365l; // Ignore leap seconds and years.
		return (int)(dateSubtract(new Date(), birthDate) / millisecondsPerYear);
	}
	
	public void setMarkForSubject(Subject subject, int mark) {
		for (SubjectStudent ss : subjects) {
			if (ss.getSubject() == subject) {
				ss.setMark(mark);
			}
		}
	}
	
	public int getMarkForSubject(Subject subject) {
		for (SubjectStudent ss : subjects) {
			if (ss.getSubject() == subject) {
				return ss.getMark();
			}
		}
		return -1;
	}
	
	public double getAverageMark() {
		double total = 0;
		double count = 0;
		for (SubjectStudent ss : subjects) {
			total += ss.getMark();
			count += 1;
		}
		return total / count;
	}
	
	public List<Subject> getAllSubjects() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		for (SubjectStudent ss : this.subjects) {
			subjects.add(ss.getSubject());
		}
		return subjects;
	}
	
	// Aufgabe 3
	public void printToStdout() {
		System.out.println("=== Student Nr. " + id + " ===");
		System.out.println("Name: " + getName());
		System.out.println("Age: " + getAge());
		System.out.println("Address: " + getAddress());
	}
	
	private static double dateSubtract(Date d1, Date d2) {
		return d1.getTime() - d2.getTime();
	}
}