import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Layer {
    private List<Neuron> neurons = new ArrayList<>();

    public Layer(int nrNeurons, int nrPrevSynapses) {
        while (nrNeurons > 0) {
            List<Double> weights = new ArrayList<>();
            for (int i = 0; i < nrPrevSynapses; i++) {
                Random random = new Random();
                double randomValue = -0.1 + (0.2) * random.nextDouble();
                weights.add(randomValue);
            }
            neurons.add(new Neuron(weights));
            nrNeurons--;
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "neurons=" + neurons +
                '}';
    }
}
