package pro.sky.java.course2.homework14;

import java.util.Arrays;

public class ComparisonOfSorts {

    public static void main(String[] args){

        Integer[] arr1 = generateRandomArray();
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        long start1 = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println(System.currentTimeMillis() - start1);
        long start2 = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println(System.currentTimeMillis() - start2);
        long start3 = System.currentTimeMillis();
        sortInsertion(arr3); // самая быстрая сортировка в данном случае
        System.out.println(System.currentTimeMillis() - start3);
    }

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(Integer[] arr1) {
        for (int i = 0; i < arr1.length - 1; i++) {
            for (int j = 0; j < arr1.length - 1 - i; j++) {
                if (arr1[j] > arr1[j + 1]) {
                    swapElements(arr1, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr2) {
        for (int i = 0; i < arr2.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr2.length; j++) {
                if (arr2[j] < arr2[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr2, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr3) {
        for (int i = 1; i < arr3.length; i++) {
            Integer temp = arr3[i];
            int j = i;
            while (j > 0 && arr3[j - 1] >= temp) {
                arr3[j] = arr3[j - 1];
                j--;
            }
            arr3[j] = temp;
        }
    }
}
