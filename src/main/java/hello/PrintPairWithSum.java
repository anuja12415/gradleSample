package hello;

import java.util.HashSet;

//This program will print all unique pairs with the given sum

public class PrintPairWithSum {

    static void printpairs(int arr[],int sum)
    {
        HashSet<Integer> s = new HashSet<Integer>();
        for (int i=0; i<arr.length; ++i)
        {
            int temp = sum-arr[i];
            if(s.contains(arr[i]) && s.contains(temp)){
                continue;
            }

            // checking for condition
            if (s.contains(temp))
            {
                System.out.println("Pair with given sum " +
                        sum + " is (" + arr[i] +
                        ", "+temp+")");
            }
            s.add(arr[i]);
        }
    }

    // Main to test the above function
    public static void main (String[] args)
    {
        int A[] = {1, 5, 4, 5, 45, 6, 11, 10, 8, 11};
        int n = 16;
        printpairs(A, n);
    }
}
