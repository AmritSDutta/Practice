public class LinkListPalindromeCheckRecursive {
    static LLNode HEAD;
    final static LLNode SUCCESS = new LLNode(9999);

    public static void main(String[] args) {
        HEAD = new LLNode(85);
        HEAD.next = new LLNode(15);
        HEAD.next.next = new LLNode(6);
        HEAD.next.next.next = new LLNode(15);
        HEAD.next.next.next.next = new LLNode(85);


        System.out.println("Given Linked list : ");
        printList(HEAD);
        checkPlaindrom(HEAD);
    }

    static LLNode checkPlaindrom(final LLNode root) {
        LLNode nodeToReturn = null;
        if (root != null) {
            LLNode myComparableNode;
            if (root.next != null)
                myComparableNode = checkPlaindrom(root.next);
            else
                myComparableNode = HEAD;


            if (myComparableNode != null && root.data == myComparableNode.data) {
                if (myComparableNode.next != null)
                    nodeToReturn = myComparableNode.next;
                else {
                    nodeToReturn = SUCCESS;
                }
            }
        }

        if (nodeToReturn == null && root.equals(HEAD)) System.out.println("It's not palindrom");
        if (nodeToReturn != null && nodeToReturn.equals(SUCCESS)) System.out.println("It's palindrom");
        return nodeToReturn;
    }

    static void printList(LLNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println("\n\n");
    }
}

class LLNode {

    int data;
    LLNode next;

    LLNode(int d) {
        data = d;
        next = null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LLNode)) return false;
        LLNode node = (LLNode) o;
        return data == node.data;
    }
}
