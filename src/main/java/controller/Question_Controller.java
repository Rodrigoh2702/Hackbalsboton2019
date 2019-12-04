/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.Question;

/**
 *
 * @author Rodrigo
 */
public class Question_Controller {
    
    public Question_Controller(Question view){
        view.setQuestion("ask");
        view.setVisible(true);
    }
}
