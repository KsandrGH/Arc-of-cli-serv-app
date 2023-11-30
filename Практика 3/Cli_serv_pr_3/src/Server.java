import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    private static final int PORT = 12345;
    private static final int BROADCAST_INTERVAL = 5000; // Интервал для рассылки сообщений (5 секунд)
    private static final int TIMEOUT = 5000; // Тайм-аут ожидания подтверждения (5 секунд)

    private static List<Socket> clients = new ArrayList<>();
    private static Queue<String> messageQueue = new LinkedList<>();
    private static long lastBroadcastTime = System.currentTimeMillis();

    private static Timer broadcastTimer = new Timer(true);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            // Устанавливаем таймер для рассылки сообщений
            broadcastTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    sendBroadcast();
                }
            }, 0, BROADCAST_INTERVAL);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                System.out.println("Клиент подключен: " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

                // Запускаем отдельный поток для обработки клиента
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcastMessage(String message) {
        messageQueue.add(message);
    }

    static synchronized void sendBroadcast() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastBroadcastTime >= BROADCAST_INTERVAL && !messageQueue.isEmpty()) {
            String message = messageQueue.poll();

            for (Socket client : clients) {
                try {
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println(message);

                    // Ожидание подтверждения от клиента
                    boolean confirmationReceived = true;
                    long startTime = System.currentTimeMillis();

                    while (!confirmationReceived && System.currentTimeMillis() - startTime < TIMEOUT) {

                    }

                    if (confirmationReceived) {
                        System.out.println("Сообщение успешно отправлено клиенту.");
                    } else {
                        System.out.println("Ошибка при отправке сообщения клиенту.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lastBroadcastTime = currentTime;
        }
    }
}
