package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ALClass {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void sortTest() {
        int arr[] = new int[]{2, 4, 1, 6, 19, 3};
        quickSort(arr, 0, 5);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("" + arr[i]);
        }
    }

    /**
     * 快排O (nlogn)
     */
    public int[] quickSort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) j--;
            while ((i < j) && (arr[i] < pivot)) i++;
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) arr = quickSort(arr, start, i - 1);
        if (j + 1 < end) arr = quickSort(arr, j + 1, end);
        return (arr);
    }

    /**
     * 冒泡O(n²)
     */
    public void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 2分
     */
    public boolean find2(int target, int[] array) {
        if (array.length > 0) {
            int start = 0, end = array.length - 1;
            int mid = start + end / 2;
            while (mid > 0) {
                if (array[mid] == target) return true;
                else if (array[mid] > target) mid = (mid + start) / 2;
                else if (array[mid] < target) mid = (mid + end) / 2;
            }
        }
        return false;
    }

    /**
     * 二维矩阵查找
     */
    public boolean find(int target, int[][] array) {
        int m = array.length;
        if (m == 0) return false;
        int n = array[0].length;
        if (n == 0) return false;
        int row = 0, column = n - 1;
        while (row < m && column >= 0) {
            if (target == array[row][column]) return true;
            else if (target < array[row][column]) column--;
            else if (target > array[row][column]) row++;
        }
        return false;
    }

    /**
     * 左旋转
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 括号(栈)
     */
    public boolean isValid(String s) {
        if (s.length() > 0) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (cur == '(' || cur == '{' || cur == '[') {
                    stack.push(cur);
                } else if (stack.size() > 0) {
                    char last = stack.pop();
                    if ((cur == ')' && last == '(') ||
                            (cur == ']' && last == '[') ||
                            (cur == '}' && last == '{')) continue;
                    return false;
                } else {
                    return false;
                }
            }
            return stack.size() == 0;
        }
        return true;
    }

    /**
     * 栈的压入
     */
    public boolean IsPopOrder(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && popped[j] == stack.peek()) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 合并两链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(1);
        ListNode ret = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        head.next = l1 == null ? l2 : l1;
        return ret.next;
    }

    /**
     * 回文链表
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;//空或1个
        if (head.next.next == null) {//2个
            return head.val == head.next.val;
        }
        //找中间
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tail = reverse(slow);

        while (head != tail) {
            if (head.val != tail.val) {
                return false;
            }
            if (head.next == tail) break;
            head = head.next;
            tail = tail.next;
        }
        return true;
    }

    /**
     * 链表反转
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 二叉树最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = 1 + maxDepth(root.left);
        int right = 1 + maxDepth(root.right);
        return left > right ? left : right;
    }

    /**
     * 二叉树最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return root.left == null || root.right == null ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * 子树
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        else if (s == null) return false;
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    public boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        else if (s == null || t == null) return false;
        else if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    /**
     * 树镜像
     */
    public TreeNode mirrorTree(TreeNode node) {
        if (node == null) return null;
        if (node.left != null || node.right != null) {
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        mirrorTree(node.left);
        mirrorTree(node.right);
        return node;
    }

    /**
     * 回旋镖
     */
    public boolean isBoomerang(int[][] points) {
        return (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]) !=
                (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
    }

    /**
     * 删排序数组重复项
     */
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        for (j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 两数组求交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> s1 = new HashSet();
        HashSet<Integer> s2 = new HashSet();
        for (int i = 0; i < nums1.length; i++) {
            s1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            s2.add(nums2[i]);
        }
        s1.retainAll(s2);
        int[] ret = new int[s1.size()];
        int i = 0;
        for (int v : s1) {
            ret[i++] = v;
        }
        return ret;
    }

    /**
     * 爬台阶(1-2递归，动归)
     */
//    public int climbStairs(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        int[] dp = new int[n + 1];
//        dp[1] = 1;
//        dp[2] = 2;
//        for (int i = 3; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
//    }
    public int climbStairs(int n) {
        int memo[] = new int[n];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
//        if (memo[i] > 0) {
//            return memo[i];
//        }
//        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
//        return memo[i];
        return climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
    }

    /**
     * 变态爬台阶(动归)
     */
    public int JumpFloorII(int target) {
        return target <= 0 ? 0 : 1 << (target - 1);
    }

    /**
     * 罗马数字
     */
    public int romanToInt(String s) {
        int sum = 0, pre = convert(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int value = convert(s.charAt(i));
            if (pre < value) {
                sum -= pre;
            } else {
                sum += pre;
            }
            pre = value;
        }
        sum += pre;
        return sum;
    }

    private int convert(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * 确定字符互异
     */
    public boolean checkDifferent(String iniString) {
        if (iniString.length() > 256) return false;
        for (int i = 0; i < iniString.length(); i++) {
            for (int j = i + 1; j < iniString.length(); j++) {
                if ((iniString.charAt(i) ^ iniString.charAt(j)) == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * 两数之和（hash法）
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums.length >= 2) {
            HashMap map = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                int c = target - nums[i];
                if (map.containsKey(c)) {
                    return new int[]{(int) map.get(c), i};
                } else map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length >= 3) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) break;//最左值为正数则一定无解
                if (i > 0 && nums[i - 1] == nums[i]) continue; // 去重
                int first = i + 1;
                int last = nums.length - 1;
                while (first < last) {
                    int result = nums[i] + nums[first] + nums[last];
                    if (result == 0) {// 如果可以组队
                        list.add(new ArrayList<>(Arrays.asList(nums[i], nums[first], nums[last])));
                        while (first < last && nums[first] == nums[++first]) ;// 去重
                        while (first < last && nums[last] == nums[--last]) ;// 去重
                    } else if (result < 0) first++;
                    else if (result > 0) last--;
                }
            }
        }
        return list;
    }

    /**
     * 反转string
     */
    public String reverseString(String iniString) {
        char temp;
        int length = iniString.length();
        int mid = length / 2;
        char[] arr = iniString.toCharArray();
        for (int i = 0; i < length; i++) {
            if (i < mid) {
                //exchange
                temp = arr[i];
                arr[i] = arr[length - 1 - i];
                arr[length - 1 - i] = temp;
            } else break;
        }
        return String.valueOf(arr);
    }

    /**
     * 回文数
     */
    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;

    }

    /**
     * 回文串
     */
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (s.charAt(i) == s.charAt(j) ||
                    Character.isLetter(s.charAt(i)) && Character.isLetter(s.charAt(j)) &&
                            (s.charAt(i) == s.charAt(j) - 32 || s.charAt(i) - 32 == s.charAt(j))) {
                i++;
                j--;
                continue;
            } else return false;
        }
        return true;
    }

    /**
     * 多删一个的回文串
     */
    boolean del = false;

    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                if (del) return false;
                else {
                    del = true;
                    return validPalindrome(s.substring(i, j)) || validPalindrome(s.substring(i + 1, j + 1));
                }
            }
        }
        return true;
    }

    /**
     * 最长公共子串
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) break;
            }
            ans = ans.substring(0, j);
//            if (ans.equals("")) return ans;
        }
        return ans;
    }

    /**
     * 1的个数
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    public int NumberOf1_(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 旋转数组
     */
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        while (k-- > 0) {
            int last = nums[length - 1];
            for (int i = 1; i < length; i++) {
                nums[length - i] = nums[length - i - 1];
            }
            nums[0] = last;
        }
    }

    /**
     * 旋转数组最小数（{3,4,5,1,2}为{1,2,3,4,5}）
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) return 0;
        int left = 0;
        int right = array.length - 1;
        int mid = 0;
        while (left < right) {
            if (array[left] < array[right]) return array[left];//特殊
            mid = (left + right) / 2;
            if (array[mid] > array[left]) left = mid + 1;
            else if (array[mid] < array[right]) right = mid;
            else left++;
        }
        return array[left];
    }

    /**
     * 两数组合并
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n >= 0) System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 两个栈实现队列
     */
    class CQueue {
        private Stack stack1, stack2;
        private int size = 0;

        public CQueue() {
            stack1 = new Stack();
            stack2 = new Stack();
        }

        public void appendTail(int value) {
            stack1.push(value);
            size++;
        }

        public int deleteHead() {
            if (size == 0) return -1;
            if (stack2.empty()) {
                while (!stack1.empty()) stack2.push(stack1.pop());
            }
            size--;
            return (int) stack2.pop();
        }
    }

    /**
     * 两个队列实现栈
     */
    class MyStack {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        private int top;

        public void push(int x) {
            q1.add(x);
            top = x;
        }

        public int pop() {
            while (q1.size() > 1) {
                top = q1.remove();
                q2.add(top);
            }
            int pop = q1.remove();//出栈
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return pop;
        }

        public int top() {
            return top;
        }

        public boolean empty() {
            return q1.size() /*+ q2.size()*/ == 0;
        }
    }
}
