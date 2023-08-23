
public class App {
        
    public static void main(String[] args) throws Exception {
        
        Ventana_Cliente cliente = new Ventana_Cliente();
        Servidor_Chat servidor = new Servidor_Chat();

        System.out.println(cliente.getTitle());
        System.out.println(servidor.toString());

        
    }
}
