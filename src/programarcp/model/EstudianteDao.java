/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representacion de datos de acceso de estudiante, posee metodos para agregar,
 * buscar, modificar y eliminar un estudiante.
 * 
 * @author Hector
 */
public class EstudianteDao {
    
    private static EstudianteDao instance;
    
    public static EstudianteDao singleton() {
        if(instance == null) {
            instance = new EstudianteDao();
        }
       
        return instance;
    }
    
    private EstudianteDao() {
        System.out.println("[Server] Instanciando dao");
    }
    
    private final List<EstudianteVo> estudiantes = new ArrayList();
    
    public boolean createEstudiante(EstudianteVo e){
      
        if(readEstudiante(e.getCedula()) != null) {
            return false;
        }
        
        estudiantes.add(e);
        
        return true;
    }
    
    public ArrayList<EstudianteVo> readEstudiante() {
        return (ArrayList<EstudianteVo>) estudiantes;
    }
    
    public EstudianteVo readEstudiante(String cedula) {
        for(EstudianteVo e : estudiantes) {
            
            if(e.getCedula().equals(cedula)) {
                return e;
            }
        }
        
        return null;
    }
    
    public boolean updateEstudiante(EstudianteVo e) {
        
        EstudianteVo estudiante = readEstudiante(e.getCedula());
        if(estudiante == null) {
            return false;
        }
        
        estudiante.setNombre(e.getNombre());
        estudiante.setApellido(e.getApellido());
        estudiante.setNotas(e.getNotas());
        
        return true;
    }
    
    public boolean deleteEstudiante(String cedula) {
        
        EstudianteVo estudiante = readEstudiante(cedula);
        if(estudiante == null) {
            return false;
        }
        
        estudiantes.remove(estudiante);
        
        return true;
    }
    
    public float getPromedioParticular(String cedula) {
        EstudianteVo estudiante = readEstudiante(cedula);
        if(estudiante == null) {
            return -1;
        }
        
        float promedio = 0;
        
        for(float nota: estudiante.getNotas()) {
            promedio += nota;
        }
        
        return promedio /= estudiante.getNotas().size();
    }
    
    public float getPromedioGeneral() {
        float promedio = 0;
        float cantidad = 0;
        
        for(EstudianteVo e: estudiantes) {
            for(float nota : e.getNotas()) {
                promedio += nota;
                cantidad++;
            }
        }
        
        return promedio /= cantidad;
    }
    
    public float getMinimaNota() {
        // Obtengo la primera nota del primer estudiante como referencia
        float minimo = estudiantes.get(0).getNotas().get(0);
        
        for(EstudianteVo e: estudiantes) {
            for(float nota : e.getNotas()) {
                if(minimo > nota) {
                    minimo = nota;
                }
            }
        }
        
        return minimo;
    }
    
    public float getMaximaNota() {
        // Obtengo la primera nota del primer estudiante como referencia
        float maximo = estudiantes.get(0).getNotas().get(0);
        
        for(EstudianteVo e: estudiantes) {
            for(float nota : e.getNotas()) {
                if(maximo < nota) {
                    maximo = nota;
                }
            }
        }
        
        return maximo;
    }
    
}
