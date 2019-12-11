/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import model.bootstraper.EMFBootstrapper;
import model.schemas.User;


/**
 *
 * @author joses
 */
public class UserCRUD {
    
    public void createUser(User user) throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        
        transaction.begin();
        manager.persist(user);
        transaction.commit();
        System.out.printf("se ha añadido con exito");
       
    }
    public void updateUser(User user, String newPass) throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        
        transaction.begin();
        clone(user, newPass);
        manager.merge(user);
        transaction.commit();
        System.out.printf("se ha actualizado con exito");
       
    }
    
    private void clone(User user,String newPass){
        user.setPassword(newPass);
    }

    public User getUser(String email) throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
       
        user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();

        return user;
    }
    //Log.getQuestion(this)
    public User getQuestionQuery(String email) throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        
        user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
   

        return user;
    }
    
     public User getAnswer(String answer) throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        
        user = (User) manager.createQuery("from User u where u.Answer='" + answer + "'").getSingleResult();
        

        return user;
        
    }
    
    public void deleteUser(String email) throws PersistenceException{
        String delims = "[,]";
        String[] tokens = email.split(delims);


        for(int i = 0; i < tokens.length; i++){
            User user = getUser(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();
          
            transaction.begin();
            manager.remove(user);
            transaction.commit();
            System.out.printf("se ha eliminado con exito");
           
        }
    }

}
