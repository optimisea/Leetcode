Given a string and an integer k, you need to reverse the first k characters for every 2k 
characters counting from the start of the string. If there are less than k characters left, 
reverse all of them. If there are less than 2k but greater than or equal to k characters, 
then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

class Solution {
    public String reverseStr(String s, int k) {
        char[] charArray = s.toCharArray();
        for (int start = 0; start < s.length(); start += 2*k){
            int i = start;
            int j = Math.min(i + k - 1, s.length() - 1);
            while (i < j){
                char temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
                i++;
                j--;
            }
        }
        return new String(charArray);
    }
}
