Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

Method 1:
class Solution {
    public int integerReplacement(int n) {
        if (n == Integer.MAX_VALUE){ //n = 2^31 -1
            return 32;
        }
        int count = 0;
        while (n > 1){
            if (n % 2 == 0){
                n /= 2;
            }else{
                if ((n + 1) % 4 == 0 && n != 3){
                    n++;
                }else{
                    n--;
                }
            }
            count++;
        }
        return count;
    }
}


Method: DP TLE
class Solution {
    public int integerReplacement(int n) {
        if (n == 1){
            return 0;
        }
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++){
            if (i % 2 == 0){
                dp[i] = dp[i/2] + 1;
            }else{
                dp[i] = Math.min(dp[i-1], dp[(i+1)/2] + 1) + 1;
            }
        }
        return dp[n];
    }
}

https://leetcode.com/problems/integer-replacement/discuss/87928/Java-12-line-4(5)ms-iterative-solution-with-explanations.-No-other-data-structures.
