import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        if (args.length < 2) {
            System.out.println("Usage: java CalculatorServer <host> <port>");
            System.exit(1);
        }

        try {
            String registryHost = args[0];
            int registryPort = Integer.parseInt(args[1]);

            System.setProperty("java.rmi.server.hostname", registryHost);

            try {
                LocateRegistry.createRegistry(registryPort);
                System.out.println("RMI Registry started at port " + registryPort);
            } catch (RemoteException e) {
                System.out.println("Registry already running at port " + registryPort);
            }

            CalculatorServerImpl calculator = new CalculatorServerImpl();
            String rmiURL = "rmi://" + registryHost + ":" + registryPort + "/CalculatorService";

            Naming.rebind(rmiURL, calculator);

            System.out.println("Calculator Server is ready at: " + rmiURL);

        } catch (Exception e) {
            System.out.println("Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
