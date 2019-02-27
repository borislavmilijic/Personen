import java.util.LinkedHashSet;
import java.util.Set;

public class Expert extends Person {

	private Set<Skill> specialisedSkills = new LinkedHashSet<>();
	
	public Expert(String firstname, String lastname, int age, int heightCm, double weightKg,
			Set<Skill> skills, Set<Skill> specialisedSkills) {
		super(firstname, lastname, age, heightCm, weightKg, skills);
		if(specialisedSkills.size() > 3)
			throw new IllegalArgumentException("Number of maximum skills is 3!");
		this.specialisedSkills = new LinkedHashSet<>(specialisedSkills);
	}
	
	@Override
	public boolean practise(Skill skill) {
		if(this.specialisedSkills.contains(skill)) {
			if(this.getSkillLevel().containsKey(skill)) {
			this.getSkillLevel().put(skill, this.getSkillLevel().get(skill) + 3);
			return true;
			}
			return false;
		} else if(this.getSkillLevel().containsKey(skill)) {
			this.getSkillLevel().put(skill, this.getSkillLevel().get(skill) + 1);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String resenje = super.toString();
		 resenje+= specialisedSkills;
		 return resenje;	
	}

	@Override
	double calculateHealth() {
		double res = specialisedSkills.size()* (super.calculateHealth()/100);
		
		return super.calculateHealth() + res; 
	}

}
