package controller;

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Login;
import javax.swing.*;
import java.util.*;
import view.Question;

public class Login_Controller {
    
    //Validates the input and send it to the model
    public Boolean LoginUser(Login view) throws Exception {
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
            
        user = model.getUser(data.get("email"));
        validate = validateLogin(data, user, view);
            

        }
        
        return validate;
    }
   
    public Boolean answerUser(Question view) throws Exception{        
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
        user = model.getAnswer(data.get("answer"));
        System.out.println(user);
        validate = val.validateAnswer(data, user, view);
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
   
    
    
    
    
    
    
}


