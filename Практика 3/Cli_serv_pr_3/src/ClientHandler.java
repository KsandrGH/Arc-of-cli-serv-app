import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Получено сообщение от клиента " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() + ": " + message);
                Server.broadcastMessage(message); // Передаем сообщение серверу для рассылки
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Здесь можно добавить код для удаления текущего клиента из списка клиентов на сервере.
        }
    }
}
