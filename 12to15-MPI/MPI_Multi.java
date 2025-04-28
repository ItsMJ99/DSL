import mpi.MPI;
import java.util.Arrays;

class MPI_Multi {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();      // Get the rank of the current process
        int size = MPI.COMM_WORLD.Size();      // Get the total number of processes

        // If there aren't enough arguments, print an error message and finalize
        int mpjArgCount = 3;  // Skipping the MPJ-specific args
        if (args.length <= mpjArgCount) {
            if (rank == 0) {
                System.out.println("Invalid Number of args : java -np <process_no> MPI_Multi <num1> <num2>...");
            }
            MPI.Finalize();
            return;
        }

        // Parse the array of integers from arguments
        int n = args.length - mpjArgCount;  // Number of actual array elements
        int[] array = new int[n];
        try {
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(args[i + mpjArgCount]);  // Parse the integers
            }
        } catch (NumberFormatException e) {
            System.out.println("Error : Invalid number format");
            MPI.Finalize();
            return;
        }

        // Calculate the chunk size for each process
        int chunkSize = n / size;  // Divide the array into chunks
        int start = rank * chunkSize;  // The starting index for this process
        int end = (rank == size - 1) ? n : start + chunkSize;  // The ending index, the last process handles remaining elements

        // Calculate the intermediate product for the current chunk
        int interMulti = 1;  // Initialize as 1 for multiplication
        for (int i = start; i < end; i++) {
            interMulti *= array[i];  // Multiply the elements
        }

        // Print the intermediate results from each process
        System.out.println("Received partial array at Rank " + rank + ": " + Arrays.toString(Arrays.copyOfRange(array, start, end))
                + ", Intermediate Multiplication = " + interMulti);

        // Use MPI.COMM_WORLD.Reduce to compute the final product from all processes
        int[] totalProd = new int[1];
        MPI.COMM_WORLD.Reduce(new int[]{interMulti}, 0, totalProd, 0, 1, MPI.INT, MPI.PROD, 0);

        // Rank 0 prints the total product after reducing the intermediate products
        if (rank == 0) {
            System.out.println("Total Product: " + totalProd[0]);
        }

        // Finalize the MPI environment
        MPI.Finalize();
    }
}
