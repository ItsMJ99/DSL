import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Converter extends Remote{
    double temperature(double celcius) throws RemoteException;
    double distance(double miles) throws RemoteException;
}