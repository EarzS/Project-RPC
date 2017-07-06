/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.controller;

import programarcp.model.EstudianteDao;
import programarcp.model.EstudianteVo;
import programarcp.rpc.RPCServer;
import programarcp.view.ServerView;

/**
 * Poseera la logica que conecta a la vista y el modelo del servidor.
 * 
 * @author Hector
 */
public class ServerController {
     
    private ServerView view;
    private RPCServer rpc;
    private EstudianteDao estudianteDao;
    
    public ServerController() {
        estudianteDao = EstudianteDao.singleton();
        view = ServerView.singleton();
    }
    
    public void init() {
        view.logMessage("Starting server...");
        rpc = new RPCServer();
        view.logMessage("Server started!");
    }
    
    // ======================= Metodos de acceso ===============================
    
    public boolean create(EstudianteVo estudiante) {
        view.logMessage("Request received: create.");

        boolean b = estudianteDao.createEstudiante(estudiante);
        if(b){
            view.logMessage("Student created successfully.");
        }else {
            view.warn("Couldn't create student.");
        }
        
        return b;
    }
    
    public EstudianteVo read(String cedula) {
        view.logMessage("Request received: read (single).");
        
        EstudianteVo estudiante = estudianteDao.readEstudiante(cedula);
        if(estudiante != null){
            view.logMessage("Student found successfully.");
        }else {
            view.warn("Couldn't find student.");
        }
        
        return estudiante;
    }
    
    public boolean update(EstudianteVo estudiante) {
        view.logMessage("Request received: update.");
        
        boolean b = estudianteDao.updateEstudiante(estudiante);
        if(b){
            view.logMessage("Student updated successfully.");
        }else {
            view.warn("Couldn't update student.");
        }
        
        return b;
    }
    
    public boolean delete(String cedula) {
        view.logMessage("Request received: delete.");
        
        boolean b = estudianteDao.deleteEstudiante(cedula);
        if(b){
            view.logMessage("Student deleted successfully.");
        }else {
            view.warn("Couldn't delete student.");
        }
        
        return b;
    }
    
    public float promedio(String cedula) {
        view.logMessage("Request received: avg (single).");
        
        float promedio = 
                estudianteDao.getPromedioParticular(cedula);
        if(promedio != -1) {
            view.logMessage("Average found successfully.");
        }else {
            view.warn("Couldn't get average.");
        }
        
        return promedio;
    }
    
    public float promedio() {
        view.logMessage("Request received: avg (general).");
        
        float promedio = 
                estudianteDao.getPromedioGeneral();
        if(promedio != -1) {
            view.logMessage("Average found successfully.");
        }else {
            view.warn("Couldn't get average.");
        }
        
        return promedio;
    }
    
    public float maximo() {
        view.logMessage("Request received: maximo.");
        
        float maximo = 
                estudianteDao.getMaximaNota();
        if(maximo != -1) {
            view.logMessage("Max note found successfully.");
        }else {
            view.warn("Couldn't get max note.");
        }
        
        return maximo;
    }
    
    public float minimo() {
        view.logMessage("Request received: minimo.");
        
        float minimo = 
                estudianteDao.getMinimaNota();
        if(minimo != -1) {
            view.logMessage("Min note found successfully.");
        }else {
            view.warn("Couldn't get min note.");
        }
        
        return minimo;
    }
    
    public static void main(String[] args) {
        ServerController server = new ServerController();
        server.init();
    }
}
