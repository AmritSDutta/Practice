import java.util.*;
import java.util.concurrent.TimeUnit;

public class Subsets {

    public static void main(String[] args) {
        Subsets ss = new Subsets();
        long start = System.nanoTime();
        System.out.println(ss.subsets(new int[]{1, 9, 8, 3, -1, 0}));
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMicros(end - start));
    }

    public Set<Set<Integer>> subsets(int[] nums) {
        Set<Set<Integer>> response = new HashSet<>();
        if (nums == null || nums.length < 0) return Collections.emptySet();

        //trivial case.
        response.add(Collections.emptySet());

        if (nums.length == 1) {
            response.add(Collections.singleton(nums[0]));
            return response;
        }
        findSubSetRecursively(response, nums, new HashSet<>());
        return response;
    }

    private void findSubSetRecursively(final Set<Set<Integer>> response,
                                       final int[] nums, final Set<Integer> usedAlready) {
        if (usedAlready.size() == nums.length) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (usedAlready.contains(nums[i])) continue;
            Set<Integer> usedAlreadyTemp = new HashSet<>(usedAlready);
            usedAlreadyTemp.add(nums[i]);

            if (!response.contains(usedAlreadyTemp)) {
                response.add(usedAlreadyTemp);
                findSubSetRecursively(response, nums, usedAlreadyTemp);
            }
        }

    }
}
