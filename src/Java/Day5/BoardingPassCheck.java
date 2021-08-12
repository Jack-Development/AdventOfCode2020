package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BoardingPassCheck {
    static ArrayList<String> inputString;

    public static void main(String[] args) {
        Path thisFile = Paths.get("").toAbsolutePath();
        Path greatGrandparent = thisFile.getParent().getParent().getParent();
        File file = new File(greatGrandparent.toAbsolutePath() + "/Resources/Day1.txt");
        writeInputString(file);
        ArrayList<Integer> totals = new ArrayList<>();
        for(int i = 0; i < inputString.size(); i++){
            int row = passRowCheck(0,128,0,i);
            int column = passColumnCheck(0,8,0,i);
            int total = row * 8 + column;
            totals.add(total);
        }
        Collections.sort(totals);
        int index = 0;
        for(int i = 51; i < 832; i++){
            if(totals.get(index) != i){
                System.out.println(i);
                index--;
            }
            index++;
        }
    }

    public static int passRowCheck(int lower, int upper, int attempt, int index){
        if(inputString.get(index).charAt(attempt) == 'B'){
                lower += ((upper - lower) / 2);
        }
        else if(inputString.get(index).charAt(attempt) == 'F'){
                upper -= ((upper - lower) / 2);
        }
        else{
            return Integer.MIN_VALUE;
        }

        if(lower != upper - 1){
            return passRowCheck(lower,upper,attempt + 1, index);
        }
        else{
            return lower;
        }
    }

    public static int passColumnCheck(int lower, int upper, int attempt, int index){
        if(inputString.get(index).charAt(attempt + 7) == 'R'){
            lower += ((upper - lower) / 2);
        }
        else if(inputString.get(index).charAt(attempt + 7) == 'L'){
            upper -= ((upper - lower) / 2);
        }
        else{
            return Integer.MIN_VALUE;
        }
        if(lower != upper - 1){
            return passColumnCheck(lower,upper,attempt + 1, index);
        }
        else{
            return lower;
        }
    }

    public static void writeInputString(File file){
        try {
            Scanner s = new Scanner(file);
            inputString = new ArrayList<>();
            while (s.hasNextLine()){
                inputString.add(s.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        }
    }
}
