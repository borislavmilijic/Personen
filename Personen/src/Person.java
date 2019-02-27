import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* 
 * @author: 01249658 Borislav Milijic 
 */


public class Person extends HealthCheckable implements Comparable<Person> {
	private static final int MIN_AGE = 10, MAX_AGE = 100, MIN_WEIGHT = 20, MAX_WEIGHT = 200,
							 MIN_HEIGHT = 100, MAX_HEIGHT = 250;
	
	private String firstname;
	private String lastname;
	private int age = 20;
	private int heightCm = 180; 
	private double weightKg = 70;
	private Map<Skill, Integer> skillLevel;
	
	public Person (String firstname, String lastname, int age, int heightCm, double weightKg, Set<Skill> skills) {
		setFirstname(firstname);
		setLastname(lastname);
		setAge(age);
		setHeightCm(heightCm);
		setWeightKg(weightKg);
		if (skills == null || skills.isEmpty())
			throw new IllegalArgumentException("Invalid input of skill!");
		
		this.skillLevel = new LinkedHashMap<>();
		for(Skill s: skills)
			this.skillLevel.put(s, 0);
	}
	
	public Person (String firstname, String lastname, Set<Skill> skills) {
		setFirstname(firstname);
		setLastname(lastname);
		if (skills == null || skills.isEmpty())
			throw new IllegalArgumentException("Invalid input of skill!");
		this.skillLevel = new LinkedHashMap<>();
		for(Skill s: skills)
			this.skillLevel.put(s, 0);
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		if (firstname == null || firstname.isEmpty() || firstname.length() == 1)
			throw new IllegalArgumentException("Firstname not a valid Parameter!");
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		if (firstname == null || firstname.isEmpty() || firstname.length() == 1)
			throw new IllegalArgumentException("Lastname not a valid Parameter!");
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public int getHeightCm() {
		return heightCm;
	}

	public double getWeightKg() {
		return weightKg;
	}

	public void setAge(int age) {
		if (age < MIN_AGE || age > MAX_AGE) {
			throw new IllegalArgumentException("Age input invalid!");
		} else this.age = age;
	}

	public void setHeightCm(int heightCm) {
		if (heightCm < MIN_HEIGHT || heightCm > MAX_HEIGHT) {
			throw new IllegalArgumentException("Height input invalid!");
		} else this.heightCm = heightCm;
	}

	public void setWeightKg(double weightKg) {
		if (weightKg < MIN_WEIGHT || weightKg > MAX_WEIGHT) {
			throw new IllegalArgumentException("Weight input invalid!");
		} else this.weightKg = weightKg;
	}

	public Map<Skill, Integer> getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(Map<Skill, Integer> skills) {
		if (skills == null || skills.isEmpty())
			throw new IllegalArgumentException("Invalid input of skill!");
		this.skillLevel = skills;
	}

	public boolean olderThan (Person other) {
		 if (this.getAge() > other.getAge())
			 return true;
		 return false;
	}

	public boolean greaterThan (Person other) {
		if (this.getHeightCm() > other.getHeightCm())
			 return true;
		 return false;
	}
	 
	public boolean heavierThan (Person other) {
		if (this.getWeightKg() > other.getWeightKg())
			 return true;
		 return false;
	}
	 
	@Override
	public String toString() {
		 String resenje = 
				 getFirstname() + ", "
			   + getLastname() + ", (" 
			   + getAge() + " yrs, " 
			   + getHeightCm() 
			   + " cm, " + getWeightKg() 
			   + " kg) - " + getSkillLevel() 
	 		   + " -> " + calculateHealth() + " " ;
		 return resenje;
	}
	
	public boolean learn(Person other, Skill skill) {
		if(this.skillLevel.containsKey(skill))
			return false;
		if(!other.getSkillLevel().containsKey(skill))
			return false;
		
		for(Skill s: this.skillLevel.keySet()) {
			boolean marker = true;
			for(Skill s1: other.getSkillLevel().keySet()) {
				if (s1 == s)
					marker = false;
			}
			if(marker) {
				other.getSkillLevel().put(s, 0);
				this.skillLevel.put(skill, 0);
				return true;
			}
		}
		return false;
	}
	 
	 public boolean practise (Skill skill) {
		 if(!this.skillLevel.containsKey(skill))
			 return false;
		 this.skillLevel.put(skill, this.skillLevel.get(skill) + 1);
		 return true;
	 }
	 
	 @Override
	 public int compareTo(Person person) {
		 if(this.getAge() - person.getAge() < 0)
			 return -1;
		 else if(this.getAge() - person.getAge() == 0)
			 return 0;
		 return 1;
	 }
	 
	 public static Comparator<Person> compareByWeight() {
		Comparator<Person> compareWeight = new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				 if(p1.getWeightKg() - p2.getWeightKg() < 0)
					 return -1;
				 else if(p1.getWeightKg() - p2.getWeightKg() == 0)
					 return 0;
				 return 1;
			 }
		};
		return compareWeight;
	 }
	 
	 public static Comparator<Person> compareByHeight() {
		 Comparator<Person> compareHeight = new Comparator<Person>() {
				public int compare(Person p1, Person p2) {
					 if(p1.getHeightCm() - p2.getHeightCm() < 0)
						 return -1;
					 else if(p1.getHeightCm() - p2.getHeightCm() == 0)
						 return 0;
					 return 1;
				 }
			};
			return compareHeight;
	 }
	 
	 private int getBonus() {
		int sum = 0;
		for(Entry<Skill,Integer> pair : skillLevel.entrySet()) {
			sum+=pair.getKey().getBonus(pair.getValue());
		}
		return sum;
	 }
	 
	 public static Comparator<Person> compareByBonus() {
		 Comparator<Person> compareBonus = new Comparator<Person>() {
				public int compare(Person p1, Person p2) {
					 if(p1.getBonus() - p2.getBonus() < 0)
						 return -1;
					 else if(p1.getBonus() - p2.getBonus() == 0)
						 return 0;
					 return 1;
				 }
			};
		return compareBonus;
	 }
	 
	 @Override
	 double calculateHealth() {
		return 55+getBonus()*0.21; 
	 }
	 
}
