/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.controller;

import java.util.regex.Pattern;
import programarcp.model.EstudianteVo;
import programarcp.rpc.RPCClient;
import programarcp.view.ClienteView;

/**
 * Poseera la logica que conecta a la vista y el modelo del cliente.
 * 
 * @author Hector
 */
public class ClienteController {
    
    private final ClienteView view;
    private final RPCClient rpc;
    
    public ClienteController() {
        rpc = new RPCClient();
        view = new ClienteView(this);
        view.start();
    }
    
    public boolean createEstudiante() {
        
        if(!validateCedula()) {
            view.warnUser("Cedula invalida.");
            return false;
        }else if (!validateNota()) {
            view.warnUser("Nota debe estar entre 0 y 10.");
            return false;
        }
        
        EstudianteVo estudiante = new EstudianteVo(view.getCedula(),
                                                   view.getNombre(),
                                                   view.getApellido());
        estudiante.setNotas(view.getNotas());
        
        boolean b = (boolean) rpc.callMethod("server.create", estudiante);
        if(!b) {
            view.warnUser("No se pudo crear el estudiante.");
            return false;
        }
        
        return true;
    }
    
    public EstudianteVo readEstudiante() {
        if(!validateCedula()) {
            view.warnUser("Cedula invalida.");
            return null;
        }
        
        EstudianteVo estudiante = (EstudianteVo) rpc.callMethod("server.read", view.getCedula());
        if(estudiante == null) {
            view.warnUser("No se pudo encontrar el estudiante.");
        }
        
        return estudiante;
    }
    
    public boolean updateEstudiante() {
        if (!validateNota()) {
            view.warnUser("Nota debe estar entre 0 y 10.");
            return false;
        }
        
        EstudianteVo estudiante = new EstudianteVo(view.getCedula(),
                                                   view.getNombre(),
                                                   view.getApellido());
        estudiante.setNotas(view.getNotas());
        
        boolean b = (boolean) rpc.callMethod("server.update", estudiante);
        if(!b) {
            view.warnUser("No se pudo actualizar el estudiante.");
            return false;
        }
        
        
        return true;
    }
    
    public boolean deleteEstudiante() {
        if(!validateCedula()) {
            view.warnUser("Cedula invalida.");
            return false;
        }
        
        if(!(boolean) rpc.callMethod("server.delete", view.getCedula())) {
            view.warnUser("No se pudo eliminar el estudiante.");
            return false;
        }
        
        return true;
    }
    
    public float promedioParticular() {
        if(!validateCedula()) {
            view.warnUser("Cedula invalida.");
            return -1;
        }
        
        float promedio = (float) rpc.callMethod("server.promedio", view.getCedula());
        if(promedio == -1) {
            view.warnUser("No se pudo obtener el promedio particular.");
        }
        
        return promedio;
    }
    
    public float promedioGeneral() {
        float promedio = (float) rpc.callMethod("server.promedio", null);
        if(promedio == -1) {
            view.warnUser("No se pudo obtener el promedio general.");
        }
        
        return promedio;
    }
    
    public float notaMaxima() {
        float maximo = (float) rpc.callMethod("server.maximo", null);
        if(maximo == -1) {
            view.warnUser("No se pudo obtener la nota maxima.");
        }
        
        return maximo;
    }
    
    public float notaMinima() {
        float minimo = (float) rpc.callMethod("server.minimo", null);
        if(minimo == -1) {
            view.warnUser("No se pudo obtener la nota minima.");
        }
        
        return minimo;
    }
    
    private boolean validateCedula() {
        return Pattern.matches("[0-9]{1,2}\\.[0-9]{3}\\.[0-9]{3}", view.getCedula());
    }
    
    private boolean validateNota() {
        for(float nota : view.getNotas()) {
            if(nota < 0 || nota > 10) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        ClienteController cliente = new ClienteController();
    }
}
