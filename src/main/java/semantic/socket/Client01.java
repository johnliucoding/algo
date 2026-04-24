package semantic.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liuxiaodong02
 */
public class Client01 {
    static void main(String[] args) throws IOException {
        if(args.length!=1){
            System.err.println("Usage: java Client01 [IP]");
            return;
        }
        try(var socket = new Socket(args[0], 59090)) {
            try (var in = new Scanner(socket.getInputStream())) {
                IO.println("Server response: " + in.nextLine());
            }
        }
    }
}

// java DateClient 127.0.0.1

