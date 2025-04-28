import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operations extends Remote{
    String Greet(String name) throws RemoteException;
    String LargeString(String str1,String str2) throws RemoteException;
    int CountVowels(String word) throws RemoteException;
    int Factorial(int num) throws RemoteException;
}