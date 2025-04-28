import java.io.*;   // For input and output operations
import java.net.*;  // For networking (ServerSocket and Socket)

public class MachineA {
    public static void main(String[] args) throws Exception {
        
        // Create a server that listens on port 1099
        ServerSocket server = new ServerSocket(1099);
        
        // Wait for a client (MachineB) to connect
        Socket socket = server.accept();
        
        // Setup a way to read messages from MachineB
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // Setup a way to send messages to MachineB
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        while (true) {
            
            // Read the token (message) sent by MachineB
            String token = in.readLine();
            
            // If the received message is "TOKEN"
            if ("TOKEN".equals(token)) {
                
                System.out.println("Received token");
                
                // Simulate doing some work in the Critical Section
                Thread.sleep(10000);  
                
                System.out.println("Releasing token");
                
                // Send the token back to MachineB
                out.println("TOKEN");
            }
        }
    }
}
