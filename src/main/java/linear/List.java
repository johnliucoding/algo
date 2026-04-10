package linear;

public interface List<T> {

    void addFirst(T x);
    void addLast(T x);
    T getFirst();
    T getLast();
    T removeLast();
    T get(int i);
    void insert(T x, int p);
    int size();


    default String print() {

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            final T t = get(i);
            sb.append(" ");
            sb.append(t);
            sb.append(" ");
        }
        return sb.toString();
    }

}
