import java.io.Serializable;

public class EquationResult implements Serializable {
    private double root1;
    private double root2;

    public EquationResult(double root1, double root2) {
        this.root1 = root1;
        this.root2 = root2;
    }

    public EquationResult(double root1) {
        this.root1 = root1;
        this.root2 = Double.NaN;
    }

    public EquationResult() {
        this.root1 = Double.NaN;
        this.root2 = Double.NaN;
    }

    public double getRoot1() {
        return root1;
    }

    public double getRoot2() {
        return root2;
    }
}
