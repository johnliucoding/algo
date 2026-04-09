int factorial(int number) {

    // require number > 0

    if(number == 1) {
        return 1;
    } else {
        // multiplication happens after the recursive calls are made
        return number * factorial(number - 1);
    }
}

System.out.println(factorial(5))
/exit