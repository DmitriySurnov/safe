package dataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Assumptions {
    public int number;

    public Number[] position;

    public Assumptions(int Number) {
        number = Number;
        position = new Number[] {3 , 2 ,1 , 0};
    }

    public  void delitIndex(int Index){
        int index = Arrays.asList(position).indexOf(Index);
        position[index] = -1;
    }
}
