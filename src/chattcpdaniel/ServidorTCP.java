/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattcpdaniel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author dam
 */
public class ServidorTCP implements Runnable {

    int puerto;
    ServerSocket socketServidor;
    DataInputStream flujoDeEntrada;
    DataOutputStream flujoDeSalida;
    ArrayList <DataOutputStream> flujosSalida = new ArrayList();
    Thread hiloCliente;

    public ServidorTCP(int puerto) {

        this.puerto = puerto;
        Thread hilo = new Thread(this);
        hilo.start();

    }

    //new ServerSocket (Puerto);
    @Override
    public void run() {

        try {
            socketServidor = new ServerSocket(puerto);

            while (true) {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Cliente conectado.");
                flujoDeEntrada = new DataInputStream(socketCliente.getInputStream());
                flujoDeSalida= new DataOutputStream(socketCliente.getOutputStream());
                flujosSalida.add(flujoDeSalida);
                hiloCliente = new HiloTCP(socketCliente, flujoDeEntrada, flujosSalida);
                hiloCliente.start();
                    
            }
        } catch (IOException ex) {
        }

    }
    
    public static void main(String[] args) {
        
        new ServidorTCP(5000);
        
    }

}
