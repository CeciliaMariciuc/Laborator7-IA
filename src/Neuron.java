import java.util.List;

public class Neuron {
    private List<Double> weights;

    public Neuron(List<Double> weights) {
        this.weights = weights;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "weights=" + weights +
                '}';
    }
}
