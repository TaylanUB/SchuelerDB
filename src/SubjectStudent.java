public class SubjectStudent {
	private int id;
	private Subject subject;
	private Student student;
	private int mark;
	
	public SubjectStudent(int id, Subject subject, Student student) {
		this.id = id;
		this.subject = subject;
		this.student = student;
		mark = 0;
	}
	
	public int getID() {
		return id;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public int getMark() {
		return mark;
	}
}