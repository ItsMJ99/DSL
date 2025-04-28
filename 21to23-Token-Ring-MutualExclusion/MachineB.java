import java.io.*;    
import java.net.*;   

public class MachineB {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);  // (If using real 2 machines, replace "localhost" with MachineA's IP)
       
        // Setup input to read messages from MachineA
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Setup output to send messages to MachineA
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // MachineB starts with the token (first chance to enter Critical Section)
        System.out.println("Machine B: Starting with token, entering Critical Section...");
        Thread.sleep(2000); // Wait for 2 seconds to simulate Critical Section work

        System.out.println("Machine B: Exiting Critical Section, sending token...");
        out.println("TOKEN"); // Send token to MachineA

        while (true) {
            // Read token from MachineA
            String token = in.readLine();

            // If token is received
            if ("TOKEN".equals(token)) {
                System.out.println("Machine B: Got token, entering Critical Section...");
                Thread.sleep(2000); 
                System.out.println("Machine B: Exiting Critical Section, sending token...");
                out.println("TOKEN");
            }
        }
    }
}
