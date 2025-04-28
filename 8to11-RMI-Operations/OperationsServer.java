import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;

class OperationsServer {
    public static void main(String []args) throws RemoteException, MalformedURLException{
        if(args.length < 2){
            System.out.println("Invalid number of args : java OperationsServer <host_ip> <host_port>");
            System.exit(1);
        }
        try{
            String registryHost = args[0];
            int registryPort = Integer.parseInt(args[1]);

            System.setProperty("java.rmi.server.hostname",registryHost);
            try{
                LocateRegistry.createRegistry(registryPort);
                System.out.println("RMI Registry running on port : "+registryPort);
            }catch(RemoteException e){
                System.out.println("RMI Registry already running on port : "+registryPort);
            }
            OperationsServerImpl operate = new OperationsServerImpl();
            String rmiURL = "rmi://"+registryHost+":"+registryPort+"/OperationsService";
            Naming.rebind(rmiURL,operate);
            System.out.println("Converter Service ready at "+rmiURL);
        }catch(Exception e){
            System.out.println("Server Exception : "+e.getMessage());
        }
    }
}