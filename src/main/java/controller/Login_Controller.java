package controller;

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Login;
import javax.swing.*;
import java.sql.SQLException;
import java.util.*;
import javax.persistence.PersistenceException;
import view.NewPassword;
import view.Question;
import view.getPassword;

public class Login_Controller {
    
    //Validates the input and send it to the model
    public Boolean LoginUser(Login view) throws EmptyException {
        validations val = new validations();
        Boolean validate = false;
        User user;
        UserCRUD model = new UserCRUD();

        Map<String, String> data = new HashMap<>();
        data.put("email", view.getEmailText());
        data.put("password", view.getPasswordText());

        if (!val.validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
                validate = validateLogin(data, user, view);
            }catch(PersistenceException ex){
                showError(ex, view);
            }

        }
        
        return validate;
    }
   
    public Boolean answerUser(Question view) throws EmptyException{        
        Boolean validate = false;
        validations val = new validations();
        User user;
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("answer", view.getAnswer());
        
        if (!val.validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getAnswer(data.get("answer"));
                System.out.println(user);
                validate = val.validateAnswer(data, user, view);
            }catch(Exception ex){
                showErrorAnswer(ex, view);
            }
        }
        return validate;
    }
   
    //Verify the password
    public Boolean validateLogin(Map<String,String> data, User user, Login view){
        Boolean validatePass = false;
        if(user.getPassword().equals(data.get("password"))){
            JOptionPane.showMessageDialog(
                    view, "Login success" , "Success", JOptionPane.INFORMATION_MESSAGE);
            validatePass = true;
        }
        
        return validatePass;
    }

    //Display an OptionPane in the view with the error
    public void showError(Exception ex, Login view){
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


