package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exception.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

    public static void main(String[] args) {
        try {

            List<String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
            List<String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();


            List<String> lastNames = CSVReader_Writer.getLastNames(); //calling method getLastName
            NameService nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);


            Person test = nameService.getNewRandomPerson();
            System.out.println(test);

            Person myTestPerson = nameService.getNewRandomPerson(); //create new person
            System.out.println(myTestPerson.toString());

            List<String> maleTestNames = new ArrayList<>();
            maleTestNames.add("testName1");
            maleTestNames.add("testName2");
            maleTestNames.add("testName3");
            maleTestNames.add("testName4");
            CSVReader_Writer.saveMaleNames(maleTestNames);

            nameService.addMaleFirstName("testName1"); //this should be a duplicate

        } catch (IOException e) {
            System.out.println("Error while trying to fetch last names from file: " + e.getMessage());
        } catch (DuplicateNameException e) {
            System.out.println("Name already exists within file.");;
        }


    }

}
