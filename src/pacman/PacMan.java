/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PacMan extends JFrame {

    public static final int N_BLOCKS = 15;
    public static final int maximum = 9;
    

    public static final short levelData[][] = {
        {19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22},
        {21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20},
        {17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20},
        {25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21},
        {1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21},
        {1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21},
        {1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21},
        {1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21},
        {1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21},
        {1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21},
        {1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20},
        {9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28}
    };
    static char grid[][];
    static int path[][];
    static int shortest[][];

    public PacMan() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            PacMan ex = new PacMan();
            ex.setVisible(true);
        });

        int index =0;
                
        
        path = new int[N_BLOCKS+1][N_BLOCKS+1];
        shortest = new int[N_BLOCKS*N_BLOCKS+1][N_BLOCKS*N_BLOCKS+1];

        grid = new char[N_BLOCKS+1][N_BLOCKS+1];
        
        for (int i = 0; i < N_BLOCKS*N_BLOCKS; i++) {

            for (int j = 0; j < N_BLOCKS*N_BLOCKS; j++) {

                if(i!=j){
                    shortest[i][j] = maximum;
                }
            }
        }
        
        
        for (int i = 0; i < N_BLOCKS; i++) {

            for (int j = 0; j < N_BLOCKS; j++) {

                char val = '#';
                if((levelData[i][j]&16)!=0)
                    val = '.';
                //System.out.print(val+"("+i+","+j+")" + "  ");
                System.out.print(val+ "  ");
                grid[i][j]= val;

            }
            System.out.println("");
        }
        

        for (int i = 0; i < N_BLOCKS; i++) {

            for (int j = 0; j < N_BLOCKS; j++) {
                
                if(!checkIfValid(i,j)){
                    
                    continue;
                }

                if(checkIfValid(i,j+1))
                    path[i][j+1]++;
                if(checkIfValid(i,j-1))
                    path[i][j-1]++;
                if(checkIfValid(i+1,j))
                    path[i+1][j]++;
                if(checkIfValid(i-1,j))
                    path[i-1][j]++;           
               
            }
        }
        
//        for (int i = 0; i < N_BLOCKS; i++) {
//
//            for (int j = 0; j < N_BLOCKS; j++) {
//
//              
//                System.out.print(path[i][j] + "  ");
//
//            }
//            System.out.println("");
//        }
        
        
        System.out.println("==========================================");

        
        for (int i = 0; i < N_BLOCKS*N_BLOCKS; i++) {

            for (int j = 0; j < N_BLOCKS*N_BLOCKS; j++) {

                System.out.print(shortest[i][j]+"  ");
            }
                        
            System.out.println("");

        }
         
        
    }
    
    static boolean checkIfValid(int x,int y){
        
        if(x<0||y<0||x>=N_BLOCKS || y>=N_BLOCKS)
            return false;
        if(grid[x][y]=='.'){
            
            shortest[x][y] = 1;
            return true;
        }
        return false;
    }
}
