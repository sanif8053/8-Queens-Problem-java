/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ques2;
import java.util.*;
/**
 *
 * @author sanif
 */
public class Ques2 {
    
    //function to calculate fitness
   static int fitnessfunc(int [][]popu,int s){
   int [][] board=new int[8][8];
       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               board[i][j]=0;   //0 show empty spaces  
                                // number show their location in the board
           }
       }
       int k=0;
       int x;
       for (int i = 0; i < 8; i++) {
            x=popu[s][i];
            board[x-1][k++]=x;
       }
            printboard(board);
       
       int fitness=0;
       // checking diagonally upward if queen collide
       int q=0;
       int i=0;
       int j=0;
       while(q<8)
       {
           int y=popu[s][q]-1;
           int z=j;
           z++;
           y--;
           while(y>=0 && z<=7){
               if(board[y][z]!=0)fitness++;
               
               y--;
               z++;
               
           }
           j++;
           
       q++;
       }
       // checking diagonally downward if queen collide
        q=0;
        i=0;
        j=0;
       while(q<8)
       {
           int y=popu[s][q]-1;
           
           int z=j;
           z++;
           y++;
           while(z<=7 && y<=7){
               if(board[y][z]!=0)fitness++;
              
               y++;
               z++;
               
           }
           j++;
           
       q++;
       }   
       // checking horizonntally if queen collide
        q=0;
        i=0;
        j=0;
       while(q<8)
       {
           int y=popu[s][q]-1;
          int z=j;
           z++;
           
           while(z<=7){
               if(board[y][z]!=0)fitness++;
                         
               z++;
               
           }
           j++;
           
       q++;
       }
      
      
    System.out.println(" fitness ="+fitness);
       System.out.println("");
       System.out.println("");
   return fitness;
   }
   
   //printing chess board
   static void printboard(int [][]board){
       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               System.out.print(" "+board[i][j]);
           }
           System.out.println("");
       }
   }
   static void printgene(int []gene){
       for (int i = 0; i <gene.length; i++) {
           System.out.print(" "+gene[i]);
       }
   }
   //choose maximum fitness
   static int getbestthree(int []fit){
   int max=-100;
       for (int i = 0; i < fit.length; i++) {
           if(max<fit[i])max=fit[i];
       }
   return max;
   }  //return index of maximum fitness
   static int checkindex(int []fit,int max){
       for (int i = 0; i < fit.length; i++) {
           if(fit[i]==max)return i;
       }
       return 0;
   }
   // removing 1 population from 4
   static int[][] removepopu(int x,int [][]arr){
       
      int [][]select=new int [3][8];
      int k=0;
       for (int i = 0; i < 4; i++) {
          if(i!=x){
           for (int j = 0; j < 8; j++) {
               select[k][j]=arr[i][j];
           }
          
           k++;}
       }
       
       return select;
   }
   
   // crossover array is break after index  2 and merging two array to form new two arrays
   static int [][] crossoverfunc(int [][]cross){
     int [][]crossover=new int [4][8];
        for (int i = 0; i < 8; i++) {
           if(i<3)crossover[0][i]=cross[1][i];
           else  crossover[0][i]=cross[0][i];
       }
       for (int i = 0; i < 8; i++) {
           if(i<3)crossover[1][i]=cross[0][i];
           else  crossover[1][i]=cross[1][i];
       }
       for (int i = 0; i < 8; i++) {
           if(i<3)crossover[2][i]=cross[2][i];
           else  crossover[2][i]=cross[1][i];
       }
       for (int i = 0; i < 8; i++) {
           if(i<3)crossover[3][i]=cross[1][i];
           else  crossover[3][i]=cross[2][i];
       }
       System.out.println("");
       System.out.println("BEFORE CROSS OVER");
       System.out.println("");
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 8; j++) {
               System.out.print(" "+cross[i][j]);
           }
           System.out.println("");
       }
       System.out.println("");
       System.out.println("AFTER CROSSOVER");
       System.out.println("");
       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 8; j++) {
               System.out.print(" "+crossover[i][j]);
           }
           System.out.println("");
       }
     
     
   return crossover;
   }
   // changing one repeated element or random replacement
    static int [][] mutatefunc(int [][]mutation){
     
     Random r=new Random();
     boolean check=true;
     //repitation
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 8; j++) {
            for (int k = j+1; k < 8; k++) {
                if(mutation[i][j]==mutation[i][k]){
                
                    mutation[i][k]=r.nextInt(8)+1;
                    check=false;
                }
            }
        }
    }
