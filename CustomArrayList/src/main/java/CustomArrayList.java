package main.java;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_LIST = {};
    private Object[] dataArray;
    private int size = 0;

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.dataArray = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.dataArray = EMPTY_LIST;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public CustomArrayList() {
        this.dataArray = EMPTY_LIST;
    }

    public CustomArrayList(Collection<? extends T> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            dataArray = Arrays.copyOf(a, size, Object[].class);
        } else {
            dataArray = EMPTY_LIST;
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = dataArray.length;
        if (oldCapacity > 0 || dataArray != EMPTY_LIST) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            return dataArray = Arrays.copyOf(dataArray, newCapacity);
        } else {
            return dataArray = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

  /*
 + add(E element)
 + add(int index, E element)
 + addAll(Collection<? extends E> c)
 + clear()
 + get(int index)
 + size()
 + isEmpty()
 + remove(int index)
 + remove(Object o)
 + sort(Comparator<? super E> c)
  */

    public void add(T e) {
        if (size == dataArray.length) {
            dataArray = grow(size + 1);
        }
        dataArray[size] = e;
        size += 1;
    }

    public void add(int index, T element) {
        checkIndex(index);
        Object[] elementData = this.dataArray;
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size += 1;
    }

    public boolean addAll(Collection<? extends T> collection) {
        Object[] array = collection.toArray();
        int numNew = array.length;
        if (numNew == 0) {
            return false;
        }
        Object[] elementData = this.dataArray;
        if (numNew > (elementData.length - size)) {
            elementData = grow(size + numNew);
        }
        System.arraycopy(array, 0, elementData, size, numNew);
        size += numNew;
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            dataArray[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) dataArray[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void remove(int index) {
        checkIndex(index);
        size -= 1;
        if (index < size) {
            System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        }
        dataArray[size] = null;
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (dataArray[i].equals(o) || (dataArray[i] == null && o == null)) {
                remove(i);
                i--;
            }
        }
    }

    public String toString() {
        return Arrays.toString(dataArray);
    }

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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }
}
