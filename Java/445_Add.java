You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Method 1: reverse
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int carry = 0;
        int sum = 0;
        for (ListNode i = l1, j = l2; i != null || j != null;){
            sum = carry;
            sum += (i != null) ? i.val : 0;
            sum += (j != null) ? j.val: 0;
            
            ListNode node = new ListNode (sum % 10);
            tail.next = node;
            tail = node;
            carry = sum / 10;
            i = (i != null) ? i.next : null;
            j = (j != null) ? j.next : null;
        }
        if (carry != 0){
            tail.next = new ListNode(carry);
        }
        return reverse(dummy.next);
    }
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}
