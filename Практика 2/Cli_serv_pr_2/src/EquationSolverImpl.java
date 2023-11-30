import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EquationSolverImpl extends UnicastRemoteObject implements EquationSolver {
    protected EquationSolverImpl() throws RemoteException {
        super();
    }

    @Override
    public EquationResult solveEquation(double a, double b, double c) throws RemoteException {
        // Решение квадратного уравнения и создание объекта EquationResult
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new EquationResult(root1, root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return new EquationResult(root);
        } else {
            return new EquationResult(); // Уравнение не имеет действительных корней
        }
    }
}
