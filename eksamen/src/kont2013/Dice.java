package kont2013;


public interface Dice {

	public static final int MAX_DIE_VALUE = 6;
	
	public int getValueCount(int value);
	public void roll();
	public void roll(int[] values);

	public int getHighestValueOfSame(int count, int butNot);
	public int getDualCountScore(int count1, int count2);
	public int getStraightSum(int startValue, int endValue);
}
