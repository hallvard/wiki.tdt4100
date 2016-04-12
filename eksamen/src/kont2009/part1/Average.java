package kont2009.part1;

public class Average {
	
	public static double average(int[][] array2d, int x, int y, int dx, int dy) {
		double sum = 0.0;
		int count = 0;
		while (y >= 0 && y < array2d.length && x >= 0 && x < array2d[y].length) {
			sum += array2d[y][x];
			count++;
			x += dx;
			y += dy;
		}
		return sum / count;
	}
	
	public static void main(String[] args) {
		int[][] array2d = {
				{1, 2, 3},
				{4, 5},
				{7, 8, 9},
		};
		System.out.println(average(array2d, 1, 1, -1, -1));
		System.out.println(average(array2d, 1, 1,  0, -1));
		System.out.println(average(array2d, 1, 1,  1, -1));
		System.out.println(average(array2d, 1, 1,  1,  0));
		System.out.println(average(array2d, 1, 1,  1,  1));
		System.out.println(average(array2d, 1, 1,  0,  1));
		System.out.println(average(array2d, 1, 1, -1,  1));
		System.out.println(average(array2d, 1, 1, -1,  0));

		System.out.println(average(array2d, 1, 1, -2, -2));
		System.out.println(average(array2d, 1, 1,  0, -2));
		System.out.println(average(array2d, 1, 1,  2, -2));
		System.out.println(average(array2d, 1, 1,  2,  0));
		System.out.println(average(array2d, 1, 1,  2,  2));
		System.out.println(average(array2d, 1, 1,  0,  2));
		System.out.println(average(array2d, 1, 1, -2,  2));
		System.out.println(average(array2d, 1, 1, -2,  0));
	}
}
