import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConverterServerImpl extends UnicastRemoteObject implements Converter{
    protected ConverterServerImpl() throws RemoteException{
        super();
    }
    public double temperature(double celcius) throws RemoteException{
        return ((celcius * (9.0/5.0)) + 32);
    }
    public double distance(double miles) throws RemoteException{
        return (miles*1.609);
    }
}