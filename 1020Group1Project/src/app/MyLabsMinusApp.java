package app;

//imports the other packages.
import messages.usermenus.teachermenu.*;
import messages.usermenus.studentmenu.*;
import messages.usermenus.parentmenu.*;
import java.io.IOException;
import messages.newusers.*;
import messages.newusers.newparents.*;
import messages.newusers.newstudents.*;
import messages.newusers.newteachers.*;
import messages.*;
import users.*;
//imports ArrayList
import java.util.ArrayList;

public class MyLabsMinusApp {
    private int currentMessage; //a field to hold the index of the currently active message
    private ArrayList[] userArray = new ArrayList[3]; //An array of ArrayLists designed to hold quick-access user data (not all user data)
    private ArrayList<Integer> messageHistory = new ArrayList<>(); //An ArrayList that holds the past active messages
    private static ArrayList<Message> messageArray = new ArrayList<>(); //An ArrayList that holds the message components that make up the functional part of the application.
    private User currentUser; //a field to hold the user currently logged in.
    public static final User NULLUSER = new User("None", "None", "None", "None"); //a constant user for that will be used when no one is logged in.
    
    //Constructor for MyLabsMinusApp
    public MyLabsMinusApp() {
        //Initializes current user as NULLUSER.
        currentUser = NULLUSER;
        //Will intialize the ArrayLists inside userArray.
        userArray[0] = new ArrayList<Student>();
        userArray[1] = new ArrayList<Teacher>();
        userArray[2] = new ArrayList<Parent>();
        //Adds all the message components to the array.
        messageArray.add(new MessageOpen(this)); //0
        messageArray.add(new MessageLogin(this)); //1
        messageArray.add(new MessageWizard(this)); //2
        messageArray.add(new MessageUserName(this)); //3
        messageArray.add(new MessageFirstName(this)); //4
        messageArray.add(new MessageLastName(this)); //5
        messageArray.add(new MessageEmail(this)); //6
        messageArray.add(new MessageGradeLevel(this)); //7
        messageArray.add(new MessageTeacherUserName(this)); //8
        messageArray.add(new MessageNumStudents(this)); //9
        messageArray.add(new MessageNumChildren(this)); //10
        messageArray.add(new MessageChildUserName(this)); //11
        messageArray.add(new MessageConfirmStudent(this)); //12
        messageArray.add(new MessageConfirmTeacher(this)); //13
        messageArray.add(new MessageConfirmParent(this)); //14
        messageArray.add(new MessageStudentMenu(this)); //15
        messageArray.add(new MessageTeacherMenu(this)); //16
        messageArray.add(new MessageParentMenu(this)); //17
        messageArray.add(new MessageQuiz(this)); //18
        messageArray.add(new MessageSearch(this)); //19
        messageArray.add(new MessagePrintReport(this)); //20
        
    }
    
    //Door to program
    public static void main(String[] args) throws IOException {
        MyLabsMinusApp app = new MyLabsMinusApp(); //Calls constructor and does initial setup
        messageArray.get(0).printMessage(); //Starts off message chain.
    }
    //prints given menu at index messageIndex and decides whether or not to include the message in the message history. 
    public void printMessage(int messageIndex, boolean setLastMessage) throws IOException {
        //If tracking message history.
        if (setLastMessage) {
            //adds the message you are leaving to the message history.
            messageHistory.add(currentMessage);
        }
        //sets the message you are going to as currentMessage.
        currentMessage = messageIndex;
        //calls print to screen method for the new message. 
        messageArray.get(messageIndex).printMessage();
    }
    //Creates new Student user
    public void createNewStudent() throws IOException {
        //initializes confirmed to regulate validation cycling.
        boolean confirmed = false;
        //Will initialize new Student into the user array pass the coordinates for the new user to the appropriate message components.
        userArray[0].add(new Student());
        ((MessageUserName)messageArray.get(3)).setUserCoordinates(0, userArray[0].size()-1);
        ((MessageFirstName)messageArray.get(4)).setUserCoordinates(0, userArray[0].size()-1);
        ((MessageLastName)messageArray.get(5)).setUserCoordinates(0, userArray[0].size()-1);
        ((MessageEmail)messageArray.get(6)).setUserCoordinates(0, userArray[0].size()-1);
        ((MessageGradeLevel)messageArray.get(7)).setUserCoordinates(userArray[0].size()-1);
        ((MessageTeacherUserName)messageArray.get(8)).setUserCoordinates(userArray[0].size()-1);
        
        //Placeholder code for debugging.
        //((MessageEmail)messageArray.get(6)).setUserCoordinates(0,0);
        
        //A while loop that implements validation (some validation may not work properly until all code is integrated).
        while (!confirmed) {
            //starts off user creation messages
            printMessage(3, true);
            //sets confirmed to the result of the end of the user creation chain.
            confirmed = Boolean.parseBoolean(messageArray.get(12).getCurrentInput());
        }
        //Clears message history
        messageHistory.clear();
        //returns you to the starting message.
        printMessage(0, false);
    }
    //Creates new Teacher user
    public void createNewTeacher() throws IOException {
        //initializes confirmed to regulate validation cycling.
        boolean confirmed = false;
        //Will initialize new Teacher into the user array pass the coordinates for the new user to the appropriate message components.
        userArray[1].add(new Teacher());
        ((MessageUserName)messageArray.get(3)).setUserCoordinates(1, userArray[1].size()-1);
        ((MessageFirstName)messageArray.get(4)).setUserCoordinates(1, userArray[1].size()-1);
        ((MessageLastName)messageArray.get(5)).setUserCoordinates(1, userArray[1].size()-1);
        ((MessageEmail)messageArray.get(6)).setUserCoordinates(1, userArray[1].size()-1);
        ((MessageNumStudents)messageArray.get(9)).setUserCoordinates(userArray[1].size()-1);
        
        //Placeholder code for debugging.
        //((MessageEmail)messageArray.get(6)).setUserCoordinates(0,1);
        
        //A while loop that implements validation (some validation may not work properly until all code is integrated).
        while (!confirmed) {
            //starts off user creation messages
            printMessage(3, true);
            //sets confirmed to the result of the end of the user creation chain.
            confirmed = Boolean.parseBoolean(messageArray.get(13).getCurrentInput());
        }
        //Clears message history
        messageHistory.clear();
        //returns you to the starting message.
        printMessage(0, false);
    }
    
