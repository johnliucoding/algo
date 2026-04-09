int factorial(int number) {

    // require number > 0

    var product = 1;
    for(int i = 1; i <= number; i++) {
        product *= i;
    }
    return product;
}

System.out.println(factorial(5))
/exit