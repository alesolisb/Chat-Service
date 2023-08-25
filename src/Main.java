import javax.swing.JOptionPane;

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
