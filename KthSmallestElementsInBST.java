/**

Tested in Leetcode website , runtime top 100%.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class KthSmallestElementsInBST {
    int finalResult=0;
    public int kthSmallest(TreeNode root, int k) {
        //System.out.println("KthSmallest : ( "+ k + " )");

        if(k <= 0)
            System.out.println("Kth should start from 1");

        if (root != null) {
            TreeNode start = root;
            printKS(start, k);
            //System.out.println();
        } else {
            //System.out.println("Empty Tree");
        }
        return finalResult;
    }
    private int printKS(TreeNode node, int remaining) {
        if (node != null && remaining > 0) {
            int result = printKS(node.left, remaining);
            if (result != 0) {
                int kth = result - 1;
                if (kth == 0) {
                    finalResult= node.val;
                    //System.out.println(node.val);
                    return kth;
                }
                result = printKS(node.right, kth);
                return result;
            } else
                return 0;
        }
        return remaining;
    }
    
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