    public void createNewParent() throws IOException {
        //initializes confirmed to regulate validation cycling.
        boolean confirmed = false;
        userArray[2].add(new Parent());
        ((MessageUserName)messageArray.get(3)).setUserCoordinates(2, userArray[2].size()-1);
        ((MessageFirstName)messageArray.get(4)).setUserCoordinates(2, userArray[2].size()-1);
        ((MessageLastName)messageArray.get(5)).setUserCoordinates(2, userArray[2].size()-1);
        ((MessageEmail)messageArray.get(6)).setUserCoordinates(2, userArray[2].size()-1);
        ((MessageNumChildren)messageArray.get(10)).setUserCoordinates(userArray[2].size()-1);
        ((MessageChildUserName)messageArray.get(11)).setUserCoordinates(userArray[2].size()-1);
        //A while loop that implements validation (some validation may not work properly until all code is integrated).
        while (!confirmed) {
            //starts off user creation messages
            printMessage(3, true);
            //sets confirmed to the result of the end of the user creation chain.
            confirmed = Boolean.parseBoolean(messageArray.get(14).getCurrentInput());
        }
        //Clears message history
        messageHistory.clear();
        //returns you to the starting message.
        printMessage(0, false);
    }
    //returns the index of the current message.
    public int getCurrentMessage() {
        return currentMessage;
    }
    //allows user to set the current message by specifying the index.
    public void setCurrentMessage(int newCurrentMenu) {
        currentMessage = newCurrentMenu;
    }
    //returns the index of the previous message
    public int getLastMessage(boolean accessing) {
        //gets the index of the previous message.
        int transfer = messageHistory.get(messageHistory.size()-1);
        //if accessing removes last messge from the message history.
        if (accessing) {
            messageHistory.remove(messageHistory.size()-1);
        }
        //returns index
        return transfer;
    }
    //allows user to set the previous message in the message history.
    public void setLastMessage(int message) {
        messageHistory.add(message);
    }
    //returns the message history array list
    public ArrayList<Integer> getMessageHistory() {
        return messageHistory;
    }
    //returns the array of message components
    public ArrayList<Message> getMessageArray() {
        return messageArray;
    }
    //returns the array of quick access user data.
    public ArrayList[] getUserArray() {
        return userArray;
    }
    //returns the currently logged in user.
    public User getCurrentUser() {
        return currentUser;
    }
    //sets the current User to a new User.
    public void setCurrentUser(User newUser) {
        currentUser = newUser;
    }
    //checks if username matches between parameter and any user.
    public boolean isMatching(String userName) {
        boolean success = false;
        for (ArrayList userArray1 : this.getUserArray()) {
            for (int e = 0; e < userArray1.size(); e++) {
                String currentName = ((User) userArray1.get(e)).getUserName();
                if (currentName.equals(userName)) {
                    success = true;
                } 
            }
        }
        return success;
    }
}
