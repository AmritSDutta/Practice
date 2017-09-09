
/* Code is written to be executed for Leetcode site , thus caries a specific format */

import java.util.*;

class PermutationOfArrayElements {

    public static void main(String args[])
    {
        int[] ar = {1,2,3,4};
        permute(ar);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> masterList = new LinkedList<>();
        permute(nums,null,  masterList);

        System.out.println("Size : " +masterList.size());

        System.out.println(masterList);
        return masterList;
    }

    static void permute(int[] nums,LinkedList<Integer> tillNow, List<List<Integer>> masterList)
    {
        if(tillNow == null)
            tillNow= new LinkedList<>();

        if(nums.length == 1)
        {
            tillNow.add(nums[0]);
            masterList.add(tillNow);
            return;
        }

        for (int i:nums)
        {
            LinkedList<Integer> tillNowTemp = (LinkedList<Integer>) tillNow.clone();
            tillNowTemp.add(i);
            int[] restOfnums = Arrays.stream(nums).filter(n -> n !=i).toArray();
            permute(restOfnums,tillNowTemp,  masterList);

        }
    }

}
