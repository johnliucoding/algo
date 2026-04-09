package cs61b.thread;


public class ArrayStackThread implements Stack {
    private final int MAX_ELEMENTS = 10;
    private int[] stack;
    private int index;

    public ArrayStackThread() {
        stack = new int[MAX_ELEMENTS];
        index = -1;
    }

    @Override
    public synchronized void push(int elt) {
        if (index != stack.length - 1) {
            index++; // 1
            stack[index] = elt; // 2
        } else {
            throw new IllegalStateException("stack overflow");
        }

    }

    @Override
    public synchronized int pop() {
        if (index != -1) {
            int t = stack[index];
            index--;
            return t;
        } else {
            throw new IllegalStateException("stack underflow");
        }
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    public static void main(String[] args) {

        final ArrayStackThread stack = new ArrayStackThread();

        for (int i = 0; i < 100; i++) {
            System.out.println("----"+i+"----");
            new Thread(() -> {
                stack.push(4);
                System.out.println("pushed 4");
                stack.pop();
                System.out.println("poped 4");
            }).start();
            new Thread(() -> {
                System.out.println(stack.isEmpty());
            }).start();

        }



    }
}

interface Stack {
    void push(int elt);

    int pop();

    boolean isEmpty();
}