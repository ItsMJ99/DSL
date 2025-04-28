import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServerImpl extends UnicastRemoteObject implements Calculator {

    protected CalculatorServerImpl() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    public double divide(double a, double b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Division by zero not allowed.");
        return a / b;
    }

    public double power(double a, double b) throws RemoteException {
        return Math.pow(a, b);
    }
}
