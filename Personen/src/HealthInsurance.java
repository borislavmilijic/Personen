import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public interface HealthInsurance extends Comparable<HealthInsurance> {
	static Set<HealthCheckable> findAbove(final Collection<HealthCheckable> coll, final Double threshold) {
		double compare = 0;
		
		if (threshold == null)
			compare = 75;
		else compare = threshold;
		
		Set<HealthCheckable> set1 = new TreeSet<HealthCheckable>(new Comparator<HealthCheckable>() {
			@Override
			public int compare(HealthCheckable hc1, HealthCheckable hc2) {
				return (int) (hc2.calculateHealth() - hc1.calculateHealth());
			}
		});
		
		if (coll == null)
			return set1;
		
		for(HealthCheckable hc: coll)
			if(hc != null && hc.calculateHealth()>compare)
				set1.add(hc);
		
		
		return set1;
		
	}
}
