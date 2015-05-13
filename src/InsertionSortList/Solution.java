package InsertionSortList;

public class Solution {
    public ListNode insertionSortList(ListNode head) {
        //ListNode转换为int[]
        ListNode node = head;
        int N = 0;
        while (node != null) {
            N += 1;
            node = node.next;
        }
        int[] a = new int[N];
        node = head;
        int i = 0;
        while (node != null) {
            a[i] = node.val;
            node = node.next;
            i += 1;
        }
        //insert sort
        int temp;
        for (int j = 1; j < N; j++) {
            for (int k = j; k > 0 && a[k] < a[k - 1]; k--) {
                temp = a[k];
                a[k] = a[k - 1];
                a[k - 1] = temp;
            }
        }
        //int[] 转换为ListNode
        if (a.length == 0) return null;
        head = new ListNode(a[0]);
        node = head;
        for (int j = 1; j < N; j++) {
            node.next = new ListNode(a[j]);
            node = node.next;
        }
        return head;
    }

    public ListNode insertionSortList2(ListNode head) {
        ListNodeD headD = ListNodeD.toListNodeD(head);
        return null;
    }

    public static void main(String[] args) {
        ListNode h1 = Solution.createListNode(new int[]{});
        ListNode h2 = Solution.createListNode(new int[]{1, 3, 2, 55, 99, 232, 9});
        ListNode h3 = Solution.createListNode(new int[]{-2, -4, 5, -9, 99, 45, 29, 102});
        Solution s = new Solution();
        s.insertionSortList(h1);
        System.out.println(s.insertionSortList(h2).toString());
        System.out.println(s.insertionSortList(h3).toString());

    }

    private static ListNode createListNode(int[] a) {
        ListNode head = null;
        ListNode node = null;
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                head = new ListNode(a[0]);
                node = head;
            }
            node.next = new ListNode(a[i]);
            node = node.next;
        }
        return head;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){val=x;}

    @Override
    public String toString(){
        if(this==null)return "";
        String s="";
        ListNode node=this;
        do{
            s+=node.val+">>";
            node=node.next;
        }while(node!=null);
        return s;
    }
}
class ListNodeD {
    int val;
    ListNodeD next;
    ListNodeD pre;

    public ListNodeD() {
    }
    public static ListNodeD toListNodeD(ListNode head) {
        if (head == null) return null;
        ListNodeD headD = new ListNodeD();
        ListNodeD pre = null;
        do {
            headD.val = head.val;
            headD.next = toListNodeD(head.next);
            headD.pre = pre;
            head = head.next;
            pre = toListNodeD(head);
        } while (head != null);
        return headD;
    }

}
