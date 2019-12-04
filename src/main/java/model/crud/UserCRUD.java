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
    
    public void createUser(User user){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.printf("se ha añadido con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }
    public void updateUser(User user, String newPass){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            clone(user, newPass);
            manager.merge(user);
            transaction.commit();
            System.out.printf("se ha actualizado con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }
    
    private void clone(User user,String newPass){
        user.setPassword(newPass);
    }

    public User getUser(String email){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        try {
            user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return user;
    }
    //Log.getQuestion(this)
    public User getQuestionQuery(String email){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        try {
            user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return user;
    }
    
     public User getAnswer(String answer){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        try {
            user = (User) manager.createQuery("from User u where u.Answer='" + answer + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return user;
        
    }
    
    public void deleteUser(String email){
        String delims = "[,]";
        String[] tokens = email.split(delims);


        for(int i = 0; i < tokens.length; i++){
            User user = getUser(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.remove(user);
                transaction.commit();
                System.out.printf("se ha eliminado con exito");
            }
            catch(PersistenceException e) {
                transaction.rollback();
                throw e;
            }
            finally {
                manager.close();
            }
        }
    }

}
