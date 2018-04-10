/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages.newusers;
import app.*;
import messages.Message;
/**
 *
 * @author StephenAHyberger
 */
//prompts user for last name
public class MessageLastName extends Message{
    //tracks user data position
    private int[] userCoordinates = new int[2];
    public MessageLastName(MyLabsMinusApp app) {
        //predefined text
        super ("please enter your last name", "", app);
    }
    //returns user data position
    public int[] getUserCoordinates() {
        return userCoordinates;
    }
    //sets user data position
    public void setUserCoordinates(int userType, int position) {
        userCoordinates[0] = userType;
        userCoordinates[1] = position;
    }
    @Override
    public boolean runOverride() {
        //empty string validation
        if (!getCurrentInput().isEmpty()) {
            //app.getUserArray()[userCoordinates[0]].get(userCoordinates[1]).setLastName(getCurrentInput());
            app.printMessage(6, true);
            return true;
        } else {
            return false;
        }
    }
}
