import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduceti cele 4 valori ale functiei: ");
        int in1 = scan.nextInt();
        int in2 = scan.nextInt();
        int in3 = scan.nextInt();
        int in4 = scan.nextInt();
        //AM ADAUGAT SI INTRODUCEREA NR. EPOCI SI RATA  DE INVATARE DE LA TASTATURA

        int epochNr = scan.nextInt();
        double learnRate = scan.nextDouble();
        Function function = new Function(Arrays.asList(in1, in2, in3, in4));
        MultiLayerPerceptron multiLayerPerceptron = new MultiLayerPerceptron(epochNr, learnRate, function);

        //Am adaugat apelurile necesare pt testare
        System.out.println(multiLayerPerceptron);
        //System.out.println(multiLayerPerceptron.forward_propagation(Arrays.asList(0.0, 1.0)));
        multiLayerPerceptron.train_network();
    }

}
