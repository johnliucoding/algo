package semantic;

public class TestPrivate {

    static class A {
        private A() {
            System.out.println("Subclassed A in " + getClass().getName());
        }
    }

    static class B extends A {
        public B() {

        }
    }

    static void main(String... ignored) {
        new B();
    }
}
