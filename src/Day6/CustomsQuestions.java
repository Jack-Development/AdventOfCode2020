package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomsQuestions {
    static ArrayList<String> inputString = new ArrayList<>();

    public static void main(String[] args) {
        writeInputString(new File("/home/jack/Documents/University/Year 1/AdventOfCode/Resources/Day6.txt"));

        ArrayList<Integer> splitNum = new ArrayList<>();
        System.out.println(inputString.size());
        splitNum.add(0);
        for(int i = 0; i < inputString.size(); i++){
            if(inputString.get(i).equals("")){
                splitNum.add(i);
            }
        }
        splitNum.add(inputString.size() - 1);

        ArrayList<String> infoSplit = new ArrayList<>();
        for(int i = 0; i < splitNum.size() - 1; i++){
            infoSplit.add(extractInfo(inputString, splitNum.get(i), splitNum.get(i + 1)));
        }

        infoSplit.set(0, infoSplit.get(0).substring(0, infoSplit.get(0).length() - 2));
        for(int i = 1; i < infoSplit.size(); i++){
            infoSplit.set(i, infoSplit.get(i).substring(1, infoSplit.get(i).length() - 2));
        }

        ArrayList<Integer> questionCount = new ArrayList<>();
        for(String s : infoSplit) {
            questionCount.add(uniqueString(s.split(" ")));
        }
        int total = 0;
        for(int i : questionCount){
            total += i;
        }
        System.out.println(total);
    }

    public static int uniqueString(String[] s){
        String[] keywords = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        ArrayList<Integer> letterCount = new ArrayList<>();
        for(String words : keywords){
            letterCount.add(0);
        }
        for(int i = 0; i < s.length; i++){
            for(int j = 0; j < s[i].length(); j++){
                letterCount.set(Character.getNumericValue(s[i].charAt(j)) - 10, letterCount.get(Character.getNumericValue(s[i].charAt(j)) - 10) + 1);
                System.out.println(Character.getNumericValue(s[i].charAt(j)) - 10 + ", " + s[i].charAt(j));
            }
        }
        int count = 0;
        for(int i : letterCount){
            if(i == s.length){
                count++;
            }
        }
        return count;
    }

    public static String extractInfo(ArrayList<String> input, int topLine, int bottomLine){
        String output = "";
        for(int i = topLine; i <= bottomLine; i++){
            output += (input.get(i)) + " ";
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
}
