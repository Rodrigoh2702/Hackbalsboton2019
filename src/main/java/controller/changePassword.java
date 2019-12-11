/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.EmptyException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import model.crud.UserCRUD;
import model.schemas.User;
import view.NewPassword;
import view.Question;
import view.getPassword;

/**
 *
 * @author rod
 */
public class changePassword {
     public Boolean passwordUser(getPassword pass) throws EmptyException{
        validations val = new validations();
        Boolean validate = false;
        
        User user;
        
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmailText());
        
        if (!val.validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
                validate = val.validateEmail(data, user, pass);
            }catch(PersistenceException ex){
                showErrorPass(ex, pass);
            }

        }
        
        return validate;
    }

    
    
    public User newPassword(NewPassword pass) throws EmptyException{
        User user = null;
        validations val = new validations();
        UserCRUD model = new UserCRUD();
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmail());
        System.out.println(pass.getEmail());
        
        if (!val.validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
            }catch(PersistenceException ex){
                showErrorNewPass(ex, pass);
            }

        }
        
        return user;
    }
    
    public String getQuestion(getPassword pass) throws EmptyException{
        
        String question = "Not found";
        
        User user;
        
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmailText());
            try{
                user = model.getQuestionQuery(data.get("email"));
                question = user.getQuestion();
            }catch(Exception ex){
                showErrorPass(ex, pass);
            }

        return question;
    }
    public String getEmail(getPassword pass) throws EmptyException{
        String email = "Not found";
        User user;
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmailText());
            try{
                user = model.getQuestionQuery(data.get("email"));
                email = user.getEmail();
            }catch(PersistenceException ex){
                showErrorPass(ex, pass);
            }

        return email;
    }
    
    public void showErrorPass(Exception ex, getPassword view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must fill every text field" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof SQLException){
            JOptionPane.showMessageDialog(
                    view, "Database error" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showErrorNewPass(Exception ex, NewPassword view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must fill every text field" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof SQLException){
            JOptionPane.showMessageDialog(
                    view, "Database error" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showErrorAnswer(Exception ex, Question view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must fill every text field" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof SQLException){
            JOptionPane.showMessageDialog(
                    view, "Database error" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error :(", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
