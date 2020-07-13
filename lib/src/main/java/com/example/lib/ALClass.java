package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class ALClass {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class ListNode {
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
     * 公共链表首节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int count = 0;
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
            if (l1 == null) {
                count++;
                l1 = headB;
            }
            if (l2 == null) {
                count++;
                l2 = headA;
            }
            if (count > 2) return null;//两轮未找到
        }
        return l1;
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
     * 二叉树子树
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
     * 二叉树子结构
     */
    public boolean isSubStructure(TreeNode node1, TreeNode node2) {
        if (node2 == null || node1 == null) return false;
        if (!isSub(node1, node2))
            return isSubStructure(node1.left, node2) || isSubStructure(node1.right, node2);
        return true;
    }

    public boolean isSub(TreeNode node1, TreeNode node2) {
        if (node2 == null) return true;
        if (node1 == null) return false;
        if (node1.val == node2.val)
            return isSub(node1.left, node2.left) && isSub(node1.right, node2.right);
        else return false;
    }

    /**
     * 二叉树镜像
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
//        if (n == 1) return 1;
//        int[] dp = new int[n];
//        dp[0] = 1;
//        dp[1] = 2;
//        for (int i = 2; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n-1];
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
     * 数组奇数放偶数前
     */
    public int[] exchange(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            while (start < end && nums[start] % 2 == 1) start++;
            while (start < end && nums[end] % 2 == 0) end--;
            if (start < end) {
                int temp = nums[end];
                nums[end] = nums[start];
                nums[start] = temp;
            }
        }
        return nums;
    }

    /**
     * 数组奇数放偶数前（保证序列）
     */
    public void reOrderArray(int[] array) {
        int i = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] % 2 == 1) {
                int tmp = array[j];
//                for (int k = j - 1; k >= i; k--) {
//                    array[k + 1] = array[k];
//                }
                if (j - i >= 0) System.arraycopy(array, i, array, i + 1, j - i);
                array[i++] = tmp;
            }
        }
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
     * 超过一半的数(比排序除2优)
     */
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
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
        int start = 0;
        int end = array.length - 1;
        int mid = 0;
        while (start < end) {
            if (array[start] < array[end]) return array[start];//特殊
            mid = (start + end) / 2;
            if (array[mid] > array[start]) start = mid + 1;
            else if (array[mid] < array[end]) end = mid;
            else start++;
        }
        return array[start];
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


    /**
     * 股票买卖最佳时期
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        //Map<Integer,Integer> map = new HashMap();
        int buy = prices[0];
        int reword = 0;
        //map.put(buy,0);
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            //if (price > buy) {
            //if (map.get(buy) < price - buy) map.put(buy,price - buy);
            if (price - buy > reword) {
                reword = price - buy;
            } else if (price < buy) {
                buy = price;
                //map.put(buy,0);
            }
        }
        //int reword = 0;
        //for (Map.Entry<Integer,Integer> entry:map.entrySet()){
        //    if (reword < entry.getValue()) reword = entry.getValue();
        //}
        return reword;
    }

    /**
     * 股票买卖最佳时期(多区间)
     */
    public int maxProfit1(int[] prices) {
        if (prices.length == 0) return 0;
        int reword = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {//前后比较，计算所有的上升阶段值
                reword += prices[i] - prices[i - 1];
            }
        }
        return reword;
    }

    /**
     * 字符串的排列
     */
    List<String> res = new LinkedList<>();
    char[] sa;

    public String[] permutation(String s) {
        sa = s.toCharArray();
        cal(0);
        return res.toArray(new String[res.size()]);
        // return new ArrayList<String>(res);
    }

    private void cal(int x) {
        if (x + 1 == sa.length) {
            res.add(String.valueOf(sa));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < sa.length; i++) {
            if (set.contains(sa[i])) continue; // 重复，因此剪枝
            set.add(sa[i]);
            swap(i, x); // 交换，将 sa[i] 固定在第 x 位
            cal(x + 1); // 开启固定第 x + 1 位字符
            swap(i, x); // 恢复交换
        }
    }

    private void swap(int a, int b) {
        char tmp = sa[a];
        sa[a] = sa[b];
        sa[b] = tmp;
    }

    /**
     * 移除元素
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int start = 0, end = nums.length;
        while (start < end) {
            if (nums[start] != val) {
                start++;
            } else {
                nums[start] = nums[end - 1];
                end--;
            }
        }
        return end;
    }

    /**
     * 合并二叉树
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * 从上往下打印二叉树，同层左到右
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList();
        if (root == null) return list;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return list;
    }

    /**
     * 对称二叉树（递归）
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return check(root, root);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return check(left.left, right.right) && check(left.right, right.left);
    }

    /**
     * 迭代
     */
    private boolean check2(TreeNode left, TreeNode right) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(left);
        queue.add(right);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null || t1.val != t2.val) return false;//唯一不对称情况
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

    /**
     * 二叉树第k大节点
     */
    int n = 0;
    int des = 0;

    public int kthLargest(TreeNode root, int k) {
        if (k == 0) return 0;
        n = k;
        dfs(root);
        return des;
    }

    private void dfs(TreeNode node) {
        if (node == null || n == 0) return;
        dfs(node.right);
        if (--n == 0) {
            des = node.val;
            return;
        }
        dfs(node.left);
    }

    /**
     * 重新构建二叉树
     */
    //利用原理,先序遍历的第一个节点就是根。在中序遍历中通过根 区分哪些是左子树的，哪些是右子树的
    //左右子树，递归
    HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历
    int[] preorder;//保留的先序遍历

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < preorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recursive(0, 0, inorder.length - 1);
    }

    /**
     * @param pre_root_idx 先序遍历的索引
     * @param in_left_idx  中序遍历的索引
     * @param in_right_idx 中序遍历的索引
     */
    public TreeNode recursive(int pre_root_idx, int in_left_idx, int in_right_idx) {
        //相等就是自己
        if (in_left_idx > in_right_idx) {
            return null;
        }
        //root_idx是在先序里面的
        TreeNode root = new TreeNode(preorder[pre_root_idx]);
        // 有了先序的,再根据先序的，在中序中获 当前根的索引
        int idx = map.get(preorder[pre_root_idx]);

        //左子树的根节点就是 左子树的(前序遍历）第一个，就是+1,左边边界就是left，右边边界是中间区分的idx-1
        root.left = recursive(pre_root_idx + 1, in_left_idx, idx - 1);

        //由根节点在中序遍历的idx 区分成2段,idx 就是根

        //右子树的根，就是右子树（前序遍历）的第一个,就是当前根节点 加上左子树的数量
        // pre_root_idx 当前的根  左子树的长度 = 左子树的左边-右边 (idx-1 - in_left_idx +1) 。最后+1就是右子树的根了
        root.right = recursive(pre_root_idx + (idx - 1 - in_left_idx + 1) + 1, idx + 1, in_right_idx);
        return root;
    }

    /**
     * 矩形覆盖（动归）
     */
    public int RectCover(int target) {
        if (target == 0 || target == 1 || target == 2) return target;
        int a = 1, b = 2, c = 0;
        for (int i = 3; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 打印回型矩阵
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix.length == 0) return null;
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        //int[] ret = new int[matrix.length*matrix[0].length];
        //int c = 0;
        ArrayList<Integer> list = new ArrayList();
        while (true) {
            for (int i = l; i <= r; i++) {// left to right.
                list.add(matrix[t][i]);
            }
            if (++t > b) break;
            for (int i = t; i <= b; i++) {// top to bottom.
                list.add(matrix[i][r]);
            }
            if (l > --r) break;
            for (int i = r; i >= l; i--) {// right to left.
                list.add(matrix[b][i]);
            }
            if (t > --b) break;
            for (int i = b; i >= t; i--) {// bottom to top.
                list.add(matrix[i][l]);
            }
            if (++l > r) break;
        }
        return list;
    }

    /**
     * 两两交换链表
     */
    public ListNode swapParis(ListNode head) {
        /*声明一个哑节点*/
        ListNode dumb = new ListNode(0);
        /*哑节点的下一个节点指向头节点*/
        dumb.next = head;
        /*声明一个引用指向哑节点*/
        ListNode temp = dumb;
        /*如果哑节点的下一个节点和下下一个节点不为空继续转换*/
        while (dumb.next != null && dumb.next.next != null) {
            /*要转换的第一个节点（称为curr）*/
            ListNode curr = dumb.next;
            /*要转换的第二个节点（称为next）*/
            ListNode next = dumb.next.next;
            /*指定curr的下一个是next的下一个*/
            curr.next = next.next;
            /*指定next的下一个是curr*/
            next.next = curr;
            /*指定哑节点的下一个是next*/
            dumb.next = next;
            /*哑节点引用指向哑节点的下下一个节点*/
            dumb = dumb.next.next;
        }
        /*返回哑节点的没改变引用的下一个节点*/
        return temp.next;
    }

    /**
     * strStr
     */
    public int strStr(String str1, String str2) {
        int l = str2.length(), n = str1.length();
        for (int start = 0; start < n - l + 1; ++start) {
            if (str1.substring(start, start + l).equals(str2)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * strStr优化
     */
    public int strStr1(String haystack, String needle) {
        int l = needle.length(), n = haystack.length();
        if (l == 0) return 0;
        int pn = 0;
        while (pn < n - l + 1) {
            //找到第一个与needle匹配的字符
            while (pn < n - l + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;

            //最大匹配子串
            int currLen = 0, pL = 0;
            while (pL < l && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }
            //找到即返回
            if (currLen == l) return pn - l;

            //未找到，回溯
            pn = pn - currLen + 1;
        }
        return -1;
    }
}
