import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {

    static Map<Integer,Integer> memoization= new HashMap<>();
    static int globalMaxLength=0;

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{11,1,-1,2,3,-9,5}));
    }


    public  static int lengthOfLIS(int[] nums) {
        System.out.println(findLongestIncreasingSubSequence(0,nums));
        System.out.println("result : "+globalMaxLength);

        return globalMaxLength;
    }
    static int findLongestIncreasingSubSequence(int index,int[] arr)
    {
        if(memoization.get(index) != null)
            return memoization.get(index);

        int maxLengthForThisIndex=1;
        for(int j=index+1; j< arr.length;j++)
        {
            int localMaxLength=findLongestIncreasingSubSequence(j,arr);
            if(arr[index] < arr[j])
            {
                localMaxLength = 1+ localMaxLength;
                if(localMaxLength > maxLengthForThisIndex)
                    maxLengthForThisIndex=localMaxLength;
            }
        }

        memoization.put(index,maxLengthForThisIndex);

        if(globalMaxLength < maxLengthForThisIndex)
            globalMaxLength= maxLengthForThisIndex;
        return maxLengthForThisIndex;
    }
}
