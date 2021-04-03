package list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Time_benchmark {
    private static final int numbers_of_element = 50000, numbers_of_iterations = 2;
    List<Integer> list;
    public double averageTimeOfGet = 0, averageTimeOfSet = 0, averageTimeOfAdd;
    public Time_benchmark(List<Integer> list) {
        this.list = list;
    }
    public void addTesting() {
        Integer times = numbers_of_element;
        for (int i = 0; i < times;i++) {
            Integer element = new Integer(i);
            list.add(0, element);
        }
    }
    public void getTesting() {
        Integer val = list.get(500);
    }
    public void setTesting() {
        Integer times = numbers_of_element;
        for (int i = 0; i < times;i++) {
            Integer element = new Integer(i);
            list.add(element);
        }
    }


    public void visualize(String nameOfType) {
        System.out.println(nameOfType + " implementation: ");
        System.out.printf("get(int index): %.5f \n", averageTimeOfGet);
        System.out.printf("add(int index, E element): %.5f \n", averageTimeOfSet);
        System.out.printf("add(E element): %.5f \n", averageTimeOfAdd);
        System.out.println("---------------------------------------------");
    }

    public void benchmark() {

        for (int i = 0; i < numbers_of_iterations; i++) {
            long startTime = System.nanoTime();
            addTesting();
            long endTime = System.nanoTime();
            averageTimeOfAdd += (endTime - startTime);
            list.clear();
        }
//        System.out.println("Add test Done");
        averageTimeOfAdd /= numbers_of_iterations;

        for (int i = 0; i < numbers_of_iterations; i++) {
            long startTime = System.nanoTime();
            setTesting();
            long endTime = System.nanoTime();
            averageTimeOfSet += (endTime - startTime);
            list.clear();
        }
        averageTimeOfSet /= numbers_of_iterations;
//        System.out.println("Set test Done");
        addTesting();
        for (int i = 0; i < numbers_of_iterations; i++) {
            long startTime = System.nanoTime();
            getTesting();
            long endTime = System.nanoTime();
            averageTimeOfGet += endTime - startTime;
        }
        averageTimeOfGet /= numbers_of_iterations;
//        System.out.println("Get test done");

        // Convert nanoseconds to milliseconds
        averageTimeOfGet /= 1000000;
        averageTimeOfSet /= 1000000;
        averageTimeOfAdd /= 1000000;
    }
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<Integer> list;
        Time_benchmark arrayListBenchmark = new Time_benchmark(list = new ArrayList<>());
        Time_benchmark linkedListBenchmark = new Time_benchmark(list = new LinkedList<>());
        Time_benchmark vectorBenchmark = new Time_benchmark(list = new Vector<>());
        Time_benchmark myArrayList = new Time_benchmark(list = new MyArrayList<>());
        arrayListBenchmark.benchmark();
        linkedListBenchmark.benchmark();
        vectorBenchmark.benchmark();
        myArrayList.benchmark();

        arrayListBenchmark.visualize("Array list");
        linkedListBenchmark.visualize("Linked List");
        vectorBenchmark.visualize("Vector");
        myArrayList.visualize("MyArrayList");
        long endTime = System.nanoTime();
        System.out.println("Total execution time " +  (endTime - startTime) / 1000000);
    }
}
