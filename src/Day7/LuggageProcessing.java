package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LuggageProcessing {
    static ArrayList<String> inputString = new ArrayList<>();
    static ArrayList<ArrayList<String>> requirements = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> bagCount = new ArrayList<>();
    static ArrayList<String[]> stringCombo = new ArrayList<>();
    static ArrayList<String> bags = new ArrayList<>();

    public static void main(String[] args) {
        writeInputString(new File("/home/jack/Documents/University/Year 1/AdventOfCode/Resources/Day7.txt"));
        for(String s : inputString){
            stringCombo.add(new String[2]);
        }
        for(int i = 0; i < inputString.size(); i++){
            stringCombo.get(i)[0] = inputString.get(i).split(" ")[0];
            stringCombo.get(i)[1] = inputString.get(i).split(" ")[1];
        }
        for(int i = 0; i < inputString.size(); i++){
            bags.add(stringCombo.get(i)[0] + " " + stringCombo.get(i)[1]);
        }
        /*for(String s : bags){
            System.out.println(s);
        }*/
        for(int i = 0; i < inputString.size(); i++){
            int num = 0;
            requirements.add(new ArrayList<>());
            String part1 = "";
            String part2 = "";
            for(String s : inputString.get(i).split(" ")){
                if(num == 2){
                    part1 = s;
                    num--;
                }
                else if(num == 1){
                    part2 = s;
                    num--;
                    requirements.get(i).add(part1 + " " + part2);
                }
                try{
                    Integer.parseInt(s);
                    num = 2;
                }
                catch (NumberFormatException e){

                }
            }
        }
        /*for(ArrayList<String> list : requirements){
            for(String s : list){
                System.out.println(s);
            }
        }*/
        int count = 0;
        for(int i = 0; i < bags.size(); i++){
            if(findRequirements(bags.get(i))){
                count++;
            }
        }
        System.out.println(count);

        //System.out.println(findRequirements("shiny chartreuse"));
    }
    
    public static boolean findRequirements(String bagName){
        int bagIndex = 0;
        for(int i = 0; i < bags.size(); i++){
            if(bags.get(i).equals(bagName)){
                bagIndex = i;
            }
        }
        ArrayList<String> getRequires = requirements.get(bagIndex);
        boolean found = false;
        for(String s : getRequires){
            if(s.equals("shiny gold")){
                found = true;
            }
        }
        if(!found){
            if(getRequires.size() > 0){
                boolean subset = false;
                for(int i = 0; i < getRequires.size(); i++){
                    String s = getRequires.get(i);
                    if(findRequirements(s)){
                        subset = true;
                    }
                }
                if(subset){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
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
