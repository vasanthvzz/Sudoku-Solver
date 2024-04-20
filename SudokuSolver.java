import java.util.Arrays;

public class SudokuSolver{


    // String[][] matrix= {
    // {"5","3",".",".","7",".",".",".","."},
    // {"6",".",".","1","9","5",".",".","."},
    // {".","9","8",".",".",".",".","6","."},
    // {"8",".",".",".","6",".",".",".","3"},{
    // "4",".",".","8",".","3",".",".","1"},
    // {"7",".",".",".","2",".",".",".","6"},
    // {".","6",".",".",".",".","2","8","."},
    // {".",".",".","4","1","9",".",".","5"},
    // {".",".",".",".","8",".",".","7","9"}
    // };

    // String[][] matrix = {
    //     {"1","2",".","3",".","4",".","5","6"},
    //     {"7",".",".",".",".","6",".",".","1"},
    //     {".",".",".",".",".",".",".",".","."},
    //     {".","8",".","4",".","9",".","2","."},
    //     {".",".",".",".","6",".",".",".","."},
    //     {".","3",".","5",".","1",".","8","."},
    //     {".",".",".",".",".",".",".",".","."},
    //     {"9",".",".","2",".",".",".",".","8"},
    //     {"8","4",".","6",".","7",".","1","9"}
    //     };

    String[][] matrix = {
        {".",".","3","."},
        {".",".","1","."},
        {".",".",".","1"},
        {"3",".","2","."}
    };
    int gridOffset = (int)Math.sqrt(matrix.length);


    public static void main(String[] args) {
        SudokuSolver sudokuSolver =new SudokuSolver();
        sudokuSolver.init();
    }


    public void init(){
        if(solveSudoku(matrix,0,0)){
            printArray();
        }else{
            System.out.println("Solution not found");
        }
        

    }

    public void printArray(){
        for(String[] arr : matrix){
            System.out.println(Arrays.toString(arr));
        }
    }


    private boolean solveSudoku(String[][] matrix, int row,int col) {
        // printArray();
        // System.out.println();
        if(row == matrix.length){
            return true;
        }
        if(col == matrix.length){
            return solveSudoku(matrix, row+1, 0);
        }
        if(!matrix[row][col].equals(".")){
            return solveSudoku(matrix, row, col+1);
        }
        for(int i=1;i<=matrix.length;i++){
            String str =""+i;
            if(isValid(str, row, col)){
                matrix[row][col] = str;
                if(solveSudoku(matrix, row, col+1)){
                    return true;
                }
                matrix[row][col] = ".";
            }  
        }
        return false;

    }

    private boolean isValid(String s,int r,int c){
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][c].equals(s)
             || matrix[r][i].equals(s) ){
                return false;
             }
        }
        int rowStart = (r/gridOffset)*gridOffset;
        int colStart = (c/gridOffset)*gridOffset;
        for(int i =rowStart;i<rowStart+gridOffset;i++){
            for(int j= colStart;j<colStart+gridOffset;j++){
                if(matrix[i][j].equals(s)){
                    return false;
                }
            }
        }
        return true;
    }
}


