package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingDecrypt {
    public static void main(String[] args) {
        try {
            File file = new File("/home/jack/Documents/University/Year 1/AdventOfCode/Resources/Java.Day1.txt");
            Scanner s = new Scanner(file);

            ArrayList<Integer> input = new ArrayList<Integer>();
            while(s.hasNextLine()){
                input.add(Integer.parseInt(s.nextLine()));
            }

            for(Integer i : input){
                for(Integer j : input) {
                    for(Integer k : input){
                        if(i + j + k == 2020){
                            System.out.println(i * j * k);
                        }
                    }
                }
                //System.out.println(i);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: File not found");
        }
    }
}
