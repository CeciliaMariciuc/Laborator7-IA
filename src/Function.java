import java.util.ArrayList;
import java.util.List;

public class Function {
    List<Row> values = new ArrayList<>();

    public Function(List<Integer> y_values) {
        values.add(new Row(0, 0, y_values.get(0)));
        values.add(new Row(0, 1, y_values.get(1)));
        values.add(new Row(1, 0, y_values.get(2)));
        values.add(new Row(1, 1, y_values.get(3)));
    }

    public List<Row> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "Function{" +
                "values=" + values +
                '}';
    }
}