int x;
if(check==false){ //if no repitation in array
    for (int i = 0; i < 4; i++) {
      x=r.nextInt(8);
      mutation[i][x]=r.nextInt(8)+1;
    }
}    
        System.out.println("");
        System.out.println("AFTER MUTATION");
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" "+mutation[i][j]);
            }
            System.out.println("");
        }
     
   return mutation;
   }
   
    public static void main(String[] args) {
        Random rand=new Random();
        //initial population of 4
        System.out.println("INITIAL POPULATION");
        int [][]arr=new int[4][8];
        // every number in array represent the queen location.
      for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 8; j++) {
        arr[i][j]=rand.nextInt(8)+1;
             }
  
        }
    
        int []farray=new int[4];  //farray contain fitness value for corresponding population
        for (int i = 0; i < 4; i++) {
            farray[i]=fitnessfunc(arr,i);
        }
        
       
       int max=getbestthree(farray);  // funtion to find best three population for crossover
      System.out.println("removing maximum fitness value "+max);
        int i=checkindex(farray,max);
        int [][]selected=new int [3][8];
        selected=removepopu(i,arr);
        // creating threshold of 200 population or 50 generation and if fitness get 2 or less than 2
        int [][]newpopulation=new int [200][8];
        int []fitness=new int [200];
        System.out.println("checking");
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 8; k++) {
                System.out.print(" "+selected[j][k]);
            }
            System.out.println("");
        }
 
        
        // 50 generation in each generation we will made 4 population
       int [][]crossover=new int [4][8];
       int [][]mutate=new int [4][8];
       int fittest = 0;
       int fittestindex=0;
        int l=0,fitnesscounter=0;
        boolean check=true;
        int newpopucounter=0;
        int generation=1;
        // creating threshold of 200 population or 50 generation and if fitness get 2 or less than 2
       for (int j = 0; j < 200 && check==true; j=j+4) {
  
           System.out.println("GENERATION "+generation);
           generation++;
           System.out.println("");
            crossover=crossoverfunc(selected);
            mutate=mutatefunc(crossover);
           
            for (int k = 0; k < 4; k++) {
                for (int m = 0; m < 8; m++) {
                newpopulation[newpopucounter][m]=mutate[k][m];
            }
                newpopucounter++;
            }
            System.out.println("");
           System.out.println(""); 
            for (int k = 0; k < 4; k++) {
              fitness[fitnesscounter]=fitnessfunc(mutate,k);
                fitnesscounter++;
            }
           for (int k = 0; k < fitnesscounter && check==true; k++) {
               if(fitness[k]<=2){
                          fittest=fitness[k];
                          fittestindex=k; 
                          check=false;               
               }
               }
            //choosing last three population for next crossover
            int counter=newpopucounter-3;
            for (int k = 0; k <3; k++) {
                for (int m = 0; m < 8; m++) {
                    selected[k][m]=newpopulation[counter][m];
                }
                counter++;
                
           }
            counter=0;
           }
       
           System.out.println("FINAL RESULT");
           System.out.println("");
           //thrsehold
           if(check==false){
                   System.out.println("THRESHOLD OF FITENESS =2 or less 2");
                   for (int k = 0; k < 8; k++) {
                       System.out.print(" "+newpopulation[fittestindex][k]);        
                   }
                   System.out.println("");
                   System.out.println("FITNESS = "+fittest);
               
           }
           if(check==true){
               System.out.println("THRESHOLD OF 50 GENERATION COMPLETED");
               int fittestfit=1000;
               int indexx=0;
               for (int j = 0; j < newpopulation.length; j++) {
                  if(fittestfit>fitness[j]){
                  fittestfit=fitness[j];
                  indexx=j;
                  }
               }
               for (int j = 0; j < 8; j++) {
                   System.out.print(" "+newpopulation[indexx][j]);
               }
               System.out.println("");
               System.out.println("FITNESS = "+fittestfit);
           
           }
            
            
        }
       
    }
    

