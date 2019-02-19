Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1
Example 2:

Input: [0,2,1,0]
Output: 1

Method 1:
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid+1]){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (A[start] < A[end]){
            return end;
        }
        return start;
    }
}

class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] > A[mid-1] && A[mid] > A[mid+1]){
                return mid;
            }else if (A[mid] > A[mid-1] && A[mid] < A[mid+1]){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (A[start] > A[end]){
            return start;
        }
        return end;
        
    }
}

Method 2:
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 0; i < A.length - 1; i++){
            if (A[i] > A[i+1]){
                return i;
            }
        }
        return 0;
    }
}
