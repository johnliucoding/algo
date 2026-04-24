package semantic.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liuxiaodong02
 */
public class Client02 {
    static void main(String[] args) throws IOException {
        if(args.length!=1){
            System.err.println("Usage: java Client02 <IP>");
            return;
        }

        try(var socket = new Socket(args[0], 59898)) {
            try(var out = new PrintWriter(socket.getOutputStream(), true)) {
                try (var in = new Scanner(socket.getInputStream())) {
                    while (true) {
                        String line = IO.readln();  // input to send
                        if(line == null) break;     // no more to send, done
                        out.println(line);          // send to server
                        IO.println(in.nextLine());  // echo response from server
                    }
                }
            }
        }
    }
}
