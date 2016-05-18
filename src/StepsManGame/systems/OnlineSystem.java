/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StepsManGame.systems;
import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private DataOutputStream output_s, output_r;
    private DataInputStream input_s, input_r;
    
    private Point position_s, position_r;
    
    public OnlineSystem(){
        status = false;
        hosting = false;
    }
    
    public void createServer() throws IOException{
        status = true;
        hosting = true;
        
        System.out.println("Criando Servidor.");
        ss = new ServerSocket(3323);
        System.out.println("Aguardando conexão.");
        socket_s = ss.accept();
        System.out.println("Conexão estabelecida!");
        output_s = new DataOutputStream(socket_s.getOutputStream());
        input_s = new DataInputStream(socket_s.getInputStream());
    }
    
    public void requestServer(){
        String xy;
        int x = 0, y = 0;
        Point point = null;
        //receber do servidor sua jogada
        xy = input_r.toString();
        //setar o point do player1
        switch (xy.charAt(0)){
            case '0':
                x=0;
                break;
            case '1':
                x=1;
                break;
            case '2':
                x=2;
                break;
            case '3':
                x=3;
                break;
            case '4':
                x=4;
                break;
        }
        switch (xy.charAt(1)){
            case '0':
                y=0;
                break;
            case '1':
                y=1;
                break;
            case '2':
                y=2;
                break;
            case '3':
                y=3;
                break;
            case '4':
                y=4;
                break;
            case '5':
                y=5;
                break;
            case '6':
                y=6;
                break;
            case '7':
                y=7;
                break;
            case '8':
                y=8;
                break;
        }
        point.setLocation(x,y);
        gameSystem.setPoint(1, point);
        //efetuar a jogada com plantSeed
        gameSystem.plantSeed(1);
        
    }
    
    public void respondClient(Point position_s){
        int x, y;
        String response_x = "", response_y = "", response = "";
        x = (int) position_s.getX();
        y = (int) position_s.getY();
        
        response_x = Integer.toString(x);
        response_y = Integer.toString(y);
        response = response_x.concat(response_y);
        
        try {
            output_s.writeChars(response);
        } catch (IOException ex) {
            Logger.getLogger(OnlineSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createClient() throws IOException{
        status = true;
        hosting = false;
        
        socket_r = new Socket("localhost", 3323);
        output_r = new DataOutputStream(socket_r.getOutputStream());
        input_r = new DataInputStream(socket_r.getInputStream());
        
    }
    
    public void requestClient(){
        String xy;
        int x = 0, y = 0;
        Point point = null;
        //receber do cliente sua jogada
        xy = input_s.toString();
        //setar o point do player2
        switch (xy.charAt(0)){
            case '0':
                x=0;
                break;
            case '1':
                x=1;
                break;
            case '2':
                x=2;
                break;
            case '3':
                x=3;
                break;
            case '4':
                x=4;
                break;
        }
        switch (xy.charAt(1)){
            case '0':
                y=0;
                break;
            case '1':
                y=1;
                break;
            case '2':
                y=2;
                break;
            case '3':
                y=3;
                break;
            case '4':
                y=4;
                break;
            case '5':
                y=5;
                break;
            case '6':
                y=6;
                break;
            case '7':
                y=7;
                break;
            case '8':
                y=8;
                break;
        }
        point.setLocation(x,y);
        gameSystem.setPoint(2, point);
        //efetuar a jogada com plantSeed
        gameSystem.plantSeed(2);
    }
    
    public void respondServer(Point position_r){
        int x, y;
        String response_x = "", response_y = "", response = "";
        x = (int) position_r.getX();
        y = (int) position_r.getY();
        
        response_x = Integer.toString(x);
        response_y = Integer.toString(y);
        response = response_x.concat(response_y);
        
        try {
            output_r.writeChars(response);
        } catch (IOException ex) {
            Logger.getLogger(OnlineSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
