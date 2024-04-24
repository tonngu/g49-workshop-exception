package se.lexicon.exceptions.workshop.data_access;

import java.util.List;
import java.util.Random;

//import jdk.internal.foreign.abi.Binding;
import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exception.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class NameService {
	

	    private List<String>maleFirstNames;
	    private List<String>femaleFirstNames;
	    private List<String>lastNames;
	    private static Random random = new Random();

	    //should be no nulls
	    public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {

	        this.maleFirstNames = maleFirstNames;
	        this.femaleFirstNames = femaleFirstNames;
	        this.lastNames = lastNames;
	    }

	    public Person getNewRandomPerson(){
	        Gender gender = getRandomGender();
	        Person person = null;
	        switch (gender){
	            case MALE:
	                person = new Person(getRandomMaleFirstName(),getRandomLastName(),gender);
	                break;
	            case FEMALE:
	                person = new Person(getRandomFemaleFirstName(),getRandomLastName(),gender);
	                break;
	        }
	        return person;
	    }


	    public String getRandomFemaleFirstName(){
	        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
	    }

	    public String getRandomMaleFirstName(){
	        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
	    }

	    public String getRandomLastName(){
	        return lastNames.get(random.nextInt(lastNames.size()));
	    }

	    public Gender getRandomGender(){
	        return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
	    }


	    /**
	     * Here you need to check if List<String> femaleFirstNames already contains the name
	     * If name already exists throw a new custom exception you will have to create called
	     * DuplicateNameException.
	     * @param name
	     */
	    public void addFemaleFirstName(String name) throws DuplicateNameException {
			if (femaleFirstNames.contains(name)){
				throw new DuplicateNameException("The name being added already existed.");
			}
	    	femaleFirstNames.add(name);
	    	CSVReader_Writer.saveFemaleNames(femaleFirstNames);
	    		
	    }

	    /**
	     * Here you need to check if List<String> maleFirstNames already contains the name
	     * If name already exists throw a new custom exception you will have to create called
	     * DuplicateNameException.
	     * @param name
	     */
	    public void addMaleFirstName(String name) throws DuplicateNameException {
			if (maleFirstNames.contains(name)){
				throw new DuplicateNameException("The name being added already existed.");
			}
	    	maleFirstNames.add(name);
	        CSVReader_Writer.saveMaleNames(maleFirstNames);
	    }

	    /**
	     * Here you need to check if List<String> lastNames already contains the name
	     * If name already exists throw a new custom exception you will have to create called
	     * DuplicateNameException.
	     * @param lastName
	     */
	    public void addLastName(String lastName) throws DuplicateNameException{
			if (lastNames.contains(lastName)){
				throw new DuplicateNameException("The name being added already existed.");
			}
	    	lastNames.add(lastName);
	        CSVReader_Writer.saveLastNames(lastNames);
	    }




	
}
