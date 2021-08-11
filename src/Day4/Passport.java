package Day4;

import java.util.ArrayList;

public class Passport {
    private int byr;
    private int iyr;
    private int eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;

    public Passport(String inputLine){
        ArrayList<String> infoSplit = new ArrayList<>();
        for(String s : inputLine.split(" ")){
            infoSplit.add(s);
        }
        for(String s : infoSplit){
            if(s.contains("byr"))
                byr = Integer.parseInt(s.split(":")[1]);
            else if(s.contains("iyr"))
                iyr = Integer.parseInt(s.split(":")[1]);
            else if(s.contains("eyr"))
                eyr = Integer.parseInt(s.split(":")[1]);
            else if(s.contains("hgt"))
                hgt = s.split(":")[1];
            else if(s.contains("hcl"))
                hcl = s.split(":")[1];
            else if(s.contains("ecl"))
                ecl = s.split(":")[1];
            else if(s.contains("pid"))
                pid = s.split(":")[1];
        }
        System.out.println(byr);
        System.out.println(iyr);
        System.out.println(eyr);
        System.out.println(hgt);
        System.out.println(hcl);
        System.out.println(ecl);
        System.out.println(pid);
    }

    public Boolean validate(){
        int count = 0;
        if(byr >= 1920 && byr <= 2002){
            count++;
        }
        if(iyr >= 2010 && iyr <= 2020){
            count++;
        }
        if(eyr >= 2020 && eyr <= 2030){
            count++;
        }
        if(hgt.startsWith("cm", hgt.length() - 2) || hgt.startsWith("in", hgt.length() - 2)){
            int hgtConversion = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            if((hgt.startsWith("cm", hgt.length() - 2) && hgtConversion >= 150 && hgtConversion <= 193) ||(hgt.startsWith("in", hgt.length() - 2)) && hgtConversion >= 59 && hgtConversion <= 76){
                count++;
            }
        }
        if(hcl.charAt(0) == '#' && hcl.length() == 7) {
            Boolean check = false;
            for(int i = 1; i < hcl.length(); i++){
                if(Character.getNumericValue(hcl.charAt(i)) >= 16){
                    // 'g' Numerical value == 16
                    check = true;
                }
            }
            if(check == false){
                count++;
            }
        }
        if(stringContains(ecl, new String[]{"amb","blu","brn","gry","grn","hzl","oth"})){
            count++;
        }
        if(pid.length() == 9){
            try{
                Integer.parseInt(pid);
                count++;
            }
            catch (NumberFormatException e){

            }
        }


        if(count == 7){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean stringContains(String string, String[] keywords){
        boolean found = false;
        for(String s : keywords){
            if(string.contains(s)){
                found = true;
            }
        }
        return found;
    }
}
