/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.view;

import programarcp.model.EstudianteVo;

/**
 *
 * @author Hector
 */
public class ServerView {
    
    private static ServerView instance;
    
    public static ServerView singleton() {
        if(instance == null) {
            instance = new ServerView();
        }
        
        return instance;
    }
    
    private EstudianteVo estudiante;
    
    private ServerView() {
        estudiante = new EstudianteVo();
        logMessage("Instanciando vista");
    }
    
    public void setEstudiante(EstudianteVo e) {
        this.estudiante = e;
    }
    
    public EstudianteVo getEstudiante() {
        return estudiante;
    }
    
    public void logMessage(String message) {
        System.out.println("[Server] " + message);
    }
    
    public void warn(String message) {
        System.out.println("[Warning] " + message);
    }
}
