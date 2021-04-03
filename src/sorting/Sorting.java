package sorting;

import geom.*;

public class Sorting {
    public static void main(String[] args) {
//        StraightInsertionSortDemo.demo();
        sortingAPI();
    }
    public static void sortingAPI() {

        ISort[] algorithms = {
                new StraightInsertionSort<Point2D>(),
                new ShellSort<Point2D>(),
                new StraightSelectionSort<Point2D>(),
                new BubbleSort<Point2D>()
        };

        String[] sort_algo_str = {"InsertionSort", "ShellSort", "SelectionSort", "BubbleSort"};
        for(int aIdx=0; aIdx < algorithms.length; aIdx++){
            Point2D[] points = Point2D.generate(100, -20, 20);
            //If you want to sort ...
            algorithms[aIdx].sort(points, new PointComparator(true)); //do sorting
            //If you want to time it ...
            Point2D[] time = SortingEval.timeit(algorithms[aIdx], 10, 100, true);
            //here: more code for other purpose.

            System.out.println(String.format("Benchmarks for %s:", sort_algo_str[aIdx]));
            for (Point2D tmp : time) {
                System.out.println(String.format("%d\t%.8f", (int)tmp.getX(), tmp.getY()));
            }
            System.out.println();
        }
    }
}