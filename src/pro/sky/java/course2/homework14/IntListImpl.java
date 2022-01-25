package pro.sky.java.course2.homework14;

import pro.sky.java.course2.homework14.exceptions.ArrayIsFullException;
import pro.sky.java.course2.homework14.exceptions.IncorrectIndexException;
import pro.sky.java.course2.homework14.exceptions.ItemDoesNotExistException;
import pro.sky.java.course2.homework14.exceptions.ItemIsNullException;

import java.util.Arrays;
import java.util.Objects;

public class IntListImpl implements IntList{

    private Integer[] intArray;
    private int size;

    public IntListImpl(int arraySize) {
        this.intArray = new Integer[arraySize];
    }

    // Переписанные методы из прошлой домашки
    @Override
    public Integer add(Integer item) {
        checkItemIsNotNull(item);
        checkArrayIsNotFull(intArray);
        intArray[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItemIsNotNull(item);
        checkIndex(index);
        System.arraycopy(intArray, index, intArray, index + 1, intArray.length - index);
        intArray[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItemIsNotNull(item);
        checkIndex(index);
        intArray[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItemIsNotNull(item);
        checkItemExists(item);
        checkIndex(indexOf(item));
        System.arraycopy(intArray, indexOf(item) + 1, intArray, indexOf(item), intArray.length - 1);
        intArray[size--] = 0;
        return item;
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = get(index);
        System.arraycopy(intArray, indexOf(item) + 1, intArray, indexOf(item), intArray.length - 1);
        intArray[size--] = 0;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        sortInsertion(intArray);
        return binarySearch(intArray, item);
    }

    @Override
    public int indexOf(Integer item) {
        checkItemIsNotNull(item);
        for (int i = 0; i < size; i++) {
            if(item.equals(intArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItemIsNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if(item.equals(intArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int get(int index) {
        checkIndex(index);
        return intArray[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if(otherList == null || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (get(i) != otherList.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        intArray = new Integer[size];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(intArray, intArray.length);
    }

    // Проверки
    private void checkItemIsNotNull(Integer item) {
        if (item == null) {
            throw new ItemIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
    }

    private void checkItemExists(Integer item) {
        if (!contains(item)) {
            throw new ItemDoesNotExistException();
        }
    }

    private void checkArrayIsNotFull(Integer[] IntArray) {
        if (size >= IntArray.length) {
            throw new ArrayIsFullException();
        }
    }

    // Самая быстрая сортировка
    private static void sortInsertion(Integer[] intArray) {
        for (int i = 1; i < intArray.length; i++) {
            Integer temp = intArray[i];
            int j = i;
            while (j > 0 && intArray[j - 1] >= temp) {
                intArray[j] = intArray[j - 1];
                j--;
            }
            intArray[j] = temp;
        }
    }

    public static boolean binarySearch(Integer[] intArray, Integer item) {
        int min = 0;
        int max = intArray.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (Objects.equals(item, intArray[mid])) {
                return true;
            }
            if (item < intArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
