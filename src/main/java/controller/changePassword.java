/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.EmptyException;
import java.util.HashMap;
import java.util.Map;
import model.crud.UserCRUD;
import model.schemas.User;
import view.NewPassword;
import view.getPassword;

/**
 *
 * @author rod
 */
public class changePassword {
     public Boolean passwordUser(getPassword pass) throws Exception{
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
           
            user = model.getUser(data.get("email"));
            validate = val.validateEmail(data, user, pass);
            

        }
        
        return validate;
    }

    
    
    public User newPassword(NewPassword pass) throws Exception{
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
        user = model.getUser(data.get("email"));
        }
        
        return user;
    }
    
    public String getQuestion(getPassword pass) throws Exception{
        
        String question = "Not found";
        
        User user;
        
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmailText());
           
        user = model.getQuestionQuery(data.get("email"));
        question = user.getQuestion();
            

        return question;
    }
    public String getEmail(getPassword pass) throws Exception{
        String email = "Not found";
        User user;
        UserCRUD model = new UserCRUD();
        
        Map<String, String> data = new HashMap<>();
        data.put("email", pass.getEmailText());
    
        user = model.getQuestionQuery(data.get("email"));
        email = user.getEmail();
           

        return email;
    }
  
}
