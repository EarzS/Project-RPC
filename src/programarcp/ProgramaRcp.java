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
        // 1 Servidor y 3 clientes conectados simultaneamente.
        ServerController servidor = new ServerController();
        
        ClienteController cliente1 = new ClienteController();
        /*ClienteController cliente2 = new ClienteController();
        ClienteController cliente3 = new ClienteController();*/
    }
    
}
