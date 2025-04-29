import socket
import time
import random
from datetime import datetime

# Function to start the client
def start_client():
    host = '127.0.0.1'  # IP address of the server (localhost for now)
    port = 8080         # Port number to connect to (should match server's port)

    # Create a TCP socket
    client_socket = socket.socket()
    try:
        # Try connecting to the server
        client_socket.connect((host, port))
    except socket.error as e:
        print(f"Failed to connect to server: {e}")
        return  # Exit if connection fails

    # Get the local time of the client with a random error (-5 to +5 seconds)
    local_time = time.time() + random.uniform(-5, 5)  
    print(f"Client local time (before sync): {datetime.fromtimestamp(local_time)}")

    try:
        # Send the (fake) local time to the server
        client_socket.send(str(local_time).encode())

        # Receive the synchronized time from the server
        sync_time = float(client_socket.recv(1024).decode())
        print(f"Synchronized time received from server: {datetime.fromtimestamp(sync_time)}")

    except socket.error as e:
        print(f"Error communicating with server: {e}")

    finally:
        # Close the socket connection in any case
        client_socket.close()

# Start the client when the script runs
if __name__ == '__main__':
    start_client()
