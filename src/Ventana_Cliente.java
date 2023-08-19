import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Ventana_Cliente extends JFrame {
    int WIDTH=1000;
    int HEIGHT=1000;
    JButton enviar;
    JTextField mensaje;
    JPanel panel_inferior;
    JTextArea chat;

    Ventana_Cliente(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("ChatApp");
        this.setResizable(false);
        //this.setSize(WIDTH, HEIGHT);
        
        

        enviar = new JButton("Enviar", null);

        mensaje = new JTextField();

        mensaje.setPreferredSize(new Dimension(600, 40));
        mensaje.setBackground(Color.BLUE);

        chat= new JTextArea();
        chat.setBackground(Color.DARK_GRAY);
        chat.setForeground(Color.WHITE);
        chat.setPreferredSize(new Dimension(750, 500));

        panel_inferior = new JPanel();
        panel_inferior.setBackground(Color.LIGHT_GRAY);
        panel_inferior.setPreferredSize(new Dimension(750, 50));

        this.add(chat, BorderLayout.NORTH);
        this.add(panel_inferior, BorderLayout.SOUTH);
        panel_inferior.add(mensaje);
        panel_inferior.add(enviar); 
        this.pack();
        this.setVisible(true);




    }
}
