import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Esta clase construye una ventana para un cliente de la aplicación de chat
 * @author Alejandro Solís
 * 
 */
public class Cliente_Chat extends JFrame implements ActionListener, Runnable{
    int WIDTH=900;
    int HEIGHT=720;
    JPanel nickPanel;
    JPanel chatPanel;
    JButton enviar;
    JTextField mensaje;
    JPanel panelInferior;
    JTextArea chat;
    JLabel nickLabel;
    JLabel portLabel;
    JLabel destLabel;
    JTextField nickTextField;
    JTextField portTextField;
    JTextField destTextField;
    JButton nickButton;
    public String nick;
    //private String ip;
    int port;
    int destport;
    Socket cSocket;
    /**
     * Constructor inicial para la ventana, GUI que guarda el nombre del usuario
     */
    Cliente_Chat(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ChatApp");
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);

        /*InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            ip = (localHost.getHostAddress()); 
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/

        nickPanel = new JPanel(null);
        nickPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        nickLabel= new JLabel("¿Cuál es tu nombre?");
        nickLabel.setBounds((WIDTH/2)-60, (HEIGHT/2) - 100, 120, 30);

        nickTextField = new JTextField();
        nickTextField.setBounds((WIDTH/2)-150, (HEIGHT/2)-50, 300, 30);

        nickButton = new JButton("Confirmar");
        nickButton.setBounds((WIDTH/2)-50, (HEIGHT/2)+100, 100, 30);
        nickButton.addActionListener(this);

        this.add(nickPanel);
        nickPanel.add(nickLabel);
        nickPanel.add(nickTextField);
        nickPanel.add(nickButton);

        this.pack();
        this.setVisible(true);

        Thread hilo_cliente = new Thread(this);
        hilo_cliente.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==nickButton){
            if (!nickTextField.getText().equals("")){
                ConfirmarNick();
            }
        }

        if (e.getSource()==enviar){
            if (!mensaje.getText().equals("")){
                Paquete datos_s = new Paquete();
                try {
                    cSocket = new Socket("192.168.1.72",9999);
                    datos_s.setMensaje(mensaje.getText());
                    datos_s.setIp(destTextField.getText());
                    datos_s.setNick(nick);             
                    ObjectOutputStream datos_salida = new ObjectOutputStream(cSocket.getOutputStream());
                    datos_salida.writeObject(datos_s);
                    cSocket.close();
                    
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                mensaje.setText("");
                chat.append("\n"+ datos_s.getMensaje());
            }
        }
    }
    /**
     * Al confirmar el nombre de usuario despliega la interfaz de chat
     * @author Alejandro Solís
     */
    public void ConfirmarNick(){

        chatPanel= new JPanel(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().removeAll();
        this.getContentPane().add(chatPanel);
        this.revalidate();
        this.pack();

        nick = nickTextField.getText();

        enviar = new JButton("Enviar");
        enviar.addActionListener(this);

        mensaje = new JTextField();
        mensaje.setPreferredSize(new Dimension(WIDTH/2, 40));

        nickLabel = new JLabel(nick+": "+String.valueOf(port));
        nickLabel.setPreferredSize(new Dimension(100, 40));

        destLabel = new JLabel("IP del destinatario: ");
        destLabel.setPreferredSize(new Dimension(135, 40));

        destTextField = new JTextField();
        destTextField.setPreferredSize(new Dimension(100, 40));

        chat= new JTextArea();
        chat.setBackground(Color.DARK_GRAY);
        chat.setForeground(Color.WHITE);
        chat.setPreferredSize(new Dimension(WIDTH, HEIGHT-90));

        panelInferior = new JPanel();
        panelInferior.setBackground(Color.LIGHT_GRAY);
        panelInferior.setPreferredSize(new Dimension(WIDTH, 50));

        chatPanel.add(chat, BorderLayout.NORTH);
        chatPanel.add(panelInferior, BorderLayout.SOUTH);
        
        panelInferior.add(nickLabel);
        panelInferior.add(mensaje);
        panelInferior.add(enviar);
        panelInferior.add(destLabel);
        panelInferior.add(destTextField);

    }
    @Override
    /**
     * Crea el socket server para que el cliente esté siempre a la escucha de nuevos mensarjes que provienen del servidor y los despliega en el chat
     * @author Sergio Salazar
     * */
    public void run() {
        try {
            ServerSocket cServerSocket = new ServerSocket(9090);
            Socket c;
            Paquete datos_e;
            
            while (true){

                c=cServerSocket.accept();
                ObjectInputStream datos_entrada = new ObjectInputStream(c.getInputStream());
                datos_e= (Paquete) datos_entrada.readObject();
                chat.append(String.format("\n\t [%s]: " + datos_e.getMensaje()+"\n",datos_e.getNick()));
                c.close();
                datos_entrada.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }
}
