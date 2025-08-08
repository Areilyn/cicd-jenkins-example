package com.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.io.InputStream;
import java.io.OutputStream;

public class Calculator {

    private static final int PORT = 5000;

    public static void main(String[] args) throws IOException {

        try (ServerSocket svSoc = new ServerSocket(PORT)) {

            // while (true) {
            for (int i = 0; i < 5; i++) {

                try (Socket cliSoc = svSoc.accept()) {
                    System.out.println("Connected through port " + PORT);

                    InputStream in = cliSoc.getInputStream();
                    OutputStream out = cliSoc.getOutputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    PrintWriter writer = new PrintWriter(out, true);

                    String inst;
                    int result = 0;

                    while ((inst = reader.readLine()) != null) {

                        int[] arr = Arrays.stream(inst.split(" "))
                                        .mapToInt(Integer::parseInt)
                                        .toArray();
                        
                        String op = "";
                        switch (arr[0]) {
                            case 1:
                                op = "+";
                                result = arr[1] + arr[2];
                                break;
                            case 2:
                                op = "-";
                                result = arr[1] - arr[2];
                                break;
                            case 3:
                                op = "x";
                                result = arr[1] * arr[2];
                                break;
                            case 4:
                                op = "/";
                                result = arr[1] / arr[2];
                                break;
                            default:
                                op = "INVALID";
                                break;
                        }

                        System.out.println("Instruction received = " + arr[1] + " " + op + " " + arr[2]);

                        writer.println(result);
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client");
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting server");
        }
    }
}