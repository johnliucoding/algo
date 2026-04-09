int fibonacci(int nthNumber) {
    // require number > 0
    var a = 1;
    var b = 1;
    for(int i = 1; i < nthNumber; i++ ) { // at the end of execution i is one more 
        var nextNumber = a + b;
        a = b;
        b = nextNumber;
    }

    return a;
}

System.out.println(fibonacci(10))

/exit