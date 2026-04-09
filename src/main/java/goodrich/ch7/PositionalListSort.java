package goodrich.ch7;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 9:26 PM
 */
public class PositionalListSort {

    public static void insertionSort(PositionalList<Integer> list) {
        Position<Integer> pointer = list.first();
        while(pointer != list.last()) {
            final Position<Integer> pivot = list.after(pointer);
            final Integer value = pivot.getElement();
            if(value > pointer.getElement()) {
                pointer = pivot;
            } else {
                Position<Integer> walker = pivot;
                while(walker != list.first() && list.before(walker).getElement() > value) {
                    walker = list.before(walker);
                }
                list.remove(pivot);
                list.addBefore(walker, value);
            }
        }

    }

    public static void main(String[] args) {
        final LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
        list.addLast(4);
        list.addLast(3);
        list.addLast(2);

        for(int a : list) {
            System.out.println(a);
        }

        insertionSort(list);

        for(int a : list) {
            System.out.println(a);
        }

    }
}
