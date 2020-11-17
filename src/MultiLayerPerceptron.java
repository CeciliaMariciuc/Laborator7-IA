import java.lang.Math;
import java.util.*;

public class MultiLayerPerceptron {

    private int epochNr;
    private double learnRate;
    private Function function;
    private Network network;

    private List<Double> input_values = new ArrayList<>();
    private List<Double> hidden_values = new ArrayList<>();
    private List<Double> output_values = new ArrayList<>();

    private double[][] DW1 = new double[2][2];
    private List<Double> gradients_hidden_layer = new ArrayList<>();

    private double[][] DW2 = new double[1][2];
    private List<Double> gradients_output_layer = new ArrayList<>();

    public MultiLayerPerceptron(int epochNr, double learnRate, Function function) {
        this.epochNr = epochNr;
        this.learnRate = learnRate;
        this.function = function;
        this.network = new Network();
    }

    double sigmActivFunction(double x) {
        return 1 / (1 + Math.pow(Math.E, x * (-1)));
    }

    double sigmActivFunctionDeriv(double x) {
        return sigmActivFunction(x) * (1 - sigmActivFunction(x));
    }

    double activate(List<Double> inputs, List<Double> weights) {
        double activation = -0.1 + (0.2) * (new Random()).nextDouble();
        for (int i = 0; i < weights.size(); i++) {
            activation += weights.get(i) * inputs.get(i);
        }

        return activation;
    }

    public List<Double> forward_propagation(List<Double> inputs) {
        List<Double> current_inputs = inputs;
        input_values = inputs;
        int layerNr = 0;
        for (Layer layer : network.getLayerList()) {
            List<Double> new_inputs = new ArrayList<>();
            for (Neuron neuron : layer.getNeurons()) {
                double activation = activate(inputs, neuron.getWeights());
                activation = sigmActivFunction(activation);

                new_inputs.add(activation);
            }
            if (layerNr == 0) hidden_values = new_inputs;
            else output_values = new_inputs;
            layerNr++;
            current_inputs = new_inputs;
        }
        return current_inputs;
    }

    public void back_propagation(double expected) {
        Collections.reverse(network.getLayerList());
        int layerNr = 2;
        double[] D1; // hidden layer Deltas
        double[] D2; // output layer Deltas
        List<Double> delta = new ArrayList<>();
        for (Layer layer : network.getLayerList()) {
            List<Double> errors = new ArrayList<>();
            //output layer
            if (layerNr == 2) {
                //gradients
                for (Neuron neuron : layer.getNeurons()) {
                    double error = (expected - output_values.get(0));
                    gradients_output_layer.add(error * sigmActivFunctionDeriv(output_values.get(0)));
                    errors.add(error);
                }
                //corecțiile ponderilor dintre stratul ascuns și stratul de ieșire
                for (int j = 0; j < 2; j++) {
                    DW2[0][j] += learnRate * gradients_output_layer.get(0) * hidden_values.get(j);
                }
                //
            } else {
                //gradients
                int nrNeuron = 0;
                for (Neuron neuron : layer.getNeurons()) {
                    double gradient = 0.0;
                    for (int k = 0; k < neuron.getWeights().size(); k++) {
                        gradient += neuron.getWeights().get(k) * gradients_output_layer.get(0);
                    }
                    gradient *= sigmActivFunctionDeriv(hidden_values.get(nrNeuron));
                    nrNeuron++;
                    gradients_hidden_layer.add(gradient);
                }
                //corecțiile ponderilor dintre stratul de intrare și stratul ascuns
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        DW1[i][j] += learnRate * gradients_hidden_layer.get(i) * input_values.get(j);

                    }
                }
            }
            layerNr--;
        }
        Collections.reverse(network.getLayerList());
    }

    public void updateWeights() {
        int layerNr = 1;
        for (Layer layer : network.getLayerList()) {
            if (layerNr == 1) {
                for (int i = 0; i < 2; i++) {
                    Neuron neuron = layer.getNeurons().get(i);
                    for (int j = 0; j < 2; j++) {
                        double prevWeight = neuron.getWeights().get(j);
                        neuron.getWeights().set(j, prevWeight + DW1[i][j]);
                    }
                }
            } else {
                Neuron neuron = layer.getNeurons().get(0);
                for (int j = 0; j < 2; j++) {
                    double prevWeight = neuron.getWeights().get(j);
                    neuron.getWeights().set(j, prevWeight + DW2[0][j]);
                }
            }
            layerNr++;
        }
    }

    //Am adaugat afisari pentru verificare
    public void train_network() {
        for (int i = 0; i < epochNr; i++) {
            System.out.println("Epoca nr. " + (i + 1));
            for (Row row : function.getValues()) {
                List<Double> input = new ArrayList<>();
                input.add((double) row.getX1());
                input.add((double) row.getX2());
                //afisez input-ul pe care se testeaza
                System.out.println("x1: " + row.getX1() + ", x2: " + row.getX2());
                List<Double> output = forward_propagation(input);
                //afisez output-ul pt fiecare set de input
                System.out.println("Output forward-propag. : " + output);
                double expected = row.getY();
                back_propagation(expected);
                updateWeights();
            }
        }
    }

    @Override
    public String toString() {
        return "MultiLayerPerceptron{" +
                "epochNr=" + epochNr +
                ", learnRate=" + learnRate +
                ", function=" + function +
                ",\n network=" + network +
                '}';
    }
}