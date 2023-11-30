import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1"; // IP адрес сервера
    private static final int SERVER_PORT = 12345; // Порт сервера

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Подключено к серверу: " + SERVER_IP + ":" + SERVER_PORT);

            String message;
            while (true) {
                System.out.print("Введите сообщение (или 'выход' для выхода): ");
                message = userInput.readLine();
                if ("выход".equalsIgnoreCase(message)) {
                    break; // Выход из цикла, если пользователь ввел "выход"
                }
                out.println(message);

                // Ожидание подтверждения от сервера
                String confirmation = in.readLine();
                if (confirmation != null) {
                    System.out.println("Подтверждение от сервера: " + confirmation);
                } else {
                    System.out.println("Ошибка при получении подтверждения от сервера.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
