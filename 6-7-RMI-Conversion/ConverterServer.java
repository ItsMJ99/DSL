import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

class  ConverterServer{
    public static void main(String []args) throws RemoteException, MalformedURLException{
        if(args.length < 2){
            System.out.println("Invalid Number of Args : java ConverterServer <host_ip> <host_port>");
            System.exit(1);
        }

        try{
            String registryHost = args[0];
            int registryPort = Integer.parseInt(args[1]);

            System.setProperty("java.rmi.server.hostname",registryHost);

            try{
                LocateRegistry.createRegistry(registryPort);
                System.out.println("RMI Registry started at Port : "+registryPort);
            }
            catch(RemoteException e){
                System.out.println("RMI Registry already running on port : "+registryPort);
            }

            String rmiURL = "rmi://"+registryHost+":"+registryPort+"/ConverterService";
            ConverterServerImpl converter = new ConverterServerImpl();
            Naming.rebind(rmiURL,converter);
            System.out.println("Converter Service ready at "+rmiURL);
        }
        catch(Exception e){
            System.out.println("Server Exception : "+e.getMessage());
        }
    }
}