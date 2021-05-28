package Complexity;

public class Complexity {
    public static void loop() {
        // o(n), (logn),


        int total = 0;
        int n = 10;
//        // O(n)
//        for (int i = 1 ; i <= n; i++) { // i++ -> i = i + 1
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total);

        ///o(n) ////
//        total = 0;
//        n = 10;
//        for (int i = 1; i <= 10; i += 2) { // i = i + 2
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");

        /*
        i = 1, total = 1
        i = i + 2 = 3, total = 2
        i = i + 2 = 5, total = 3 <-
        i = i + 2 = 7, total = 4
        i = i + 2 = 9, total = 5
         */



        //// O(n) ////
//        total = 0;
//        n = 10;
//        for (int i = 0; i < n; i += 2) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
        /*
            i = 0, total = 1
            i = 2, total = 2
            i = 4, total = 3
            i = 6, total = 4
            i = 8, total = 5
         */

    //  infinity loop ////
//        total = 0;
//        n = 10;
//        for (int i = 0; i <= n; i *= 2) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total);
//        /*
//               i = 0, total = 1
//               i = 0, total = 2
//
//         */


        // log2(n) -> 1,2,4,8,16,32,64,128, i = 2^(iterator-1)
        // log2(n) -> 128, 64,32,16,8, 4,2,1
        // 35, 17, 8,4,2,1 -> log2n -> logN
        // 1, 3, 9, 27, 81, 243 -> log3N -> logN
        total = 0;
//        n = 20;
//        for (int i = n; i > 0; i /= 2) { // i = i / 2;
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//        total = 0;
//        n = 20;
//        for (int i = n; i >= 0; i /= 2) { // i = i / 2;
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//        // 20, 10, 5, 2, 1, 0, 0, 0, 0, 0,0 ,0 ,0 ,0
//
//        // log2(n)
//        total = 0;
//        n = 20;
//        for (int i = 1; i < n; i *= 2) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//        // 1,2,4,8,16
//
//        // log3N
//        total = 0;
//        n = 20;
//        for (int i = 1; i < n; i *= 3) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//
        // n^2
//        total = 0;
//        n = 3;
//        for (int i = 1; i <= n; i++) { // <- o(n)
//            for (int j = 1; j <= n; j++) { // <-O(n)
//                total = total + 1;
//                System.out.println( "i = "+ i + ", j = " + j);
//            }
//        }
        // total = 9, total = n * n = n^2
//        System.out.println( "\nTotal operations : " + total + "\n");
//
        // n^2
        total = 0;
//        n = 3;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= i; j++) {
//                total = total + 1;
//                System.out.println( "i = "+ i + ", j = " + j);
//            }
//        }
//        /*
//        i = 1, j = 1, total = 1
//        i = 2, j =1 , total = 2
//        i = 2, j = 2, total = 3
//        i = 3, j = 1, total = 4
//        i = 3, j = 2, total = 5
//        i = 3, j = 3, total = 6
//
//        total = n * (n + 1 ) / 2 = n^2 + n / 2 + 1 / 2
//         */
//
//        System.out.println( "\nTotal operations : " + total + "\n"); // n * (n+1)/2
//
        // n^2
//        total = 0;
//        n = 3;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j = j + 3) {
//                total = total + 1;
//                System.out.println( "i = "+ i + ", j = " + j);
//            }
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//
//
//        //nlog2n
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j = j * 2) {
//                total = total + 1;
//                System.out.println( "i = "+ i + ", j = " + j);
//            }
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");

    }
    public static void test() {
        int total, n;

//        // Test 1 //
//
//        total = 0;
//        n = 10;
//        for (int i = 0; i <= n; i += 5) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//
//        // 0 5 10
//
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//        // Test 2 //
//        total = 0;
//        n = 10;
//        for (int i = 0; i < n; i += 5) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//
//        // Test 3 //
//        total = 0;
//        n = 10;
//        for (int i = n; i >= 0; i = i - 2) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//        // Test 4 //
//        total = 0;
//        n = 10;
//        for (int i = 1; i >= n; i += 1) {
//            total = total + 1;
//            System.out.print(i + " ");
//        }
//        /*
//               i= 1
//         */
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//        // Test 5 //
//        total = 0;
//        n = 10;
//        for (int i = 5; i <= n; i += 5) {
//            for (int j = 1; j <= i; j += 5) {
//                total = total + 1;
//                System.out.println( "i = "+ i + ", j = " + j);
//            }
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//        /*
//        i =5 , j = 1
//        i = 10, j = 1
//        i = 10, j = 6
//         */
//
//        // Test 6//
////        n = 10;
////        total = 0;
////        while (n > 0) {
////            System.out.print(n + " ");
////            total ++;
////
////        }
//        System.out.println( "\nTotal operations : " + total + "\n");
//
//        // Test 7 //
//        int c = 1;
//        total = 0;
//        n = 20;
//        while (c < n) {
//            c *= 2;
//            System.out.print(c + " ");
//            total++;
//        }
//
//        System.out.println( "\nTotal operations : " + total + "\n");

        // Test 8 //
//        total = 0;
//        n = 2;
//        for (int i = 1; i <= n; i++) { // i = 1
//            for (int j = 1; j <= n; j ++) // j = 2
//                for (int k = 1; k <= n; k++) { // k = 1, k = 2
//                total = total + 1;
//                System.out.println( "i = "+ i + ", j = " + j +  " , k = " + k);
//            }
//        }
//        System.out.println( "\nTotal operations : " + total + "\n");

//        // test 9 //
//        n = 10;
//        total = 0;
//        while (n > 0) {
//            System.out.print(n + " ");
//            total ++;
//            n /= 2;
//        }
//
//        System.out.println( "\nTotal operations : " + total + "\n");

//        // Test 10//
        total = 0;
        n = 10;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                total ++;
                System.out.println( "i = "+ i + ", j = " + j);
            }
        }

        System.out.println( "\nTotal operations : " + total + "\n");
//        // https://en.wikipedia.org/wiki/Harmonic_series_(mathematics) -> Integral test
    }
    public static void main(String[] args) {
        test();
    }
}
