import java.io.Serializable;

public class Paquete implements Serializable{
    private String ip, mensaje, nick;
    //private int port;
    public String getIp(){
        return ip;
    }
    public void setIp(String ip){
        this.ip=ip;
    }
    public String getMensaje(){
        return mensaje;
    }
    public void setMensaje(String mensaje){
        this.mensaje=mensaje;
    }
    public String getNick(){
        return nick;
    }
    public void setNick(String nick){
        this.nick=nick;
    }
    /*
    public int getPort(){
        return port;
    }
    public void setPort(int port){
        this.port= port;
    }*/
}
