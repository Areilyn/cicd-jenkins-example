package com.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;

public class Client {

    private static final int PORT = 5000;
    private static final String ADDRESS = "calculator";

    public static void main(String[] args) throws IOException {

        try (Socket soc = new Socket(ADDRESS, PORT)) {

            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            int op, a, b;
            String msg, opString;

            while (true) {

                op = generateRandom(4);
                a = generateRandom(100);
                b = generateRandom(100);
                msg = op + " " + a + " " + b;

                switch (op) {
                    case 1:
                        opString = "+";
                        break;
                    case 2:
                        opString = "-";
                        break;
                    case 3:
                        opString = "x";
                        break;
                    case 4:
                        opString = "/";
                        break;
                    default:
                        // Never happening
                        opString = "";
                        break;
                }

                System.out.println("Instruction sent: " + a + " " + opString + " " + b);

                 try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    continue;
                }

                out.println(msg);
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    continue;
                }

                String response = in.readLine();
                System.out.println("Result = " + response);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    continue;
                }
            }
        }
    }

    static int generateRandom(int upper) {
        // Generate random number between 1 and upper
        return ((int) (Math.random() * upper)) + 1;
    }
}
