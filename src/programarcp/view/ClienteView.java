/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.view;

import java.util.ArrayList;
import java.util.Scanner;
import programarcp.controller.ClienteController;
import programarcp.model.EstudianteVo;

/**
 * Vista sencilla desde linea de comandos.
 * 
 * @author Hector
 */
public class ClienteView extends Thread{
    
    private final ClienteController controller;
    
    private EstudianteVo estudiante;
    
    private final Scanner scanner;
    
    public ClienteView(ClienteController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
        estudiante = new EstudianteVo();
    }
    
    public String getCedula() {
        return estudiante.getCedula();
    }
    
    public String getNombre() {
        return estudiante.getNombre();
    }
    
    public String getApellido() {
        return estudiante.getApellido();
    }
    
    public ArrayList<Float> getNotas() {
        return estudiante.getNotas();
    }
    
    public final void createEstudiante() {
        
        System.out.print("Ingrese cedula: ");
        estudiante.setCedula(scanner.nextLine());
        System.out.print("Ingrese nombre: ");
        estudiante.setNombre(scanner.nextLine());
        System.out.print("Ingrese apellido: ");
        estudiante.setApellido(scanner.nextLine());
        
        estudiante.cleanNotas();
        System.out.print("Cuantas notas ingresara: ");
        int t = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < t; i++) {
            System.out.print("Ingrese nota #" + (i+1) + ": ");
            estudiante.addNota(Float.parseFloat(scanner.nextLine()));
        }
        
        if(controller.createEstudiante()) {
            System.out.println("Estudiante creado satisfactoriamente.");
        }
    }
    
    public final void readEstudiante() {
        System.out.print("Ingrese cedula: ");
        estudiante.setCedula(scanner.nextLine());
        
        estudiante = controller.readEstudiante();
        if(estudiante != null) {
            System.out.println("Estudiante encontrado satisfactoriamente: ");
            System.out.println(estudiante);
        }else {
            estudiante = new EstudianteVo();
        }
    }
    
    public final void updateEstudiante() {
        System.out.print("Ingrese cedula: ");
        estudiante.setCedula(scanner.nextLine());
        
        estudiante = controller.readEstudiante();
        if(estudiante != null) {
            System.out.println("Nombre actual: " + estudiante.getNombre());
            System.out.print("Ingrese  nuevo nombre, vacio para saltar: ");
            String nombre = scanner.nextLine();
            estudiante.setNombre(nombre.isEmpty()? estudiante.getNombre() : nombre);
            
            System.out.println("Apellido actual: " + estudiante.getApellido());
            System.out.print("Ingrese nuevo apellido, vacio para saltar: ");
            String apellido = scanner.nextLine();
            estudiante.setApellido(apellido.isEmpty()? estudiante.getApellido() : apellido);
            
            char letter;
            int index;
            float nota;
            System.out.print("Desea modificar una nota? (y/n) ");
            while((letter = scanner.nextLine().toLowerCase().charAt(0)) == 'y') {
                System.out.print("Que desea hacer?\n"
                        + "1. Agregar nota\n"
                        + "2. Modificar nota\n"
                        + "3. Remover nota\n"
                        + "Opcion: ");
                index = Integer.parseInt(scanner.nextLine());
                
                if(index == 1) {
                    System.out.print("Ingrese la nueva nota: ");
                    nota = Float.parseFloat(scanner.nextLine());
                    if(nota < 0 || nota > 10) {
                        System.out.println("Nota invalida...");
                    }
                } else if(index == 2 || index == 3) {
                    for(int i = 0; i < estudiante.getNotas().size(); i++) {
                        System.out.println("Nota #" + (i+1) + ": " + estudiante.getNotas().get(i));
                    }
                    System.out.print("Cual? ");
                    index = Integer.parseInt(scanner.nextLine());
                    
                    if(index < 1 || index > estudiante.getNotas().size()){
                        index--;
                        System.out.print("Ingrese la nueva nota: ");
                        nota = Float.parseFloat(scanner.nextLine());
                        if(nota < 0 || nota > 10) {
                            System.out.println("Nota invalida...");
                            continue;
                        }else {
                            estudiante.getNotas().set(index, nota);
                        }
                    }else {
                        System.out.println("Posicion invalida...");
                    }
                }
                
                System.out.print("Desea modificar una nota? (y/n) ");
            }
            

            
            if(controller.updateEstudiante()) {
                System.out.println("Estudiante modificado satisfactoriamente.");
            }
        }else {
            estudiante = new EstudianteVo();
        }
    }
    
    public final void deleteEstudiante() {
        System.out.print("Ingrese cedula: ");
        estudiante.setCedula(scanner.nextLine());
        
        if(controller.deleteEstudiante()) {
            System.out.println("Estudiante eliminado satisfactoriamente.");
        }
    }
    
    public final void promedioParticular() {
        System.out.print("Ingrese cedula: ");
        estudiante.setCedula(scanner.nextLine());
        
        float promedio = controller.promedioParticular();
        if(promedio != -1) {
            System.out.println("Promedio de " + estudiante.getCedula() + ": "
                    + promedio);
        }
    }
    
    public final void promedioGeneral() {
        float promedio = controller.promedioGeneral();
        if(promedio != -1){
            System.out.println("Promedio general: " + promedio);
        }
    }
    
    public final void notaMaxima() {
        float maximo = controller.notaMaxima();
        if(maximo != -1){
            System.out.println("Nota maxima: " + maximo);
        }
    }
    
    public final void notaMinima() {
        float minimo = controller.notaMinima();
        if(minimo != -1){
            System.out.println("Nota minima: " + minimo);
        }
    }
    
    public final void warnUser(String message) {
        System.out.println("[Warning] " + message);
    }
    
    @Override
    public void run() {
        boolean running = true;
        while(running) {
            System.out.println("----Elija Opcion----\n"
                             + "1. Agregar estudiante\n"
                             + "2. Buscar estudiante\n"
                             + "3. Modificar estudiante\n"
                             + "4. Eliminar estudiante\n"
                             + "5. Obtener promedio particular\n"
                             + "6. Obtener promedio general\n"
                             + "7. Obtener maxima nota\n"
                             + "8. Obtener minima nota\n"
                             + "0. Salir\n");
            int option = Integer.parseInt(scanner.nextLine());
            
            switch(option) {
                case 0:
                    running = false;
                break;
                case 1:
                    createEstudiante();
                break;
                case 2:
                    readEstudiante();
                break;
                case 3:
                    updateEstudiante();
                break;
                case 4:
                    deleteEstudiante();
                break;
                case 5:
                    promedioParticular();
                break;
                case 6:
                    promedioGeneral();
                break;
                case 7:
                    notaMaxima();
                break;
                case 8:
                    notaMinima();
                break;
                default:
                    warnUser("Opcion invalida.");
                break;
            }
        }
    }
}
