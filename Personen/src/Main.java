import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {

public static void main(String[] args) {


	Expert liz = new Expert("Elizabeth", "Hoover", 36, 173, 55, EnumSet.of(Skill.PROGRAMMING, Skill.HISTORY), EnumSet.of(Skill.JAPANESE));
	Person hans = new Person("Hans", "Maulwurf", 69, 185, 96, EnumSet.of(Skill.HISTORY, Skill.LITERATURE));
	Person jasper = new Person("Jasper", "Beardley", 87, 160, 60, EnumSet.of(Skill.JAPANESE, Skill.LITERATURE));
	
	System.out.println(hans.practise(Skill.LITERATURE));
	System.out.println(jasper.practise(Skill.HISTORY));
	System.out.println(hans.learn(jasper, Skill.SPORTS));
	System.out.println(liz.practise(Skill.JAPANESE));
	System.out.println(liz.learn(jasper, Skill.JAPANESE));
	System.out.println(liz.practise(Skill.JAPANESE));
	System.out.println(liz.practise(Skill.PROGRAMMING));
	
	Set<Person> set10 = new ConcurrentSkipListSet<>();
	
	set10.add(liz); set10.add(hans); set10.add(jasper);
	
	System.out.println(set10);
	
	HealthCheckable hi1 = liz;
	HealthCheckable hi2 = hans;
	HealthCheckable hi3 = jasper;
	
	Collection<HealthCheckable> coll = new LinkedList<HealthCheckable>();
	coll.add(hi1); coll.add(hi2); coll.add(hi3);
	
	Collection<HealthCheckable> coll1 = null;
	
	
	System.out.println(HealthInsurance.findAbove(coll, null));
	System.out.println(HealthInsurance.findAbove(coll, 90d));
	System.out.println(HealthInsurance.findAbove(null, 50d));
	System.out.println(HealthInsurance.findAbove(coll1, 50d));
	}
}
