package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PassportClearance {

    static ArrayList<String> inputString;
    static String[] searchKeywords = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public static void main(String[] args) {
        File file = new File("Resources/Day4.txt");
        writeInputString(file);

        ArrayList<Integer> topNumbs = new ArrayList<>();
        topNumbs.add(0);
        Integer count = 0;
        for(String string : inputString){
            if(string.equals("")){
                topNumbs.add(count);
            }
            count++;
        }
        topNumbs.add(inputString.size() - 1);

        ArrayList<String> passportSplit = new ArrayList<>();
        for(int i = 0; i < topNumbs.size() - 1; i++){
            passportSplit.add(extractSection(inputString, topNumbs.get(i), topNumbs.get(i + 1)));
        }

        ArrayList<Passport> passportList = new ArrayList<>();
        for(String s : passportSplit){
            System.out.print(s + "     : ");
            if(stringCount(s, searchKeywords) == 7){
                passportList.add(new Passport(s));
            }
        }
        int finalCount = 0;
        for(Passport p : passportList){
            if(p.validate()){
                finalCount++;
            }
        }
        System.out.println(finalCount);
    }

    public static String extractSection(ArrayList<String> input, int topLine, int bottomLine){
        String output = "";
        for(int i = topLine; i <= bottomLine; i++){
            output += input.get(i) + " ";
        }
        return output;
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

    public static Integer stringCount(String string, String[] keywords){
        Integer total = 0;
        for(String s : keywords){
            if(string.contains(s)){
                total++;
            }
        }
        System.out.println(total);
        return total;
    }
}
