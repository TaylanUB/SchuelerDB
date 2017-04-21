import java.util.*;

public class Subject {
	private int id;
	private String name;
	private List<SubjectStudent> students;
	
	public Subject(int id, String name) {
		if (name == null) {
			// XXX error
		}
		this.id = id;
		this.name = name;
		students = new ArrayList<SubjectStudent>();
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	// This method for use by StudentDB only.
	// XXX How can we change the visibility from public to something that only StudentDB can access?
	public void addStudent(SubjectStudent ss) {
		students.add(ss);
	}
	
	public List<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		for (SubjectStudent ss : this.students) {
			students.add(ss.getStudent());
		}
		return students;
	}
	
	public double getAverageMark() {
		double total = 0;
		double count = 0;
		for (SubjectStudent ss : students) {
			total += ss.getMark();
			count += 1;
		}
		return total / count;
	}
}