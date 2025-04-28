import java.rmi.Naming;
import java.util.Scanner;

class ConverterClient{
    public static void main(String []args){
        if(args.length < 2){
            System.out.println("Invalid Number of Args : java ConverterServer <host_ip> <host_port>");
            System.exit(1);
        }

        try{
            String serverIP = args[0];
            int serverPort = Integer.parseInt(args[1]);

            String rmiURL = "rmi://"+serverIP+":"+serverPort+"/ConverterService";
            Converter convert = (Converter) Naming.lookup(rmiURL);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Converter Menu ===");
                System.out.println("1. Temperature");
                System.out.println("2. Distance");
                System.out.println("3. Exit");
                System.out.print("Choose operation: ");
                int choice = sc.nextInt();
                double result = 0;
                switch(choice){
                    case 1:{
                        System.out.println("Enter Temperature in Celcius : ");
                        double temp = sc.nextDouble();
                        result = convert.temperature(temp);
                        System.out.println(temp+"Celcius = "+result+"Fahrenheit");
                        break;
                    }
                    case 2:{
                        System.out.println("Enter Distance in Miles : ");
                        double dist = sc.nextDouble();
                        result = convert.distance(dist);
                        System.out.println(dist+"Miles = "+result+"Km");
                        break;
                    }
                    case 3:{
                        System.out.println("Exiting......");
                        System.exit(1);
                    }
                    default:{
                        System.out.println("Invalid Input !!!");
                    }
                }
                
            }

        }catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}