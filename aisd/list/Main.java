package list;

public class Main {

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println("Список:");
        list.print();

        list.remove(2);

        System.out.println("\nСписок после удаления:");
        list.print();
    }
}
