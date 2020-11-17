import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Layer> layerList = new ArrayList<>();

    public Network() {
        //input layer - strat intrare
        //Layer inputLayer = new Layer(2, 2);
        //hidden layer - strat ascuns
        Layer hiddenLayer = new Layer(2, 2);
        //output layer - strat iesire
        Layer outputLayer = new Layer(1, 2);
        //layerList.add(inputLayer);
        layerList.add(hiddenLayer);
        layerList.add(outputLayer);
    }

    public List<Layer> getLayerList() {
        return layerList;
    }

    public void setLayerList(List<Layer> layerList) {
        this.layerList = layerList;
    }

    @Override
    public String toString() {
        return "Network{" +
                "layerList=" + layerList +
                '}';
    }
}
