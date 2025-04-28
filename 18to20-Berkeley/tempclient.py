import socket
import random
import time
from datetime import datetime

def start_client():
    host = "192.168.169.128"  # Change this to your server IP
    port = 8080

    client_socket = socket.socket()
    try:
        client_socket.connect((host, port))
    except socket.error as e:
        print("Error occurred while connecting to server:", e)
        return
    
    # Local time with random error
    local_time = time.time() + random.uniform(-5, 5)
    print(f"Local Time (before sync): {datetime.fromtimestamp(local_time)}")

    try:
        client_socket.send(str(local_time).encode())
        sync_time = float(client_socket.recv(1024).decode())  # << corrected 'client_socket' not 'client_time'
        print(f"Synchronized Time Received: {datetime.fromtimestamp(sync_time)}")
    except socket.error as e:
        print("Error occurred during communication:", e)
    finally:
        client_socket.close()

if __name__ == '__main__':
    start_client()
