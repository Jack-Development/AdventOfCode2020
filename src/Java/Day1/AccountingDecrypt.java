package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingDecrypt {
    public static void main(String[] args) {
        try {
            Path thisFile = Paths.get("").toAbsolutePath();
            Path greatGrandparent = thisFile.getParent().getParent().getParent();
            File file = new File(greatGrandparent.toAbsolutePath() + "/Resources/Day1.txt");
            System.out.println(file.getAbsoluteFile());
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
