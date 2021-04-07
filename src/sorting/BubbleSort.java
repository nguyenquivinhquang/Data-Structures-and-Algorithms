package sorting;
import java.util.Comparator;
public class BubbleSort<E> implements ISort<E> {
    public static void sort(E arr[], Comparator<E> comparator){
        int current, walker;
        boolean flag;
        current = 0; 
        flag = false;
        while((current < arr.length-1) && (flag == false)){
            walker = arr.length - 1; //start from the last and backward
            flag = true; //for testing if the input already in ascending order
            while(walker > current){
                if(comparator.compare(arr[walker],arr[walker-1]) < 0){
                    flag = false;
		    E temp = arr[walker]; arr[walker] = arr[walker-1]; arr[walker-1] = temp; 
                }
                walker -= 1;
            }
            current += 1;
        }
    }
}
