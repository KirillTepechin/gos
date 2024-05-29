package sort;

import java.util.Arrays;

public class Main {
    public static void quickSort(int first, int last, int[] a){
        if (first < last)
        {
            int left = first, right = last, middle = a[(left + right) / 2];
            while (left <= right){
                while (a[left]<middle){
                    left++;
                }
                while (a[right]>middle){
                    right--;
                }
                if(left<=right){
                    int t = a[left];
                    a[left] = a[right];
                    a[right] = t;
                    left++;
                    right--;
                }
            }
            quickSort(first, right, a);
            quickSort(left, last, a);
        }
    }
    private static void merge(int first, int middle, int last, int[] a){
        int[] tmpArray = new int[a.length];
        int i = first; int j = middle + 1; int k = first;
        while (i <= middle && j <= last){
            if (a[i] < a[j]){
                tmpArray[k] = a[i];
                i++;
            } else {
                tmpArray[k] = a[j];
                j++;
            }
            k++;
        }
        while (i <= middle){
            tmpArray[k] = a[i];
            i++;
            k++;
        }
        while (j <= last){
            tmpArray[k] = a[j];
            j++;
            k++;
        }
        for (k = first; k <= last; k++){
            a[k] = tmpArray[k];
        }
    }

    public static void mergeSort(int first, int last, int[] a){
        if (first < last){
            int middleInd = (first + last) / 2;
            mergeSort(first, middleInd, a);
            mergeSort(middleInd + 1, last, a);
            merge(first, middleInd, last, a);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 10, 5, 2, 15, 20, 3};
        quickSort(0, arr.length - 1, arr);
        //mergeSort(0, arr.length - 1, arr);
        System.out.println(Arrays.toString(arr));
    }
}
