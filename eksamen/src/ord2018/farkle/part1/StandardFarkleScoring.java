package ord2018.farkle.part1;

public class StandardFarkleScoring extends FarkleScoring {
	
	public StandardFarkleScoring() {
		super(new ValueScorer(5, 50), new ValueScorer(1, 100), new NOrMoreOfAKindScorer(3, true));
	}
}
