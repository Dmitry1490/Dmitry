
import fruits.Fruit;

import java.util.ArrayList;

public class BoxWithFruits<T extends Fruit> {
    private float weightBox;
    private float currentWeight = 0;
    private ArrayList<T> list = new ArrayList<T>();

    public float getWeight() {return this.currentWeight;}

    public BoxWithFruits(float size) {
        this.weightBox = size;
    }

    public ArrayList<T> addFruitInBox(T fruit){

        if(currentWeight + fruit.getWeight() < weightBox){
            list.add(fruit);
            currentWeight = currentWeight + fruit.getWeight();
        } else System.out.println("Box is full.");
        return list;
    }

    public boolean compare(BoxWithFruits<?> box){
        return this.getWeight() == box.getWeight();
    }

    public BoxWithFruits<T> intersperse(BoxWithFruits<T> box){
        if(!(box.weightBox < this.getWeight())){
            for (T fruit: this.list) {
                box.addFruitInBox(fruit);
            }
            this.list.clear();
            this.currentWeight = 0;
            return box;
        } else {
            System.out.println("Box is't suitable");
            return box;
        }
    }


}
