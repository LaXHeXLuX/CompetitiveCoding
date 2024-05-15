import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class ArrayFunctions {

    public static <T> boolean contains(T el, T[] arr) {
        for (T element : arr) {
            if (el.equals(element)) {
                return true;
            }
        }
        return false;
    }
    public static <T> boolean contains(T el, T arr) {
        return contains((Object) el, Converter.toWrapperArray(arr));
    }
    public static <T> boolean sortedContains(T el, T[] arr) {
        if (arr.length == 0) return false;
        if (arr.length == 1) return arr[0] == el;
        int lowIndex = 0;
        int highIndex = arr.length-1;
        while (lowIndex != highIndex) {
            int middleIndex = (lowIndex + highIndex)/2;
            int compareResult = Generifier.compareTo(arr[middleIndex], el);
            if (compareResult < 0) {
                lowIndex = middleIndex+1;
            }
            else if (compareResult > 0) {
                highIndex = middleIndex;
            }
            else return true;
        }
        return arr[lowIndex].equals(el);
    }
    public static <T> boolean sortedContains(T el, T arr) {
        return sortedContains((Object) el, Converter.toWrapperArray(arr));
    }
    public static <T> T switchElement(T arr, Object oldElement, Object newElement, boolean onlyFirst) {
        try {
            oldElement = Converter.convertNumberTo(arr.getClass().getComponentType(), (Number) oldElement);
            newElement = Converter.convertNumberTo(arr.getClass().getComponentType(), (Number) newElement);
        } catch (Exception ignored) {}
        return Converter.toPrimitiveArray(ArrayFunctions.switchElement(Converter.toWrapperArray(arr), oldElement, newElement, onlyFirst));
    }
    public static <T> T switchElement(T arr, Object oldElement, Object newElement) {
        return switchElement(arr, oldElement, newElement, false);
    }
    public static <T> T switchFirstElement(T arr, Object oldElement, Object newElement) {
        return switchElement(arr, oldElement, newElement, true);
    }
    public static <T> T[] switchElement(T[] arr, T oldElement, T newElement) {
        return switchElement(arr, oldElement, newElement, false);
    }
    public static <T> T[] switchFirstElement(T[] arr, T oldElement, T newElement) {
        return switchElement(arr, oldElement, newElement, true);
    }
    public static <T> T[] switchElement(T[] arr, T oldElement, T newElement, boolean onlyFirst) {
        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        System.arraycopy(arr, 0, newArr, 0, newArr.length);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(oldElement)) {
                newArr[i] = newElement;
                if (onlyFirst) return newArr;
            }
        }
        return newArr;
    }
    public static <T> T mergeSort(T arr) {
        return mergeSort(arr, (Comparator<T>) Comparator.naturalOrder());
    }
    public static <T> T mergeSort(T arr, Comparator<T> comparator) {
        return Converter.toPrimitiveArray(mergeSort(Converter.toWrapperArray(arr), comparator));
    }
    public static <T> T[] mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1) return arr;
        T[] arr1 = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length/2);
        T[] arr2 = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length - arr.length/2);
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
        arr1 = mergeSort(arr1, comparator);
        arr2 = mergeSort(arr2, comparator);
        T[] sorted = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (index2 == arr2.length || index1 < arr1.length && comparator.compare(arr1[index1], arr2[index2]) <= 0) {
                sorted[i] = arr1[index1];
                index1++;
            } else {
                sorted[i] = arr2[index2];
                index2++;
            }
        }
        return sorted;
    }
    public static <T> T[] mergeSort(T[] arr) {
        return mergeSort(arr, (Comparator<T>) Comparator.naturalOrder());
    }
    public static <T> T reverseArray(T arr) {
        return Converter.toPrimitiveArray(reverseArray(Converter.toWrapperArray(arr)));
    }
    public static <T> T[] reverseArray(T[] arr) {
        T[] reversed = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        for (int i = 0; i < arr.length; i++) {
            reversed[arr.length - i - 1] = arr[i];
        }
        return reversed;
    }
    public static <T> T removeIndex(T arr, int index) {
        return Converter.toPrimitiveArray(removeIndex(Converter.toWrapperArray(arr), index));
    }
    public static <T> T[] removeIndex(T[] arr, int index) {
        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length-1);
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index+1, newArr, index, newArr.length-index);
        return newArr;
    }
    public static <T> T subArray(T arr, int startIndex, int endIndex) {
        return Converter.toPrimitiveArray(subArray(Converter.toWrapperArray(arr), startIndex, endIndex));
    }
    public static <T> T[] subArray(T[] arr, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= arr.length) {
            throw new RuntimeException("invalid parameters: " + Arrays.toString(arr) + ", " + startIndex + ", " + endIndex);
        }
        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), endIndex-startIndex+1);
        System.arraycopy(arr, startIndex, newArr, 0, newArr.length);
        return newArr;
    }
    public static <T> T concatenate(T array1, T array2) {
        return Converter.toPrimitiveArray(concatenate(Converter.toWrapperArray(array1), Converter.toWrapperArray(array2)));
    }
    public static <T> T[] concatenate(T[] array1, T[] array2) {
        int length = array1.length + array2.length;
        T[] result = Arrays.copyOf(array1, length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
    public static <T> T commonElements(T arr1, T arr2) {
        T[] wrapperArr1 = Converter.toWrapperArray(arr1);
        T[] wrapperArr2 = Converter.toWrapperArray(arr2);
        return Converter.toPrimitiveArray(commonElements(wrapperArr1, wrapperArr2));
    }
    public static <T> T[] commonElements(T[] arr1, T[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return (T[]) Array.newInstance(arr1.getClass().getComponentType(), 0);
        }
        List<T> commonElements = new ArrayList<>();
        boolean[] taken = new boolean[arr2.length];
        for (T t : arr1) {
            for (int i = 0; i < arr2.length; i++) {
                if (taken[i]) continue;
                if (t.equals(arr2[i])) {
                    commonElements.add(t);
                    taken[i] = true;
                    break;
                }
            }
        }
        return Converter.toWrapperArray(Converter.listToArr(commonElements, arr1.getClass().getComponentType()));
    }
}
