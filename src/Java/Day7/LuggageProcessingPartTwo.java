package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class LuggageProcessingPartTwo {
    static ArrayList<String> inputString = new ArrayList<>();
    static ArrayList<ArrayList<String>> requirements = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> bagCount = new ArrayList<>();
    static ArrayList<String[]> stringCombo = new ArrayList<>();
    static ArrayList<String> bags = new ArrayList<>();

    public static void main(String[] args) {
        Path thisFile = Paths.get("").toAbsolutePath();
        Path greatGrandparent = thisFile.getParent().getParent().getParent();
        File file = new File(greatGrandparent.toAbsolutePath() + "/Resources/Day1.txt");
        writeInputString(file);
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
            bagCount.add(new ArrayList<>());
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
                    bagCount.get(i).add(Integer.parseInt(s));
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

        System.out.println(findRequirements("shiny gold"));

        //System.out.println(findRequirements("shiny chartreuse"));
    }
    
    public static int findRequirements(String bagName){
        int bagIndex = 0;
        for(int i = 0; i < bags.size(); i++){
            if(bags.get(i).equals(bagName)){
                bagIndex = i;
            }
        }

        ArrayList<String> getRequires = requirements.get(bagIndex);
        ArrayList<Integer> bagRequires = bagCount.get(bagIndex);

        if(getRequires.size() > 0){
            int total = 0;
            for(int i = 0; i < getRequires.size(); i++){
                int countOfBag = bagRequires.get(i);
                String s = getRequires.get(i);
                System.out.println("Adding: " + countOfBag + ", " + s + "(Child of: " + bagName + ")");
                total += countOfBag;
                total += countOfBag * findRequirements(s);
            }
            return total;
        }
        else{
            return 0;
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
