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
        if(head==null)return null;
        ListNodeD headD = ListNodeD.toListNodeD(head,null);
        ListNodeD node=headD.next;
        int temp;
        while (node!=null){
            ListNodeD node2=node;
            while(node2!=null){
                if(node2.pre!=null&&node2.val<node2.pre.val){
                    temp=node2.val;
                    node2.val=node2.pre.val;
                    node2.pre.val=temp;
                }
                node2=node2.pre;
            }
            node=node.next;
        }
        return ListNodeD.toListNode(headD);
    }

    public static void main(String[] args) {
        ListNode h1 = Solution.createListNode(new int[]{});
        ListNode h2 = Solution.createListNode(new int[]{1, 3, 2, 55, 99, 232, 9});
        ListNode h3 = Solution.createListNode(new int[]{-2, -4, 5, -9, 99, 45, 29, 102});
        Solution s = new Solution();
        s.insertionSortList(h1);
        System.out.println(s.insertionSortList(h2).toString());
        System.out.println(s.insertionSortList(h3).toString());
        s.insertionSortList2(h1);
        System.out.println(s.insertionSortList2(h2).toString());
        System.out.println(s.insertionSortList2(h3).toString());

    }

    private static ListNode createListNode(int[] a) {
        ListNode head = null;
        ListNode node = null;
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                head = new ListNode(a[0]);
                node = head;
                continue;
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

/**
 * 双向链表
 */
class ListNodeD {
    int val;
    ListNodeD next;
    ListNodeD pre;

    public ListNodeD(int val) {
        this.val=val;
    }
    //单向链表转换为双向链表
    public static ListNodeD toListNodeD(ListNode head,ListNodeD pre) {
        if(head==null)return null;
        ListNodeD headD=new ListNodeD(head.val);
        headD.pre=pre;
        headD.next=toListNodeD(head.next,headD);
        return headD;
    }
    //双向链表转换为单向链表
    public static ListNode toListNode(ListNodeD headD){
        if(headD==null)return null;
        ListNode head=new ListNode(headD.val);
        head.next=toListNode(headD.next);
        return head;
    }
}
