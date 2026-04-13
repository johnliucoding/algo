package sort.production;

/**
 * 1 divide the elements of the array into groups
 * 2 each group is called a run
 * 3 sort each run individually with the help of insertion sort
 * 4 merge these runs two-by-two with the help of the merge method that merge sort uses
 * the merge method performs very well when the size of the sub-array is a power of two, and that is
 * why usually the run size is picked between 32 and 64
 */
public class TimSort {
}
