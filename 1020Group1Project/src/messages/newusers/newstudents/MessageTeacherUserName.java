package messages.newusers.newstudents;
import users.Student;
import users.Teacher;
import app.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Message;
import users.User;
/**
 * Class MessageTeacherUserName is a subclass of Message that implements the prompt for the student user to pick his/her teacher.
 * @author Stephen Hybeger
 */
//Prompts student for teacher selection
public class MessageTeacherUserName extends Message{
    //An array that orders the available teachers 
    ArrayList<Teacher> available = new ArrayList<>();
    
    //Tracks user data
    private int userCoordinates;
    /**
     * This constructor enables the setting of initial values for fields, messsage, needs, and the app.
     * @param app //A link to the MyLabsMinusApp
     */
    public MessageTeacherUserName(MyLabsMinusApp app) {
        //Will print out prompt with dynamic list of available teachers 
        super ("", "Please enter an positive integer indicating your choice out of those presented below.", app);
    }
    //Returns user data coordinates
    /**
     * Returns the user coordinates given by the application class in order to allow the class to specify which User in the userArray to modify.
     * @return The coordinates of the User in the userArray to be modified.
     */
    public int getUserCoordinates() {
        return userCoordinates;
    }
    //sets user data coordinates
    /**
     * Sets the user coordinates to correspond with the index of that user in student sub-array (y-coordinate only since we already know that the user is a student).
     * @param newUserCoordinates The index of the student being modified in the student sub-array of the userArray.
     */
    public void setUserCoordinates(int newUserCoordinates) {
        userCoordinates = newUserCoordinates;
    }
    /**
     * An override of the runOverride method of the Message class. Sets the teacher associated with the student by presenting a list of teachers and allowing the student to pick by selecting a number from that list.
     * @return Whether or not the program ran properly with valid input. 
     * @throws NumberFormatException 
     */
    @Override
    public boolean runOverride() throws NumberFormatException {
        try {
            int answer = Integer.parseInt(getCurrentInput());
            if (answer == 1) {
                ((Student)app.getUserArray()[0].get(userCoordinates)).setTeacherUserName("None");
                try {
                    //starts end of account creation chain.
                    app.printMessage(12, true);
                } catch (IOException ex) {
                    Logger.getLogger(MessageTeacherUserName.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } else if (answer > 0) {
                ((Student)app.getUserArray()[0].get(userCoordinates)).setTeacherUserName(available.get(answer - 2).getUserName());    
                available.get(answer -2).getArrayOfStudents().add(((Student)app.getUserArray()[0].get(userCoordinates)));
                try {
                    //starts end of account creation chain.
                    app.printMessage(12, true);
                } catch (IOException ex) {
                    Logger.getLogger(MessageTeacherUserName.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException ex){
                return false;
        }
    }
    //Prints the message content (prompt) to the screen.
    /**
     * An override of the printMessage method of Message. The only difference is that gets all of the teachers that haven't reached their max number of students yet and presents them as options to the user.
     */
    @Override
    public void printMessage() {
        //holds the content of the message in originalMessage in case validation is triggered later on.
        String originalMessage = getMessage();
        //success will track if the input from the user passes validation. 
        boolean success = false;
        //implements validation 
        while (!success) {
            String availableMessage = "Please select between the available teachers below:\n1. None";
            for (int i = 0; i < app.getUserArray()[1].size(); i++) {
                Teacher currentTeacher = (Teacher)app.getUserArray()[1].get(i);
                if (Integer.parseInt(currentTeacher.getNumberOfStudents()) - currentTeacher.getArrayOfStudents().size() > 0) {
                    availableMessage += "\n" + (i + 2) + ". " + ((User)app.getUserArray()[1].get(i)).getUserName();
                    available.add(currentTeacher);
                }
            }
            setMessage(availableMessage);
            //prints message content
            System.out.print(getMessage() + "\n\n>>>");
            //waits for input from user.
            setCurrentInput(input.nextLine());
            //moves cursor down line.
            System.out.println();
            //checks if command was given
            if (getCurrentInput().startsWith("\\")) {
                try {
                    //detects if unrecognized command was given if not input is validated.
                    if (!executeCommand()) {
                        //replaces original message with validation message
                        setMessage("Command not recognized. Enter \\help for a list of valid commands");
                    } else {
                        success = true;
                    }
                    //runs for non-command input if runOverride returns that the input was validated.
                } catch (IOException ex) {
                    Logger.getLogger(MessageTeacherUserName.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (runOverride()) {
                success = true;
                //runs if input does not pass validation
            } else {
                printNeeds(); //prints error and reprompts
            }
        }
        //reloads original message 
        setMessage(originalMessage);
    }
}