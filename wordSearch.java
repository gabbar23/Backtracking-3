class Solution {
    private int[][] dirs;
    private boolean result;

    // DFS function to search for the word
    private void dfs(int row, int col, int idx, char[][] board, String word) {
        // If the word is found, mark result as true and return
        if (idx == word.length()) {
            result = true;
            return;
        }

        // Boundary check and character match check
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(idx) || result) {
            return;
        }

        // Logic to mark the cell as visited
        char temp = board[row][col];
        board[row][col] = '#';

        // Explore all 4 possible directions
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            dfs(newRow, newCol, idx + 1, board, word);
        }

        // Backtrack and unmark the cell
        board[row][col] = temp;
    }

    public boolean exist(char[][] board, String word) {
        // Define the 4 possible movement directions
        this.dirs = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        this.result = false;

        // Start DFS from each cell that matches the first character of the word
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == word.charAt(0)) {
                    dfs(row, col, 0, board, word);
                    if (result) return true; // Early termination if the word is found
                }
            }
        }
        return result;
    }
}
