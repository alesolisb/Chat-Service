import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Esta clase crea un servidor para el servicvio de chat
 * @author Sergio Salazar
 * @version 25/08/2023
 */
public class Servidor_Chat implements Runnable{
    Servidor_Chat(){
        Thread hilo_servidor = new Thread(this);
        hilo_servidor.start();
    }
    /**
     * Perimte que el servidor esté siempre a la escucha de nuevos mensajes y los reenvia inmediatamente
     * @author Sergio Salazar
     * */@Override
    public void run() {
        try {
            ServerSocket sServerSocket= new ServerSocket(9999);
            Socket s;
            Paquete datos_e;
            ObjectInputStream datos_entrada;
            while (true){
                s = sServerSocket.accept();
                datos_entrada = new ObjectInputStream(s.getInputStream());
                datos_e = (Paquete) datos_entrada.readObject();

                Socket reenviar = new Socket(datos_e.getIp(), 9090);

                ObjectOutputStream datos_salida = new ObjectOutputStream(reenviar.getOutputStream());

                datos_salida.writeObject(datos_e);
                datos_salida.close();
                reenviar.close();
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 
}
