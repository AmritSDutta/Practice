import java.util.Arrays;

public class MaximumProductSubarray {

    public static void main(String args[])
    {
        int arr[] = {2,-3,4,5,-2,-2,0,9,8};
        printArray(arr);
        System.out.println(getMaximumProduct(arr));

        int arr1[] = {-2,-2,-2,-2,-2};
        printArray(arr1);
        System.out.println(getMaximumProduct(arr1));

        int arr2[] = {-2,0,-2,0,-2};
        printArray(arr2);
        System.out.println(getMaximumProduct(arr2));

        int arr3[] = {-2};
        printArray(arr3);
        System.out.println(getMaximumProduct(arr3));
    }

    public static int getMaximumProduct(int[] arr)
    {
        int maxPartial=1;
        int maxProductTillNow=-9999;
        int maxNegetiveProduct=1;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==0)
            {
                maxPartial=1;
                maxNegetiveProduct=1;
                if(maxProductTillNow < 0)
                    maxProductTillNow=0;
            }
            else if(arr[i]< 0)
            {
                maxNegetiveProduct = maxNegetiveProduct* arr[i];
                if(maxNegetiveProduct > maxPartial)
                {
                    maxPartial=maxNegetiveProduct;

                    if(maxPartial > maxProductTillNow)
                        maxProductTillNow=maxPartial;
                }

            }
            else
            {
                maxPartial=maxPartial*arr[i];
                maxNegetiveProduct = maxNegetiveProduct* arr[i];
                if(maxPartial > maxProductTillNow)
                    maxProductTillNow=maxPartial;
            }


            if(i==0 && maxNegetiveProduct != 1)
                maxProductTillNow=maxNegetiveProduct;
        }
        return maxProductTillNow;
    }

    static void printArray(int arr[])
    {
        System.out.print("\n\n"+Arrays.toString(arr) + " : \n");
    }
}
