## TDD: writing test first, modify the code to make test pass, aka. gamifies the assessment process

Identify a new feature.
Write a unit test for that feature.
Run the test. It should fail.
Write code that passes the test. Yay!
Optional: refactor code to make it faster, cleaner with the tests verifying that we don't have regression.


## Test: verifies correctness

setup -> exercise -> checking

downsides to unit testing:
writing thorough tests takes time, it's easy to write incomplete unit tests which give a false confidence
difficult to write tests for units that depend on other units

unit testing in general is most definitely a good idea with or without TDD

Integration Testing: verifies that components interact properly together.

## debug

breakpoint
visual debugging
stepping through the code line-by-line
step over  vs. step into

## logging


##

invariants
iterating on a design

recursive helper method
a private helper method with an additional parameter that delineate which part of the array
to consider
this approach is quite common when trying to use recursion on a data structure that is not inherently
recursive, e.g. arrays

sub-array, sub-array-view
range
a subset of a larger array

##  common tasks for array

equals(A,B)
toString(A)
hashCode(A)

compare
compareUnsigned
mismatch

fill(A, x)
copyOf(A,n)
copyOfRange(A, s, t)

sort(A)
binarySearch(A, x)


asList


setAll(T[] array, IntFunction<? extends T> generator), i -> ? extends T
IntStream.range(startInclusive, endExclusive)
      .forEach(i -> array[i] = generator.apply(i));

parallelPrefix(T[] array, BinaryOperator<T> op)

Stream(A)
