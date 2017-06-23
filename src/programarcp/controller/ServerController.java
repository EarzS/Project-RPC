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
        view.logMessage("Iniciando servidor...");
        rpc = new RPCServer();
        view.logMessage("Servidor iniciado!");
    }
    
    // ======================= Metodos de acceso ===============================
    
    public boolean create(EstudianteVo estudiante) {
        view.logMessage("Peticion recibida: create.");

        boolean b = estudianteDao.createEstudiante(estudiante);
        if(b){
            view.logMessage("Estudiante creado exitosamente.");
        }else {
            view.warn("No se pudo crear el estudiante.");
        }
        
        return b;
    }
    
    public EstudianteVo read(String cedula) {
        view.logMessage("Peticion recibida: read (singular).");
        
        EstudianteVo estudiante = estudianteDao.readEstudiante(cedula);
        if(estudiante != null){
            view.logMessage("Estudiante encontrado exitosamente.");
        }else {
            view.warn("No se pudo encontrar el estudiante.");
        }
        
        return estudiante;
    }
    
    public boolean update(EstudianteVo estudiante) {
        view.logMessage("Peticion recibida: update.");
        
        boolean b = estudianteDao.updateEstudiante(estudiante);
        if(b){
            view.logMessage("Estudiante actualizado exitosamente.");
        }else {
            view.warn("No se pudo crear el estudiante.");
        }
        
        return b;
    }
    
    public boolean delete(String cedula) {
        view.logMessage("Peticion recibida: delete.");
        
        boolean b = estudianteDao.deleteEstudiante(cedula);
        if(b){
            view.logMessage("Estudiante eliminado exitosamente.");
        }else {
            view.warn("No se pudo eliminar el estudiante.");
        }
        
        return b;
    }
    
    public float promedio(String cedula) {
        view.logMessage("Peticion recibida: promedio (estudiante especifico).");
        
        float promedio = 
                estudianteDao.getPromedioParticular(cedula);
        if(promedio != -1) {
            view.logMessage("Promedio de estudiante obtenido exitosamente.");
        }else {
            view.warn("No se pudo obtener el promedio del estudiante.");
        }
        
        return promedio;
    }
    
    public float promedio() {
        view.logMessage("Peticion recibida: promedio (todos los estudiantes).");
        
        float promedio = 
                estudianteDao.getPromedioGeneral();
        if(promedio != -1) {
            view.logMessage("Promedio general obtenido exitosamente.");
        }else {
            view.warn("No se pudo obtener el promedio general.");
        }
        
        return promedio;
    }
    
    public float maximo() {
        view.logMessage("Peticion recibida: maximo.");
        
        float maximo = 
                estudianteDao.getMaximaNota();
        if(maximo != -1) {
            view.logMessage("Maxima nota obtenida exitosamente.");
        }else {
            view.warn("No se pudo obtener la maxima nota del grupo.");
        }
        
        return maximo;
    }
    
    public float minimo() {
        view.logMessage("Peticion recibida: minimo.");
        
        float minimo = 
                estudianteDao.getMinimaNota();
        if(minimo != -1) {
            view.logMessage("Minima nota obtenida exitosamente.");
        }else {
            view.warn("No se pudo obtener la minima nota del grupo.");
        }
        
        return minimo;
    }
    
    public static void main(String[] args) {
        ServerController server = new ServerController();
        server.init();
    }
}
