import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EquationClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            EquationSolver solver = (EquationSolver) registry.lookup("EquationSolver");

            // Здесь можно передавать параметры a, b и c и вызывать удаленный метод
            double a = 1.0;
            double b = -3.0;
            double c = 2.0;
            EquationResult result = solver.solveEquation(a, b, c);

            // Вывести результат
            if (!Double.isNaN(result.getRoot1())) {
                System.out.println("Корень 1: " + result.getRoot1());
            }
            if (!Double.isNaN(result.getRoot2())) {
                System.out.println("Корень 2: " + result.getRoot2());
            }
        } catch (Exception e) {
            System.err.println("Ошибка при работе с сервером: " + e.getMessage());
        }
    }
}
