Se programo la aplicacion RPC cliente servidor en base al contenido de 
estos tutoriales:

Del cliente:
- https://www.tutorialspoint.com/xml-rpc/xml_rpc_examples.htm

Del servidor:
- https://ws.apache.org/xmlrpc/server.html
- https://www.tutorialspoint.com/xml-rpc/xml_rpc_examples.htm

Se instalo los siguientes JAR:

- XMLRPC version 3.1.3: http://www.java2s.com/Code/Jar/o/Downloadorgapachexmlrpcjar.htm
- Commons Logging: https://mvnrepository.com/artifact/commons-logging/commons-logging/1.2
- WS Commons util: https://www.java2s.com/Code/Jar/o/Downloadorgapachewscommonsutiljar.htm

Despues de implementar el proyecto. Al codificar se obtuvo un error, aparentemente 
no se puede pasar objetos como parametros sin implementarlos como Serializable.

https://stackoverflow.com/questions/18301386/can-i-pass-an-object-using-xmlrpc-in-java

Despues de implementar el objecto como serializable, el servidor inicia sin problemas
aparentes.

Para permitir el uso de objetos especiales como Float, se debe permitir las extensiones
al configurar. Hay que destacar que tanto el servidor como el cliente deben aceptar 
extensiones.

- http://apache-xml-project.6118.n7.nabble.com/unable-to-call-XML-rpc-td30710.html

Se observo que al implementar un handler, este crea multiples veces el mismo objeto,
por lo que tanto EstudianteDao como ServerView se haran singleton.