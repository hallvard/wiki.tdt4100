package ord2017.stage3;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

public class DinerTest extends TestCase implements CapacityListener {

	private Diner diner;
	private SimpleTable table4, table8;
	private Group group1, group4, group8;

	protected void setUp() throws Exception {
		diner = new Diner();
		table4 = new SimpleTable(4);
		table8 = new SimpleTable(8);
		diner.addTable(table4);
		diner.addTable(table8);
		group1 = new Group(1);
		group4 = new Group(4);
		group8 = new Group(8);
	}
	
	public void testIsOccupied() {
		assertFalse(diner.isOccupied(table4));
		assertFalse(diner.isOccupied(table8));
		diner.addSeating(new Seating(table4, group1));
		assertTrue(diner.isOccupied(table4));
		assertFalse(diner.isOccupied(table8));
	}

	public void testGetCapacity() {
		assertEquals(12, diner.getCapacity(false));
		diner.addSeating(new Seating(table4, group1));
		assertEquals(12, diner.getCapacity(true));
		assertEquals(8, diner.getCapacity(false));
	}
	
	public void testRemoveTable() {
		diner.removeTable(table8);
		assertEquals(1, diner.getTableCount());
		diner.addSeating(new Seating(table4, group1));
		try {
			diner.removeTable(table4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, diner.getTableCount());
		} catch (Exception e) {
			fail();
		}
	}

	public void testMergeTables() {
		assertEquals(2, diner.getTableCount());
		assertEquals(12, diner.getCapacity(false));
		diner.mergeTables(table4, table8, 2);
		assertEquals(1, diner.getTableCount());
		Table table = diner.getTable(0);
		assertTrue(table instanceof CompositeTable);
		assertEquals(10, diner.getCapacity(false));
	}
	
	public void testSplitTable() {
		diner.mergeTables(table4, table8, 2);
		assertEquals(1, diner.getTableCount());
		assertEquals(10, diner.getCapacity(false));
		
		Table table = diner.getTable(0);
		assertTrue(table instanceof CompositeTable);

		diner.splitTable((CompositeTable) table, 8, 4);
		assertEquals(2, diner.getTableCount());
		assertEquals(12, diner.getCapacity(false));
	}
	
	public void testHasCapacity() {
		assertTrue(diner.hasCapacity(table4, 4));
		assertTrue(diner.hasCapacity(table4, 1));
		diner.addSeating(new Seating(table4, group1));
		assertFalse(diner.hasCapacity(table4, 4));
		assertFalse(diner.hasCapacity(table4, 1));
	}
	
	public void testFindAvailableTables() {
		Collection<Table> tables1 = diner.findAvailableTables(4);
		assertEquals(2, tables1.size());
		assertTrue(tables1.containsAll(Arrays.asList(table4, table8)));

		Collection<Table> tables2 = diner.findAvailableTables(5);
		assertEquals(1, tables2.size());
		assertTrue(tables2.containsAll(Arrays.asList(table8)));

		diner.addSeating(new Seating(table4, group1));

		Collection<Table> tables3 = diner.findAvailableTables(4);
		assertEquals(1, tables3.size());
		assertTrue(tables3.containsAll(Arrays.asList(table8)));
	}
	
	public void testCreateSeating() {
		Seating seating1 = diner.createSeating(group1);
		assertSame(group1, seating1.getGroup());
		assertSame(table4, seating1.getTable());

		Seating seating2 = diner.createSeating(group4);
		assertSame(group4, seating2.getGroup());
		assertSame(table4, seating2.getTable());
		
		Seating seating3 = diner.createSeating(group8);
		assertSame(group8, seating3.getGroup());
		assertSame(table8, seating3.getTable());

		diner.addSeating(seating1);
		Seating seating4 = diner.createSeating(group4);
		assertSame(group4, seating4.getGroup());
		assertSame(table8, seating4.getTable());
	}
	
	public void testAddSeating() {
		assertTrue(diner.addSeating(group4));
		assertTrue(diner.isOccupied(table4));
		
		assertTrue(diner.addSeating(group8));
		assertTrue(diner.isOccupied(table8));

		assertFalse(diner.addSeating(group1));
	}
	
	public void testRemoveSeating() {
		Seating seating1 = diner.createSeating(group4);
		diner.addSeating(seating1);
		assertTrue(diner.isOccupied(table4));

		diner.removeSeating(((SimpleTable) seating1.getTable()).getNum());
		assertFalse(diner.isOccupied(table4));
	}

	private int changeCount = 0;
	
	@Override
	public void capacityChanged(Diner diner) {
		changeCount++;
	}
	
	public void testCapacityListener() {
		diner.addCapacityListener(this);
		assertEquals(0, changeCount);

		SimpleTable table2 = new SimpleTable(2);
		diner.addTable(table2);
		assertEquals(1, changeCount);

		diner.addSeating(group1);
		assertEquals(2, changeCount);
		
		Seating seating1 = diner.createSeating(group1);
		diner.addSeating(seating1);
		assertEquals(3, changeCount);
		
		diner.removeSeating(((SimpleTable) seating1.getTable()).getNum());
		assertEquals(4, changeCount);

		diner.removeTable(table4);
		assertEquals(5, changeCount);
		
		try {
			diner.removeTable(table2);
		} catch (Exception e) {
		}
		assertEquals(5, changeCount);
	}
}
