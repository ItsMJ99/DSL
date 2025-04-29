import socket
import time
from datetime import datetime

# Lists to store connected clients and their reported times
client_sockets = []
client_times = []

def start_server():
    host = '0.0.0.0'  # Listen on all available network interfaces
    port = 8080       # Port to listen on
    num_clients = 1   # Number of clients expected to connect (can change)

    # Create a TCP socket
    server_socket = socket.socket()
    server_socket.bind((host, port))  # Bind server to host and port
    server_socket.listen(5)           # Allow up to 5 connections waiting
    print(f"Server started on {host}:{port}. Waiting for {num_clients} client(s)...")

    # Accept connections from clients
    for i in range(num_clients):
        try:
            conn, addr = server_socket.accept()  # Accept a client connection
            print(f"Client {i+1} connected from {addr}")
            client_sockets.append(conn)  # Save client connection
        except socket.error as e:
            print(f"Error accepting connection: {e}")
            continue  # Continue to next client if error occurs

    # Receive the time from each client
    for conn in client_sockets:
        try:
            client_time = float(conn.recv(1024).decode())  # Receive and decode client's time
            client_times.append(client_time)               # Save client time
            print(f"Received client time: {datetime.fromtimestamp(client_time)}")
        except socket.error as e:
            print(f"Error receiving time: {e}")
            continue  # Skip to next client if error occurs

    # Get server's current time
    master_time = time.time()
    print(f"Server (Master) time: {datetime.fromtimestamp(master_time)}")
    client_times.append(master_time)  # Add server time to the list

    # Calculate the average of all times (client + server)
    average_time = sum(client_times) / len(client_times)
    print(f"Calculated average synchronized time: {datetime.fromtimestamp(average_time)}")

    # Send the synchronized (average) time back to all clients
    for conn in client_sockets:
        try:
            conn.send(str(average_time).encode())  # Send average time as string
        except socket.error as e:
            print(f"Error sending time: {e}")

    # Close all connections after sending the synchronized time
    print("Synchronized time sent to all clients. Closing connections.")
    for conn in client_sockets:
        try:
            conn.close()
        except socket.error:
            pass  # Ignore errors while closing
    server_socket.close()  # Close the server socket

# Start the server when the script runs
if __name__ == '__main__':
    start_server()
