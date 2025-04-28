import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperationsServerImpl extends UnicastRemoteObject implements Operations {

    protected OperationsServerImpl() throws RemoteException {
        super();
    }

    public String Greet(String name) throws RemoteException {
        return "Hello " + name;
    }

    public String LargeString(String str1, String str2) throws RemoteException {
        return (str1.compareTo(str2) > 0) ? str1 : str2;
    }

    public int CountVowels(String word) throws RemoteException {
        int vowels = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                vowels++;
            }
        }
        return vowels;
    }

    public int Factorial(int num) throws RemoteException {
        int fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }
}
