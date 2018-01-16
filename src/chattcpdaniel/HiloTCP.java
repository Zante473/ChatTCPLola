/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattcpdaniel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class HiloTCP extends Thread {

    Socket socketCliente;
    DataInputStream flujoDeEntrada;
    ArrayList<DataOutputStream> flujosSalida;

    public HiloTCP(Socket socketCliente, DataInputStream flujoDeEntrada, ArrayList<DataOutputStream> flujosSalida) {

        this.socketCliente = socketCliente;
        this.flujoDeEntrada = flujoDeEntrada;
        this.flujosSalida = flujosSalida;

    }

    @Override
    public void run() {

        while (true) {
            
            try {
                
                flujoDeEntrada = new DataInputStream(socketCliente.getInputStream());
                String mensaje = flujoDeEntrada.readUTF();
                redistribuirMensaje(mensaje);
                System.out.println(mensaje);
            } catch (IOException ex) {
            }

        }

    }
    
    public void redistribuirMensaje(String mensaje){
        
        for (int i = 0; i < flujosSalida.size(); i++) {
            
            try {
                flujosSalida.get(i).writeUTF(mensaje);
            } catch (IOException ex) {
            }
            
        }
        
    }
}
