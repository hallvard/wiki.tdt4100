package kont2013;


public class ValuesDiceTest extends AbstractDiceTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp(new ValuesDice(this, 5));
	}
	
	public void testGetValueCount() {
		roll(1, 2, 3, 4, 5);
		for (int i = 1; i <= 5; i++) {
			assertEquals(1, dice.getValueCount(i));
		}
		assertEquals(0, dice.getValueCount(6));
		roll(2);
		for (int i = 1; i <= 5; i++) {
			assertEquals(i == 2 ? 5 : 0, dice.getValueCount(i));
		}
	}
	
	public void testToString() {
		roll(2, 1, 3, 5, 4);
		assertEquals(dice.toString(), 1, 2, 3, 4, 5);
		roll(2);
		assertEquals(dice.toString(), 2, 2, 2, 2, 2);
	}
	
	public void testGetHighestValueOfSame() {
		roll(1, 3, 1, 6, 3);
		assertEquals(3, dice.getHighestValueOfSame(2, 0));
		assertEquals(1, dice.getHighestValueOfSame(2, 3));
		assertEquals(6, dice.getHighestValueOfSame(1, 0));
		assertEquals(0, dice.getHighestValueOfSame(3, 0));
	}
	
	public void testGetStraightSum() {
		roll(2, 1, 3, 5, 3);
		assertEquals(6, dice.getStraightSum(1, 3));
		assertEquals(0, dice.getStraightSum(1, 5));
	}
	
	public void testRoll() {
		roll(1, 3, 1, 6, 3);
		setDice(4, 5);
		dice.roll(new int[]{1, 6});
		assertDiceEquals(1, 3, 3, 4, 5);
	}
}
