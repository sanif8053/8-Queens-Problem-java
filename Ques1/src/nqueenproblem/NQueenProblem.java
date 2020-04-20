
package nqueenproblem;
import java.util.*;

/**
 *
 * @author Naqib
 */
public class NQueenProblem {

    /**
     * @param args the command line arguments
     */
    
    //Checking for clash
    static Boolean checkForClash(int[][] matrix, int row, int column, int n) {  
            int i, j;  
            for (i = 0; i < row; i++) {  
                if (matrix[i][column] == 1) 
                {
                    return false;
                }  
            }  
            for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {  
                if (matrix[i][j] == 1) 
                {
                    return false;
                }  
            }  
            for (i = row, j = column; i >= 0 && j < n; i--, j++) {  
                if (matrix[i][j] == 1) 
                {
                    return false;
                }  
            }  
            return true;  
        }  
    
    public static boolean CSP(int[][] matrix, int row, int n){
        if(row >= n) return true;
            for(int i=0; i< n; i++)
            {
                if(checkForClash(matrix, row, i, n))
                {
                    matrix[row][i]= 1;
                    if(CSP(matrix, row+1, n))
                    { 
                        return true;
                    }
                    matrix[row][i]= 0;
                }
            }
        return false;
    }
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        Scanner obj= new Scanner(System.in);
        int n=0;
        System.out.println("Enter number of queens to be placed");
        n = obj.nextInt();
        int [][] matrix= new int [n][n];
        CSP(matrix, 0, n);//Building board
       
        //Printing board
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}
