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
        
        System.out.print("Insert id: ");
        estudiante.setCedula(scanner.nextLine());
        System.out.print("Insert name: ");
        estudiante.setNombre(scanner.nextLine());
        System.out.print("Insert lastname: ");
        estudiante.setApellido(scanner.nextLine());
        
        estudiante.cleanNotas();
        System.out.print("How many notes will you insert?: ");
        int t = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < t; i++) {
            System.out.print("Insert note #" + (i+1) + ": ");
            estudiante.addNota(Float.parseFloat(scanner.nextLine()));
        }
        
        if(controller.createEstudiante()) {
            System.out.println("Student created successfully.");
        }
    }
    
    public final void readEstudiante() {
        System.out.print("Insert id: ");
        estudiante.setCedula(scanner.nextLine());
        
        estudiante = controller.readEstudiante();
        if(estudiante != null) {
            System.out.println("Student found successfully: ");
            System.out.println(estudiante);
        }else {
            estudiante = new EstudianteVo();
        }
    }
    
    public final void updateEstudiante() {
        System.out.print("Insert id: ");
        estudiante.setCedula(scanner.nextLine());
        
        estudiante = controller.readEstudiante();
        if(estudiante != null) {
            System.out.println("Actual name: " + estudiante.getNombre());
            System.out.print("Insert new name, empty to skip: ");
            String nombre = scanner.nextLine();
            estudiante.setNombre(nombre.isEmpty()? estudiante.getNombre() : nombre);
            
            System.out.println("Actual lastname: " + estudiante.getApellido());
            System.out.print("Insert new lastname, empty to skip: ");
            String apellido = scanner.nextLine();
            estudiante.setApellido(apellido.isEmpty()? estudiante.getApellido() : apellido);
            
            char letter;
            int index;
            float nota;
            System.out.print("Will you modify a note? (y/n) ");
            while((letter = scanner.nextLine().toLowerCase().charAt(0)) == 'y') {
                System.out.print("What will you do?\n"
                        + "1. Add note\n"
                        + "2. Update note\n"
                        + "3. Remove note\n"
                        + "Option: ");
                index = Integer.parseInt(scanner.nextLine());
                
                if(index == 1) {
                    System.out.print("Insert new note: ");
                    nota = Float.parseFloat(scanner.nextLine());
                    if(nota < 0 || nota > 10) {
                        System.out.println("Invalid note...");
                    }
                    estudiante.getNotas().add(nota);
                } else if(index == 2 || index == 3) {
                    for(int i = 0; i < estudiante.getNotas().size(); i++) {
                        System.out.println("Note #" + (i+1) + ": " + estudiante.getNotas().get(i));
                    }
                    System.out.print("Which one? ");
                    index = Integer.parseInt(scanner.nextLine());
                    
                    if(index < 1 || index > estudiante.getNotas().size()){
                        index--;
                        System.out.print("Insert new note: ");
                        nota = Float.parseFloat(scanner.nextLine());
                        if(nota < 0 || nota > 10) {
                            System.out.println("Invalid note...");
                            continue;
                        }else {
                            estudiante.getNotas().set(index, nota);
                        }
                    }else {
                        System.out.println("Invalid position...");
                    }
                }
                
                System.out.print("Will you modify a note? (y/n) ");
            }
            

            
            if(controller.updateEstudiante()) {
                System.out.println("Student updated successfully.");
            }
        }else {
            estudiante = new EstudianteVo();
        }
    }
    
    public final void deleteEstudiante() {
        System.out.print("Insert id: ");
        estudiante.setCedula(scanner.nextLine());
        
        if(controller.deleteEstudiante()) {
            System.out.println("Student deleted successfully.");
        }
    }
    
    public final void promedioParticular() {
        System.out.print("Insert id: ");
        estudiante.setCedula(scanner.nextLine());
        
        float promedio = controller.promedioParticular();
        if(promedio != -1) {
            System.out.println("Average of " + estudiante.getCedula() + ": "
                    + promedio);
        }
    }
    
    public final void promedioGeneral() {
        float promedio = controller.promedioGeneral();
        if(promedio != -1){
            System.out.println("General average: " + promedio);
        }
    }
    
    public final void notaMaxima() {
        float maximo = controller.notaMaxima();
        if(maximo != -1){
            System.out.println("Max note: " + maximo);
        }
    }
    
    public final void notaMinima() {
        float minimo = controller.notaMinima();
        if(minimo != -1){
            System.out.println("Min note: " + minimo);
        }
    }
    
    public final void warnUser(String message) {
        System.out.println("[Warning] " + message);
    }
    
    @Override
    public void run() {
        boolean running = true;
        while(running) {
            System.out.println("----Choose a option----\n"
                             + "1. Add student\n"
                             + "2. Find student\n"
                             + "3. Update student\n"
                             + "4. Delete student\n"
                             + "5. Get student average\n"
                             + "6. Get class average\n"
                             + "7. Get max note\n"
                             + "8. Get min note\n"
                             + "0. Exit\n");
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
                    warnUser("Invalid option.");
                break;
            }
        }
    }
}
