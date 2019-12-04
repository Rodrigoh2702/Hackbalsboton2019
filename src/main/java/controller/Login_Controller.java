package controller;

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Login;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;
import view.NewPassword;
import view.Question;
import view.getPassword;

public class Login_Controller {
    
    //Validates the input and send it to the model
    public Boolean LoginUser(Login view) throws EmptyException {

        Boolean validate = false;
        User user;
        UserCRUD model = new UserCRUD();

        Map<String, String> data = new HashMap<>();
        data.put("email", view.getEmailText());
        data.put("password", view.getPasswordText());

        if (!validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
                validate = validateLogin(data, user, view);
            }catch(Exception ex){
                showError(ex, view);
            }

        }
        
        return validate;
    }
    
    public Boolean passwordUser(getPassword pass) throws EmptyException{
        
        Boolean validate = false;
        
        User user;
        
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmailText());
        
        if (!validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
                validate = validateEmail(data, user, pass);
            }catch(Exception ex){
                showErrorPass(ex, pass);
            }

        }
        
        return validate;
    }

    public Boolean answerUser(Question view) throws EmptyException{        
        Boolean validate = false;
        User user;
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("answer", view.getAnswer());
        
        if (!validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getAnswer(data.get("answer"));
                validate = validateAnswer(data, user, view);
            }catch(Exception ex){
                showErrorAnswer(ex, view);
            }

        }
        
        return validate;
    }
    
    public User newPassword(NewPassword pass) throws EmptyException{
        User user = null;
        UserCRUD model = new UserCRUD();
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmail());
        System.out.println(pass.getEmail());
        
        if (!validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
            }catch(Exception ex){
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
            }catch(Exception ex){
                showErrorPass(ex, pass);
            }

        return email;
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
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
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


