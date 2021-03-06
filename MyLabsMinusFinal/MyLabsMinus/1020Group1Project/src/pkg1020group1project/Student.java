package pkg1020group1project;
/**
 * Class Student is a subclass of User that implements the temporary data storage for the student users.
 * @author Stephen Hybeger
 */
public class Student extends User {
    private String teacherUserName;
    /**
     * An empty constructor for Student that sets default values.
     */
    public Student() {
        teacherUserName = "";
        
    }
    /**
     * A constructor for Student that sets values based on parameters
     * @param userName The username for the student
     * @param lastName The last name of the student
     * @param firstName The first name of the student
     * @param eMail The email of the student
     * @param teacherUserName The username of the teacher of the student.
     */
    public Student(String userName, String lastName, String firstName, String eMail, String teacherUserName) {
        super (userName, lastName, firstName, eMail);
        this.teacherUserName = teacherUserName;
    }
    /**
     * Sets the username of the teacher of the student.
     * @param newTeacherUserName The username to which the String representing the username of the Teacher is to be set.
     */
    public void setTeacherUserName(String newTeacherUserName) {
        this.teacherUserName = newTeacherUserName;
    }
    /**
     * Returns the username of the teacher of the student.
     * @return The username of the teacher of the student.
     */
    public String getTeacherUserName() {
        return teacherUserName;
    }
    /**
     * Returns a String representation of the Object.
     * @return A String representation of the Object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nTeacher: " + teacherUserName;
 }
}