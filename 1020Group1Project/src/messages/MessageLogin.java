package messages;


import app.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sahyberger
 */
/**Needs Aaron**/
//Quiz/main menu branch of program not implemented yet.
//Login component. Prompts user for username
public class MessageLogin extends Message{
    //Creates predefined message.
    public MessageLogin(MyLabsMinusApp app) {
        super("Please enter your username to login", app);
    }
    @Override
    public boolean runOverride() {
        /**Needs Anthony's code**/
        //will track if username search was sucessful or not
        boolean matched = false;
        /*
        for (int i = app.getUserArray().length; i > 0; i--) {
            for(int e = app.getUserArray()[i].size(); e > 0; e--) {
                //if (getCurrentInput().equals(app.getUserArray()[i].get(e)).getUsername()) {
                    matched = true;
                //}
            }
        }
        */
        if (matched) {
            return true;
        } else {
            return false;
        }
    }
}
