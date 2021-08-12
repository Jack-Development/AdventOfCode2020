package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PasswordTest {

    public static void main(String[] args) {
        try {
            File file = new File("Resources/Day2.txt");
            Scanner s = new Scanner(file);
            ArrayList<String> input = new ArrayList<>();
            while(s.hasNextLine()){
                input.add(s.nextLine());
            }
            String[][] InputList = new String[input.size()][4];
            for(int i = 0; i < input.size(); i++){
                List<String> splitString = Arrays.asList(input.get(i).split(" "));
                List<String> numBounds = Arrays.asList(splitString.get(0).split("-"));

                Integer lowerBound = Integer.parseInt(numBounds.get(0));
                Integer upperBound = Integer.parseInt(numBounds.get(1));
                String letterRequirement = splitString.get(1).substring(0,1);
                String passwordHash = splitString.get(2);
                InputList[i][0] = lowerBound.toString();
                InputList[i][1] = upperBound.toString();
                InputList[i][2] = letterRequirement;
                InputList[i][3] = passwordHash;
                System.out.println(InputList[i][0] + ", " + InputList[i][1] + ", " + InputList[i][2] + ", " + InputList[i][3]);
            }
            int count = 0;
            for(int i = 0; i < InputList.length; i++){
                String targetChar = InputList[i][2];
                List<String> charList = Arrays.asList(InputList[i][3].split(""));
                int charCount = 0;
                for(String string : charList){
                    if(string.equals(targetChar)){
                        charCount++;
                    }
                }
                if(charCount >= Integer.parseInt(InputList[i][0]) && charCount <= Integer.parseInt(InputList[i][1])){
                    count++;
                }
            }
            System.out.println(count);
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: File not found");
        }
    }
}
