package practice;

import list.DLinkedList;
import list.List;

import java.util.ListIterator;

public class Practice {
    public static void example() {
        DLinkedList<Integer> list = new DLinkedList<>(); // khai  báo list là kiểu Integer
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        ListIterator<Integer> it = list.listIterator(); // khai báo it là listIterator

        System.out.println("Ví dụ về dùng listIterator để in phần tử trong list:");
        while (it.hasNext()) {
            Integer item = it.next();
            System.out.print(item + " ");
        }

        it = list.listIterator(); // muốn it = head lại thì phải khai báo lại dòng này
        System.out.println("\n---------------------------------------");
        System.out.println("Ví dụ về dùng listIterator để xóa phần tử <= 5 trong list:");
        while (it.hasNext()) {
            Integer item = it.next();
            if (item <= 5)
                it.remove();

        }
        it = list.listIterator();
        System.out.println("Dãy sau khi xóa:");
        while (it.hasNext()) {
            Integer item = it.next();
            System.out.print(item + " ");
        }
        System.out.println("\n---------------------------------------");
        System.out.println("Ví dụ về list.add(index, value), ở đây sẽ add số 507 vào vị trí số 3, tức là sau số 8:");

        list.add(3, 507); // <- add ở đây

        it = list.listIterator();
        System.out.println("Dãy sau khi thêm số 507 vào vị trí số 3:");
        while (it.hasNext()) {
            Integer item = it.next();
            System.out.print(item + " ");
        }

        System.out.println("\n---------------------------------------");
        System.out.println("Ví dụ về list.remove(index), ở đây sẽ xóa số ở vị trí số 2, tức là số 8");

        list.remove(2); // <- xóa ở vị trí 2

        it = list.listIterator();
        System.out.println("Dãy sau khi xóa số ở vị trí số 2, nghĩa là số 8 bị biến mất:");
        while (it.hasNext()) {
            Integer item = it.next();
            System.out.print(item + " ");
        }


    }

    public static void practice1() {
        DLinkedList<Integer> list = new DLinkedList<>(); // khai  báo list là kiểu Integer
        // Bây giờ tui muốn list nó sẽ có các số (0,5,0,7)
        // Và in các số đó ra, hint: dùng list.add, và in thì dùng ví dụ ở trên
    }

    public static void practice2() {
        DLinkedList<Integer> list = new DLinkedList<>(); // khai  báo list là kiểu Integer
        for (int i = 0; i <= 10; i += 2) {
            list.add(i);
        }
        // 1. Sau khi đoạn code trên chạy, list hiện tai có những số nào, làm tay rùi so sánh lại với kết quả
        // 2. Tui muôn xóa phần tử đầu tiên thì làm sao
        // 3. Tui muốn xóa phần tử cuối cùng thì làm sao
    }
    public  static void practice3() {
        DLinkedList<Integer> list = new DLinkedList<>(); // khai  báo list là kiểu Integer
        list.add(5);
        list.add(0);
        // 1. Sau khi đoạn code trên chạy, list hiện tai có những số nào, làm tay rùi so sánh lại với kết quả
        // 2. Hãy thêm số 0 vào đầu list
        // 3. Hãy thêm số 7 va2p cuối list
        // 4. In kết quả.
    }
    public  static void practice4() {
        DLinkedList<Integer> list = new DLinkedList<>(); // khai  báo list là kiểu Integer
        for (int i = 0; i <= 100; i++) {
            list.add(3*i + 7);
        }

        // 1. Bà hãy viết code kiểm tra xem list có tồn tại số 172 hay ko
        // 2. Bà hãy viết code kiểm tra xem list có tồn tại số 50 hay ko
        //hint: dùng contains
        // 3. Hãy in ra vị trí (index) của sớ 157. Hint: dùng indexOf


    }
    public  static void practice5() {
        DLinkedList<Integer> list = new DLinkedList<>(); // khai  báo list là kiểu Integer
        for (int i = 0; i <= 20; i++) {
            list.add(5 * i + 7);
        }
        // bài này dùng listIterator nha.
        // xóa các số chẵn có trong list.
        // muốn biết 1 số chẵn hay ko thì
        /*
            giả sử số cần xét là number
            if (number % 2 == 0){
                Đây là số chẵn
            }
         */

    }
    public static void main(String[] args) {
        example();
        //practice1();
//        practice2();
//        practice3();
//        practice4();
//        practice5();
    }
}
