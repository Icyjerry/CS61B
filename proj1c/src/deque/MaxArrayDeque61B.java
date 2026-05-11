package deque;
import java.util.ArrayDeque;
import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private Comparator<T> comparator;
    public MaxArrayDeque61B(Comparator<T> c){
        this.comparator = c;
    }
    public T max(){
        T max = this.get(0);
        for (int i = 1; i < this.size(); i++){
            if (comparator.compare(this.get(i), max) > 0){
                max = this.get(i);
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        T max = this.get(0);
        for (int i = 1; i < this.size(); i++){
            if (c.compare(this.get(i), max) > 0){
                max = this.get(i);
            }
        }
        return max;

    }



}
