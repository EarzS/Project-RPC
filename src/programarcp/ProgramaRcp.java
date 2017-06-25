/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp;

import programarcp.controller.ClienteController;
import programarcp.controller.ServerController;

/**
 * Sistema de control de estudiantes realizado bajo el protocolo RCP. 
 * Consiste de un cliente que envia peticiones para administrar los estuantes.
 * Mientras que el servidor realizara los procesamientos.
 * 
 * El cliente rpc implementará la vista y manipulación de los datos y 
 * el servidor implementará el almacenamiento y los procedimientos de cálculo.
 * 
 * @author Hector
 */
public class ProgramaRcp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("usage: java ProgramaRcp <client|server>");
            return;
        }
        
        if(args[0] == "client") {
            ClienteController cliente1 = new ClienteController();
        }else if(args[0] == "server") {
            ServerController servidor = new ServerController();
        }else {
            System.out.println("usage: java ProgramaRcp <client|server>");
        }
    }
    
}
