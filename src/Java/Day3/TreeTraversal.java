package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeTraversal {
    static ArrayList<String> inputLines;

    public static void main(String[] args) {
        try {
            File file = new File("/home/jack/Documents/University/Year 1/AdventOfCode/Resources/Java.Day3.txt");
            Scanner s = new Scanner(file);
            inputLines = new ArrayList<String>();
            while(s.hasNextLine()){
                inputLines.add(s.nextLine());
            }
            s.close();

            int move1 = treeCount(1, 1);
            int move2 = treeCount(3, 1);
            int move3 = treeCount(5, 1);
            int move4 = treeCount(7, 1);
            int move5 = treeCount(1, 2);

            double total = move1 * move2;
            total = total * move3;
            total = total * move4;
            total = total * move5;
            System.out.println("Total: " + total);
        }
        catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }
    }

    public static boolean isTree(char character) {
        if (character == '#') {
            return true;
        } else {
            return false;
        }
    }

    public static char inputCheck(int x, int y){
        return inputLines.get(y).charAt(x);
    }

    public static int treeCount(int horizontalMovement, int verticalMovement){
        int posX = 0;
        int posY = 0;
        int count = 0;
        while(posY < inputLines.size()){
            if(isTree(inputCheck(posX,posY))){
                count++;
            }
            posX += horizontalMovement;
            posX = posX % inputLines.get(0).length();
            posY += verticalMovement;
        }
        System.out.println(count);
        return count;
    }
}
