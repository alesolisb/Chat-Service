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

public class Ventana_Cliente extends JFrame implements ActionListener{
    int WIDTH=1280;
    int HEIGHT=720;
    JPanel nickPanel;
    JPanel chatPanel;
    JButton enviar;
    JTextField mensaje;
    JPanel panelInferior;
    JTextArea chat;
    JLabel nickLabel;
    JTextField nickTextField;
    JButton nickButton;
    public String nick;


    Ventana_Cliente(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ChatApp");
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);

        nickPanel = new JPanel(null);
        nickPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        
        nickLabel= new JLabel("¿Cuál es tu nombre?");
        nickLabel.setBounds((WIDTH/2)-100, (HEIGHT/2) - 100, 200, 30);

        nickButton = new JButton("Confirmar");
        nickButton.setBounds((WIDTH/2)+100, (HEIGHT/2), 100, 30);
        nickButton.addActionListener(this);

        nickTextField = new JTextField();
        nickTextField.setBounds((WIDTH/2)-200, (HEIGHT/2), 300, 30);

        this.add(nickPanel);
        nickPanel.add(nickLabel);
        nickPanel.add(nickTextField);
        nickPanel.add(nickButton);
        
        this.pack();
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==nickButton){
            if (!nickTextField.getText().equals("")){
                ConfirmarNick();
            }
            else{
                System.out.println("Ingrese un nombre");
            }
        }
    }

    public void ConfirmarNick(){
        

        chatPanel= new JPanel(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().removeAll();
        this.getContentPane().add(chatPanel);
        this.revalidate();
        this.pack();
    
        System.out.println(nickTextField.getText());

        nick = nickTextField.getText();
        enviar = new JButton("Enviar", null);

        mensaje = new JTextField();
        mensaje.setPreferredSize(new Dimension(WIDTH/2, 40));

        nickLabel = new JLabel(nick);
        nickLabel.setPreferredSize(new Dimension(100, 40));


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

    }
}
