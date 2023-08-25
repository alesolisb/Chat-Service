import javax.swing.JOptionPane;
/**
* Clase principal, perimete escoger si se desea abrir un servidor o un cliente
 * @version 25/08/2023
* @param args
 * @author Alejandro Solís*/
public class Main {
    
    public static void main(String[] args){
        String[] resp = {"Cliente","Servidor"};
        
        int ans = JOptionPane.showOptionDialog(null,
            "¿Desea abrir un cliente o un servidor?",
            "",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            resp,
            0);
            System.out.println(ans);
        if (ans==0){new Cliente_Chat();}
        else if (ans==1){new Servidor_Chat();}
    }  
}
