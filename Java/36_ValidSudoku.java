Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Time complexity: O(N * N)
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++){
            if (!isValid(board, i, 0, i, 8)){
                return false;
            }
            if (!isValid(board, 0, i, 8, i)){
                return false;
            }
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (!isValid(board, 3*i, 3*j, 3*i+2, 3*j+2)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int x1, int y1, int x2, int y2){
        Set<Character> set = new HashSet<>();
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                if (board[i][j] != '.'){
                    if (!set.add(board[i][j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
