import time
import socket
from datetime import datetime

client_sockets = []
client_times = []

def start_server():
    host = '0.0.0.0'
    port = 8080
    num_clients = 1  # Set according to how many clients you want

    server_socket = socket.socket()
    server_socket.bind((host, port))
    server_socket.listen(5)
    print(f"Server started on {host}:{port}. Waiting for {num_clients} client(s)...")

    # Accept connections
    for i in range(num_clients):
        try:
            conn, addr = server_socket.accept()
            print(f"Connection completed with {addr}")
            client_sockets.append(conn)
        except socket.error as e:
            print(f"Error occurred while accepting connection: {e}")
            continue

    # Receive client times
    for conn in client_sockets:
        try:
            client_time = float(conn.recv(1024).decode())
            client_times.append(client_time)
            print(f"Received time from client: {datetime.fromtimestamp(client_time)}")
        except socket.error as e:
            print(f"Error occurred while receiving time: {e}")
            continue

    # Server (master) time
    master_time = time.time()
    print(f"Master (Server) Time: {datetime.fromtimestamp(master_time)}")
    client_times.append(master_time)

    # Calculate average time
    average_time = sum(client_times) / len(client_times)
    print(f"Calculated Average Time: {datetime.fromtimestamp(average_time)}")

    # Send synchronized average time to all clients
    for conn in client_sockets:
        try:
            conn.send(str(average_time).encode())
            print(f"Sent average time to client.")
        except socket.error as e:
            print(f"Error occurred while sending average time: {e}")

    # Close all client connections
    for conn in client_sockets:
        try:
            conn.close()
        except:
            pass

    server_socket.close()

if __name__ == "__main__":
    start_server()
