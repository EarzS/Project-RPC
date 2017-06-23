/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.rpc;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import programarcp.controller.ServerController;

/**
 * Representa el servidor RPC que recibira peticiones para ejecutar metodos de
 * forma remota. Los metodos a ejecutar estan en el controlador.
 * 
 * @see ServerController
 * @see https://ws.apache.org/xmlrpc/server.html
 * @see https://www.tutorialspoint.com/xml-rpc/xml_rpc_examples.htm
 * @author Hector
 */
public class RPCServer {

    public static final int PORT = 80;
    
    private WebServer server;
    
    public RPCServer() {
        try {
            server = new WebServer(PORT);
            
            XmlRpcServer xmlRpcServer = server.getXmlRpcServer();
            
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("server", ServerController.class);
            
            xmlRpcServer.setHandlerMapping(phm);
            
            XmlRpcServerConfigImpl serverConfig =
                    (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
            serverConfig.setEnabledForExtensions(true);
            serverConfig.setContentLengthOptional(false);
            
            server.start();
            
        } catch (Exception ex) {
            System.out.println("[RPC] No se pudo iniciar el servidor: " + ex);
        }
    }
}
