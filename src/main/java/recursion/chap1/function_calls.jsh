void c() {
    System.out.println("c() was called.");
    System.out.println("c() is returning.");
}

void b() {
    System.out.println("b() was called.");
    c();
    System.out.println("b() is returning.");
}

void a() {
    System.out.println("a() was called.");
    b();
    System.out.println("a() is returning.");
}

a();
/exit