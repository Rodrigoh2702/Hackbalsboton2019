/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import model.schemas.User;
import view.Question;
import view.getPassword;

/**
 *
 * @author rod
 */
public class validations {
    public Boolean validateEmail(Map<String,String> data, User user, getPassword view){
        Boolean validateEmail = false;
        if(user.getEmail().equals(data.get("email"))){
            JOptionPane.showMessageDialog(
                    view, "Email success" , "Success", JOptionPane.INFORMATION_MESSAGE);
            validateEmail = true;
        }
        
        return validateEmail;
    }
    
    public Boolean validateAnswer(Map<String,String> data, User user, Question view){
        Boolean validateAns = false;
        if(user.getAnswer().equals(data.get("answer"))){
            JOptionPane.showMessageDialog(
                    view, "Correct" , "Success", JOptionPane.INFORMATION_MESSAGE);
            validateAns = true;
        }
        
        return validateAns;
    }
    
    //Validate that there is no empty information
    public boolean validCompleteness(Map<String,String> data){
        boolean isComplete = false;
        Set<String> keys = data.keySet();
        for(String key: keys){
            if(!data.get(key).isEmpty()){ //Checks that there is no empty information
                isComplete = true;
            }
        }

        return isComplete;
    }

}
