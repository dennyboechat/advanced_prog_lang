public class Main {
    public static void main(String[] args) {
        Integer heapInt = new Integer(1);
        System.out.println("Heap int: " + heapInt);

        heapInt = null;

        System.gc();
    }
}