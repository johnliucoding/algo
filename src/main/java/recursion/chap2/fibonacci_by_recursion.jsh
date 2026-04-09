int fibonacci(int nthNumber) {
    // require number > 0
    if (nthNumber == 1 || nthNumber == 2) {
        // BASE CASE
        return 1;
    } else {
        // RECURSIVE CASE
        var result = fibonacci(nthNumber - 1) + fibonacci(nthNumber - 2);
        return result;
    }
}

System.out.println(fibonacci(10))

/exit