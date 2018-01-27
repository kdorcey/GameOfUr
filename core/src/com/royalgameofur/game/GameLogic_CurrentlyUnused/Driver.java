package com.royalgameofur.game.GameLogic_CurrentlyUnused;

import java.util.Scanner;

public class Driver {
    public static void main (String args[]){
        Scanner reader = new Scanner(System.in);
        int onlineCheck;
        System.out.println("1- online, 2- Local");
        onlineCheck = Integer.parseInt(reader.nextLine());

        if(onlineCheck ==1){
            System.out.println("1- host game, 2- join game");
            int hostCheck = Integer.parseInt(reader.nextLine());
            if(hostCheck ==1){
                OnlineGame.hostGameOnline();
            }
            else if (hostCheck ==2){
                System.out.println("IP to connect to: ");
                String ip = reader.nextLine();
                OnlineGame.joinGameOnline(ip);
            }
        }
        else if(onlineCheck ==2){
            RunGame.runLocally();
        }


    }
}
