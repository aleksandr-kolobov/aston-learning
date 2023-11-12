package main.java;

import java.util.Comparator;

public class CustomArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_LIST = {};
    private Object[] dataArray;
    private int size;



  /*
 - add(int index, E element)
 - addAll(Collection<? extends E> c)
 - clear()
 - get(int index)
 - isEmpty()
 - remove(int index)
 - remove(Object o)
 - sort(Comparator<? super E> c)
  */


    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> comparator) {
        quickSort((T[]) dataArray, 0, size - 1, comparator);
    }

    private void quickSort(T[] array, int begin, int end, Comparator<? super T> comparator) {
        if (begin > end) {
            return;
        }
        int leftCursor = begin;
        int rightCursor = end;
        T pivot = array[(leftCursor + rightCursor) / 2];

        do {
            while (comparator.compare(array[leftCursor], pivot) < 0) {
                leftCursor++;
            }
            while (comparator.compare(array[rightCursor], pivot) > 0) {
                rightCursor--;
            }
            if (leftCursor <= rightCursor) {
                if (leftCursor < rightCursor) {
                    T tmp = array[leftCursor];
                    array[leftCursor] = array[rightCursor];
                    array[rightCursor] = tmp;
                }
                leftCursor++;
                rightCursor--;
            }
        } while (leftCursor <= rightCursor);

        if (leftCursor < end) {
            quickSort(array, leftCursor, end, comparator);
        }
        if (begin < rightCursor) {
            quickSort(array, begin, rightCursor, comparator);
        }
    }
}
