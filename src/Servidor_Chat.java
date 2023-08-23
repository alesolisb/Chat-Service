import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor_Chat implements Runnable{

    Servidor_Chat(){
        Thread hilo_servidor = new Thread(this);
        hilo_servidor.start();
    }
    @Override
    public void run() {
        try {
            ServerSocket sServerSocket= new ServerSocket(9999);
            Socket s = sServerSocket.accept();
            ObjectInputStream datos_entrada = new ObjectInputStream(s.getInputStream());

            Paquete datos_e = (Paquete) datos_entrada.readObject();

            System.out.println(String.format("\n SERVER: [%s - %s]: " + datos_e.getMensaje(), datos_e.getIp(),datos_e.getNick()));

            Socket reenviar = new Socket(datos_e.getIp(), 9090);

            ObjectOutputStream datos_salida = new ObjectOutputStream(reenviar.getOutputStream());

            datos_salida.writeObject(datos_e);
            reenviar.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    
}
