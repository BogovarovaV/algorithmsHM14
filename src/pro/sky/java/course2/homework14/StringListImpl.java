package pro.sky.java.course2.homework14;

import pro.sky.java.course2.homework14.exceptions.IncorrectIndexException;
import pro.sky.java.course2.homework14.exceptions.ItemDoesNotExistException;
import pro.sky.java.course2.homework14.exceptions.ItemIsNullException;

import java.util.Arrays;

public class StringListImpl implements StringList{

    private String[] array;
    private int size;

    public StringListImpl(int arraySize) {
        this.array = new String[arraySize];
    }

    @Override
    public String add(String item) {
        checkItemIsNotNull(item);
        array[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkItemIsNotNull(item);
        checkIndex(index);
        System.arraycopy(array, index, array, index + 1, array.length - index);
        array[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkItemIsNotNull(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkItemIsNotNull(item);
        checkItemExists(item);
        checkIndex(indexOf(item));
        System.arraycopy(array, indexOf(item) + 1, array, indexOf(item), array.length - 1);
        array[size--] = null;
        return item;
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String item = get(index);
        System.arraycopy(array, indexOf(item) + 1, array, indexOf(item), array.length - 1);
        array[size--] = null;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        checkItemIsNotNull(item);
        for (int i = 0; i < size; i++) {
            if(item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItemIsNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if(item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if(otherList == null || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(otherList.get(i))) {
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
        array = new String[size];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private void checkItemIsNotNull(String item) {
        if (item == null) {
            throw new ItemIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
    }

    private void checkItemExists(String item) {
        if (!contains(item)) {
            throw new ItemDoesNotExistException();
        }
    }
}
