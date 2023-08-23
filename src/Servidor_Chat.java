import java.io.IOException;
import java.io.ObjectInputStream;
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

            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
