import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Question6
{
	public static void main(String[] args) throws InterruptedException //for TimeUnit/sleep
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of rows of matrix: ");
		int r = in.nextInt();
		System.out.print("Enter number of columns of matrix: ");
		int c = in.nextInt();
		int[][] array = new int[r][c];
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				System.out.print(
						"Enter integer at index[" + i + "][" + j + "]: ");
				array[i][j] = in.nextInt();
			}
		}

		IntegerMatrix m = new IntegerMatrix(array, r, c);
		m.printMatrix();

		System.out.println(
				"Sum of all elements of matrix: " + m.getSumOfAllElements());

		System.out.print("Enter the index of row: ");
		int rI = in.nextInt();
		System.out.println(
				"Sum of all elements of row[" + rI + "]: " + m.getSumOfRow(rI));

		System.out.print("Enter the index of column: ");
		int cI = in.nextInt();
		System.out.println(
				"Sum of all elements of column[" + cI + "]: " + m.getSumOfCol(cI));

		TimeUnit.MILLISECONDS.sleep(700);
		Location largest = m.findLargest();
		System.out.println("Largest element is " + largest.value);
		System.out.println("It can be found at row["+largest.row+"] and column["+largest.column+"]");

		TimeUnit.MILLISECONDS.sleep(700);
		Location smallest = m.findSmallest();
		System.out.println("Smallest element is " + smallest.value);
		System.out.println("It can be found at row["+smallest.row+"] and column["+smallest.column+"]");

		TimeUnit.MILLISECONDS.sleep(700);
		System.out.println("Existing array:");
		m.printMatrix();
		System.out.println("Enter the following data to replace a desired value in existing array:");
		System.out.print("Enter the row index: ");
		int ri = in.nextInt();
		System.out.print("Enter the column index: ");
		int rc = in.nextInt();
		System.out.print("Enter new value at [" + ri + "][" + rc + "]: ");
		int nV = in.nextInt();
		Location changeItem = new Location(ri, rc, nV);
		if (m.putItem(changeItem))
		{
			System.out.print("Updating.");
			TimeUnit.MILLISECONDS.sleep(700);
			System.out.print(".");
			TimeUnit.MILLISECONDS.sleep(700);
			System.out.print(".");
			TimeUnit.MILLISECONDS.sleep(700);
			System.out.print(".");
			TimeUnit.MILLISECONDS.sleep(700);
			System.out.println(".");
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.println("Array has been successfully updated as follows:");
			m.printMatrix();
		}
		else
			System.err.println("ERROR! Location not found!");

	}
}

class IntegerMatrix
{
	private int[][] array;
	int r, c;
	IntegerMatrix(int[][] a, int rw, int col)
	{
		r = rw;
		c = col;
		array = new int[r][c];
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				array[i][j] = a[i][j];
			}
		}
	}

	void printMatrix()
	{
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
	}

	int getSumOfAllElements()
	{
		int sAll = 0;
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				sAll += array[i][j];
			}
		}
		return sAll;
	}

	int getSumOfRow(int r)
	{
		if (r > this.r-1)
		{
			System.err.println("ERROR! Row not found!");
			return 0;
		}
		else
		{
			int sR = 0;
			for (int i = 0; i < c; i++)
			{
				sR += array[r][i];
			}
			return sR;
		}
	}

	int getSumOfCol(int c)
	{
		if (c > this.c-1)
		{
			System.err.println("ERROR! Column not found!");
			return 0;
		}
		else
		{
			int sC = 0;
			for (int i = 0; i < r; i++)
			{
				sC += array[i][c];
			}
			return sC;
		}
	}

	Location findLargest()
	{
		int largestValue = 0, row = 0, column = 0;
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				if (largestValue < array[i][j])
				{
					largestValue = array[i][j];
					row = i;
					column = j;
				}
			}
		}
		Location largest = new Location(row, column, largestValue);
		return largest;
	}

	Location findSmallest()
	{
		int smallestValue = array[0][0];
		int row = 0, column = 0;
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				if (smallestValue > array[i][j])
				{
					smallestValue = array[i][j];
					row = i;
					column = j;
				}
			}
		}
		Location smallest = new Location(row, column, smallestValue);
		return smallest;
	}

	boolean putItem(Location obj)
	{
		if (obj.row>r-1||obj.column>c-1)
			return false;
		else
		{
			array[obj.row][obj.column] = obj.value;
			return true;
		}
	}
}

class Location
{
	int row, column, value;

	Location(int r, int c, int v)
	{
		row = r;
		column = c;
		value = v;
	}
}
