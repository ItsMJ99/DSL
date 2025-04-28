import mpi.*;

public class MPI_Reciprocal {
    public static void main(String[] args) {
        // Proper way: get clean args from MPI
        String[] newArgs = MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        double[] send = new double[size];
        double[] recv = new double[1];
        double[] res = new double[size];

        if (rank == 0) {
            // Check if user gave enough inputs
            if (newArgs.length < size) {
                System.out.println("Error: Please provide " + size + " numbers as arguments!");
                MPI.Finalize();
                return;
            }

            // Read numbers safely
            for (int i = 0; i < size; i++) {
                send[i] = Double.parseDouble(newArgs[i]);
            }
        }

        // Scatter to all processes
        MPI.COMM_WORLD.Scatter(send, 0, 1, MPI.DOUBLE, recv, 0, 1, MPI.DOUBLE, 0);

        // Each process calculates reciprocal
        recv[0] = 1.0 / recv[0];

        // Gather results at root
        MPI.COMM_WORLD.Gather(recv, 0, 1, MPI.DOUBLE, res, 0, 1, MPI.DOUBLE, 0);

        if (rank == 0) {
            System.out.println("Reciprocals are:");
            for (int i = 0; i < size; i++) {
                System.out.printf("1/%.2f = %.6f\n", send[i], res[i]);
            }
        }

        MPI.Finalize();
    }
}

//Steps to run this program 
/*
 * 1) Unzip the "mpjexpress" into the "C drive" of the machine such that "C:\mpjexpress"
 * 2) Steps to run the code 
 *      2.1) Set Environment Variables (Temporary, in your Command Prompt of the system not in VScode)
 *          set MPJ_HOME=C:\mpjexpress
            set PATH=%PATH%;%MPJ_HOME%\bin
        2.2) Compile your code with MPJ library
            javac -cp .;%MPJ_HOME%\lib\mpj.jar Main.java
        2.3) Run using mpjrun
            mpjrun.bat -np 4 Main
            Here 4 means 4 process 
 */