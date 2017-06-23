/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programarcp.rpc;

import java.net.URL;
import java.util.ArrayList;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * Representa el cliente RPC que permitira ejecutar comandos de forma remota.
 * 
 * @see https://www.tutorialspoint.com/xml-rpc/xml_rpc_examples.htm
 * @author Hector
 */
public class RPCClient {
    
    private static final String PATH = "http://localhost/RPC2";
            
    private XmlRpcClient server;
    
    public RPCClient() {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(PATH));
            config.setEnabledForExtensions(true);
            
            server = new XmlRpcClient();
            server.setConfig(config);
        } catch (Exception ex) {
            System.out.println("[RPC] No se pudo establecer la conexion con el servidor: " + ex);
        }
    }
    
    public Object callMethod(String methodName, Object params) {
        ArrayList aParams = new ArrayList();
        
        if(params != null) {
            aParams.add(params);
        }
        
        Object result = null;
        try {
            result = server.execute(methodName, aParams);
        } catch (Exception ex) {
            System.out.println("[RPC] No se pudo ejecutar el comando: " + ex);
        }
        
        return result;
    }
}
