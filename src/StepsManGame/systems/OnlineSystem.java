/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;
import java.io.*;
import java.net.*;

/**
 *
 * @author Lucas
 */
public class OnlineSystem {
    
    private GameSystem gameSystem;
    private boolean status;
    private boolean hosting;
    
    private ServerSocket ss;
    private Socket socket_s, socket_r;
    private OutputStream output_s, output_r;
    private InputStream input_s, input_r;
    private BufferedReader in_s, in_r;
    private PrintStream out_s, out_r;
    
    public OnlineSystem(){
        status = false;
        hosting = false;
    }
    
    public void createServer() throws IOException{
        status = true;
        hosting = true;
        
        ss = new ServerSocket(3323);
        socket_s = ss.accept();
        output_s = socket_s.getOutputStream();
        input_s = socket_s.getInputStream();
        in_s = new BufferedReader(new InputStreamReader(input_s));
        out_s = new PrintStream(output_s);
    }
    
    public void requestServer(){
        
    }
    
    public void createClient() throws IOException{
        status = true;
        hosting = false;
        
        socket_r = new Socket("localhost", 3323);
        output_r = socket_r.getOutputStream();
        input_r = socket_r.getInputStream();
        in_r = new BufferedReader(new InputStreamReader(input_r));
        out_r = new PrintStream(output_r);
        
    }
    
    public void requestClient(){
        
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public boolean isHost(){
        return hosting;
    }
    
    public void initialize(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }
}
