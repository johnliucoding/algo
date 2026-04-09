package cs61b.list;

// singly linked list
public class IntList {
    public int first;
    public IntList rest;

    public IntList() {

    }

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    public int size() { // this must be the head
        if (this.rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    // use the name p to remind yourself that the variable is holding a pointer
    public int iterativeSize() {
        int size = 0;
        IntList p = this;

        while (p != null) {
            size++;
            p = p.rest;
        }
        return size;
    }

    public int get(int index) {
        if(index < 0) {
            throw  new IllegalArgumentException("can't not be negative");
        }
        if(index == 0) {
            return first;
        }
        IntList p = this;
        while (index > 0) {
            if(p.rest == null) {
                throw new IndexOutOfBoundsException("index too big");
            }
            index--;
            p = p.rest;
        }
        return p.first;

    }


    @Override
    public String toString() {
        return first +  " -> " + rest;
    }

    public static void squareList(IntList l) {
        while (l != null) {
            l.first = l.first * l.first;
            l = l.rest;
        }
    }

    public static IntList of(int... vals) {
       if(vals.length == 0) {
           return null;
       }
       IntList list = new IntList();

       IntList head = list;

        for (int i = 0; i < vals.length; i++) {
           head.first = vals[i];


           if((i+1) < vals.length) {
               head.rest = new IntList();
           }


           head = head.rest;
        }

        return list;
    }

    public static IntList squareListIterative(IntList l) {


        final IntList squared = new IntList(0, null);

        IntList last = squared; // last element in the squared list
        while (l != null) {
           last.first = l.first * l.first;
//           if(l.rest == null) {
//               last.rest = null;
//           } else {
//               last.rest = new IntList(0, null);
//           }
           if(l.rest != null) {
               last.rest = new IntList(0, null);
           }

           l = l.rest;
           last = last.rest;
        }

        return squared;
    }

    public static IntList squareListRecursive(IntList l) {
        if (l == null) {
            return null;
        }

        return new IntList(l.first * l.first, squareListRecursive(l.rest));
    }

    public static IntList dconcate(IntList a, IntList b) {
        if(a == null) {return b;}
        IntList last = a;
        while (last.rest != null) {
            last = last.rest;
        }

        last.rest = b;

        return a;
    }

    public static IntList concate(IntList a, IntList b) {
        if(a == null) {return b;}
        final IntList newList = new IntList(0, null);

        IntList last = newList;
        while (a.rest != null) {
            last.first = a.first;
            a = a.rest;
            last.rest = new IntList(a.first, null);
            last = last.rest;
        }
        if(b == null) {
            return newList;
        }

        last.rest = new IntList(b.first, null);
        last = last.rest;
        while (b.rest != null) {
            b = b.rest;
            last.rest = new IntList(b.first, null);
            last = last.rest;
        }
        return newList;
    }


}
