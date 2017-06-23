/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representacion visual de estudiante, posee metodos de acceso, mutacion y
 * especiales para obtener el promedio de una nota.
 * 
 * @author Hector
 */
public class EstudianteVo implements Serializable{
    
    private String cedula;
    private String nombre;
    private String apellido;
    private ArrayList<Float> notas;
    
    public EstudianteVo() {
        this.notas = new ArrayList();
    }
    
    public EstudianteVo(String cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.notas = new ArrayList();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void setNotas(ArrayList<Float> notas) {
        cleanNotas();
        this.notas.addAll(notas);
    }
    
    public ArrayList<Float> getNotas() {
        return notas;
    }
    
    public void addNota(float nota) {       
        this.notas.add(nota);
    }
    
    public void modifyNota(int index, float nota) {       
        this.notas.set(index, nota);
    }
    
    public float removeNota(int index) {
        return (float) notas.remove(index);
    }
    
    public void cleanNotas() {
        this.notas.clear();
    }
    
    @Override
    public String toString() {
        String message = cedula + " - " + nombre + " - " + apellido + "\n";
        for(int i = 0; i < notas.size(); i++) {
            message += "Nota #" + (i + 1) + ": " + notas.get(i) + "\n";
        }
        
        return message;
    }
}
