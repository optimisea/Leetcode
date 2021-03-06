Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?

https://leetcode.com/articles/power-of-three/
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0){
            return false;
        }
        while (n > 1){
            if (n % 3 != 0){
                return false;
            }
            n /= 3;
        }
        return true;
    }
}

