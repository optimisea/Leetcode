Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will 
follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its 
count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1),
and so on.

Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.

class Solution {
    public String countOfAtoms(String formula) {
        Map<String, Integer> res = parse(formula);
        StringBuilder sb = new StringBuilder();
        for (String key : res.keySet()){
            sb.append(key);
            int num = res.get(key);
            if (num > 1){
                sb.append(num);
            }
        }
        return sb.toString();
    }
    private Map<String, Integer> parse(String formula) {
        Map<String, Integer> map = new TreeMap<>();
        int i = 0;
        while (i < formula.length()) {
            if (formula.charAt(i) == '(') {
                int count = 0;
                int j = i;
                for (; j < formula.length(); j++) {//smart way to find the subproblem for brackets
                    if (formula.charAt(j) == '('){
                        count++;
                    }else if (formula.charAt(j) == ')'){
                        count--;
                    }
                    if (count == 0){
                        break;
                    }
                }
                Map<String, Integer> subMap = parse(formula.substring(i + 1, j));
                j++;
                int num = 1;
                int k = j;
                while (k < formula.length() && Character.isDigit(formula.charAt(k))){
                    k++;
                }
                if (k > j) {
                    num = Integer.parseInt(formula.substring(j, k));
                }
                for (String atom : subMap.keySet()) {
                    map.put(atom, subMap.get(atom) * num + map.getOrDefault(atom, 0));
                }
                i = k;
            } else {
                int j = i + 1;
                while (j < formula.length() && Character.isLowerCase(formula.charAt(j))){
                    j++;
                }
                int num = 1;
                int k = j;
                while (k < formula.length() && Character.isDigit(formula.charAt(k))){
                    k++;
                }
                if (k > j) {
                    num = Integer.parseInt(formula.substring(j, k));
                }
                String atom = formula.substring(i, j);
                map.put(atom, num + map.getOrDefault(atom, 0));
                i = k;
            }
        }

        return map;
    }
}
