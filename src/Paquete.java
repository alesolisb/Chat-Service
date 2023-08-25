import java.io.Serializable;
/**
 * Esta clase empaqueta los datos que se envian en un mensaje (Ip del destinatario, Mensaje a enviar, Nombre de usuario del remitente)
 * @author Alejandro Solís
 * @version 25/08/2023
 */
public class Paquete implements Serializable{
    private String ip, mensaje, nick;
    /**
     * Devuelve la ip a la que se dirige el mensaje
     * @return String
     */
    public String getIp(){
        return ip;
    }
    /**
     * Asigna el valor especificado al atributo "ip"
     * @param nick. El nuevo ip de destino a asignar
     */
    public void setIp(String ip){
        this.ip=ip;
    }
    /**
     * Devuelve el mensaje que se está enviando
     * @return String
     */
    public String getMensaje(){
        return mensaje;
    }
    /**
     * Asigna el valor especificado al atributo "mensaje"
     * @param mensaje. El nuevo mensaje a asignar
     */
    public void setMensaje(String mensaje){
        this.mensaje=mensaje;
    }
    /**
     * Devuelve el nombre de usuario del remitente del mensaje
     * @return String
     */
    public String getNick(){
        return nick;
    }
    
    /**
     * Asigna el valor especificado al atributo "nick"
     * @param nick. El nuevo nick a asignar
     */
    public void setNick(String nick){
        this.nick=nick;
    }
}
