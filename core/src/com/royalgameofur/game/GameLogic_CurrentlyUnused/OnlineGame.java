package com.royalgameofur.game.GameLogic_CurrentlyUnused;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class OnlineGame {
    private static Random dice;
    private static ServerSocket host;
    private static Socket hostsConnection;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    public static NewRunGame currentGame;
    private static String hostIP;
    private static Socket joinConnection;

    private Scanner reader = new Scanner(System.in);

    public static void hostGameOnline(){
        currentGame = new NewRunGame(1);
        try{
            host = new ServerSocket(4444, 100);

            while(true){
                //RunGame.runOnline();
                waitForOpponent();
                getOpponentsStream();
                hostProcessConnection();
            }

        }
        catch(IOException serverStreamProblem){
            System.out.println("Connection broke: "+serverStreamProblem);
        }
    }

    public static void joinGameOnline(String serverName){
        currentGame = new NewRunGame(2);
        hostIP = serverName;

        try{
            getHostStream();
            getStreams();
            joinProcessConnection();


        }catch(IOException serverStreamProblem){
            System.out.println("Connection broke: "+serverStreamProblem);
        }

    }
    //////stuff for joining/////

    public static void getHostStream() throws IOException{
        System.out.println("Attempting to connect to host...");

        try {
            joinConnection = new Socket(InetAddress.getByName(hostIP), 4444);
        } catch (UnknownHostException error){
            System.out.println("Couldn't connect to host "+error);
        }
        System.out.println("Client: connect to: "+ joinConnection.getInetAddress().getHostName());
    }

    public static void getStreams() throws IOException{
        output = new ObjectOutputStream(joinConnection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(joinConnection.getInputStream());
    }

    public static void joinProcessConnection() throws IOException{
        System.out.println("Client: processing messages");
        String recievedMove;

        do{
            try{
                recievedMove = (String) input.readObject();
                System.out.println(recievedMove);
                int diceRoll =Integer.parseInt(Character.toString(recievedMove.charAt(0)));
                int stoneMove =Integer.parseInt(Character.toString(recievedMove.charAt(1)));
                System.out.println("diceroll: "+diceRoll);
                System.out.println("stoneMove: "+stoneMove);
                currentGame.otherPlayerMove(diceRoll, stoneMove);

                currentGame.selfNextMove();

            } catch(ClassNotFoundException error2){
                System.out.println("Problem with input");
            }

        }while(true);
    }


///////////////////////////////////////////////////////
    /////stuff for hosting///////
    public static void waitForOpponent() throws IOException{
        System.out.println("Host: 'waiting for opponent' ");
        hostsConnection = host.accept();
        System.out.println("Host: 'Opponent Found!'. Opponent: "+ hostsConnection.getInetAddress().getHostName());
    }
    public static void getOpponentsStream() throws IOException{
        output = new ObjectOutputStream(hostsConnection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(hostsConnection.getInputStream());
        System.out.println("Streams crossed");
    }

    public static void hostProcessConnection() throws IOException{
        System.out.println("Server: processing messages");
        String recievedMove;

        do{
            try{
                currentGame.selfNextMove();
                recievedMove = (String) input.readObject();
                int diceRoll =Integer.parseInt(Character.toString(recievedMove.charAt(0)));
                int stoneMove =Integer.parseInt(Character.toString(recievedMove.charAt(1)));
                currentGame.otherPlayerMove(diceRoll, stoneMove);


            } catch(ClassNotFoundException error2){
                System.out.println("Problem with input");
            }

        }while(true);

    }
    /////////////////////////////

    public static void sendData(int diceMove, int stoneMove) throws IOException{
        String move = diceMove+""+stoneMove;
        System.out.println("waiting for opponent...");
        output.writeObject(move);
        output.flush();
    }


}
