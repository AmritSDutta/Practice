import java.util.*;

class PermutationOfArrayElements {

    public static void main(String args[])
    {
        int[] ar = {1,2,3,4};
        permute(ar,null);
    }

    static void permute(int[] nums,LinkedList<Integer> tillNow)
    {
       if(nums.length == 1)
        {
            tillNow.add(nums[0]);
            System.out.println(tillNow);
            return;
        }
        
        if(tillNow == null)
            tillNow= new LinkedList<>();

        for (int i:nums)
        {
            LinkedList<Integer> tillNowTemp = (LinkedList<Integer>) tillNow.clone();
            tillNowTemp.add(i);
            int[] restOfnums = Arrays.stream(nums).filter(n -> n !=i).toArray();
            permute(restOfnums,tillNowTemp);
        }
    }
}
