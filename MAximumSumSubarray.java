class MAximumSumSubarray {
    public int maxSubArray(int[] nums) {
int maxTillNow=Integer.MIN_VALUE;
        int maxsubset=0;
        for(int i:nums)
        {
           maxsubset=maxsubset+i;
           if(maxsubset > maxTillNow)
               maxTillNow=maxsubset;
           if(maxsubset < 0)
               maxsubset=0;
        }
        return maxTillNow;
    }
}
