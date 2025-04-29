import java.net.MalformedURLException; // For handling bad URL formats
import java.rmi.Naming; // For binding the remote object to a name
import java.rmi.RemoteException; // For handling RMI-related errors
import java.rmi.registry.LocateRegistry; // For creating RMI registry

public class CalculatorServer {
    
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        // Check if user has provided both host and port as command-line arguments
        if (args.length < 2) {
            System.out.println("Usage: java CalculatorServer <host> <port>");
            System.exit(1); // Exit if not enough arguments
        }

        try {
            // Read host and port from command-line arguments
            String registryHost = args[0];
            int registryPort = Integer.parseInt(args[1]);

            // Set the server's hostname (important for remote clients)
            System.setProperty("java.rmi.server.hostname", registryHost);

            try {
                // Try to create an RMI registry at the given port
                LocateRegistry.createRegistry(registryPort);
                System.out.println("RMI Registry started at port " + registryPort);
            } catch (RemoteException e) {
                // If registry already exists, just print a message
                System.out.println("Registry already running at port " + registryPort);
            }

            // Create an instance of the CalculatorServerImpl (the actual service)
            CalculatorServerImpl calculator = new CalculatorServerImpl();
            
            // Create the RMI URL where the service will be bound
            String rmiURL = "rmi://" + registryHost + ":" + registryPort + "/CalculatorService";

            // Bind the calculator object to the RMI URL so clients can find it
            Naming.rebind(rmiURL, calculator);

            // Print success message
            System.out.println("Calculator Server is ready at: " + rmiURL);

        } catch (Exception e) {
            // If any error occurs, print the error message and stack trace
            System.out.println("Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
