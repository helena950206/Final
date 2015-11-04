package com.cpsc310.vanschool.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import com.cpsc310.vanschool.client.School;

public class FileConnectionManager {
    private static List<School> schools;

    private static List readCSV(){
        List<String[]> myEntries = new ArrayList<String[]>();
        try{
            CSVReader csvReader = openCSVFile("C:\\Users\\Peter\\Documents\\UBC\\CPSC 310\\Eclipse Projects\\Schools\\src\\Schools.csv");
            myEntries = csvReader.readAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return myEntries; //schools;
    }

    private static CSVReader openCSVFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Reader reader = new FileReader(file);
        CSVReader csvReader = new CSVReader(reader);
        return csvReader;
    }

    public static List<School> getSchools(){
        List<String[]> data = readCSV();
        return getSchools(data);
    }
    public static List<School> getSchools(List<String[]> data){
        schools = new ArrayList<School>();
        for (int i = 1; i < data.size(); i++){
            String[] line = data.get(i);

            Location location = new Location(Double.parseDouble(line[14]), Double.parseDouble(line[15]));
            School school = new School(Integer.parseInt(line[0]), line[1], line[2], line[3], parseBool(line[4]), parseBool(line[5]),
                    parseBool(line[6]), parseBool(line[7]), parseBool(line[8]), parseBool(line[9]), parseBool(line[10]),
                    parseBool(line[11]), parseBool(line[12]), parseBool(line[13]), location);
            schools.add(school);
        }
        return schools;
    }

    private static boolean parseBool(String s){
        return s.trim().toUpperCase().equals("TRUE");
    }
	
/*	public static void main(String[] args) {
		List<String[]> data = readCSV();
		getSchools(data);
	}*/

}
