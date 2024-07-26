class Solution {
    List<List<String>> result;

    // Main function to solve N-Queens problem
    public List<List<String>> solveNQueens(int n) {
        this.result = new ArrayList<>();
        boolean[][] grid = new boolean[n][n]; // Create an n x n grid initialized to false
        backtrack(0, grid); // Start backtracking from the first row
        return result; // Return the final list of solutions
    }

    // Backtracking function to place queens on the board
    private void backtrack(int row, boolean[][] grid) {
        // If we've reached the end of the grid, a solution is found
        if (row == grid.length) {
            List<String> sublist = new ArrayList<>();
            // Construct the solution in the required format
            for (int i = 0; i < grid.length; i++) {
                StringBuilder str = new StringBuilder();
                for (int c = 0; c < grid.length; c++) {
                    if (grid[i][c]) str.append("Q");
                    else str.append(".");
                }
                sublist.add(str.toString());
            }
            result.add(sublist); // Add this solution to the result list
            return;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < grid.length; col++) {
            if (isSafe(row, col, grid)) {
                // Place the queen
                grid[row][col] = true;
                // Recurse to the next row
                backtrack(row + 1, grid);
                // Backtrack by removing the queen
                grid[row][col] = false;
            }
        }
    }

    // Function to check if it's safe to place a queen at grid[row][col]
    private boolean isSafe(int row, int col, boolean[][] grid) {
        // Check the column above
        for (int r = row; r >= 0; r--) {
            if (grid[r][col]) return false;
        }

        // Check the upper left diagonal
        int r = row;
        int c = col;
        while (r >= 0 && c >= 0) {
            if (grid[r][c]) return false;
            r--;
            c--;
        }

        // Check the upper right diagonal
        r = row;
        c = col;
        while (r >= 0 && c < grid.length) {
            if (grid[r][c]) return false;
            r--;
            c++;
        }

        // If no threats, it's safe to place the queen here
        return true;
    }
}
