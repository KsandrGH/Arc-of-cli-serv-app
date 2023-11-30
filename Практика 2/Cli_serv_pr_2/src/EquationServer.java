import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EquationServer {
    public static void main(String[] args) {
        try {
            EquationSolver solver = new EquationSolverImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("EquationSolver", solver);
            System.out.println("Сервер готов к работе...");
        } catch (Exception e) {
            System.err.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }
}
