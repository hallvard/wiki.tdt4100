package kont2015.unit2;

public class Unit {
	
	private final String symbol;

	public Unit(String symbol) throws IllegalArgumentException {
		for (int i = 0; i < symbol.length(); i++) {
			char c = symbol.charAt(i);
			if (! Character.isAlphabetic(c)) {
				throw new IllegalArgumentException(c + " is an illegal symbol character");
			}
		}
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
	
	public Unit findCommonBaseUnit(Unit other) {
		Unit unit1 = this;
		while (unit1 != null) {
			Unit unit2 = other;
			while (unit2 != null) {
				if (unit2 == unit1) {
					return unit1;
				}
				unit2 = (unit2 instanceof DerivedUnit ? ((DerivedUnit) unit2).getBase() : null);
			}
			unit1 = (unit1 instanceof DerivedUnit ? ((DerivedUnit) unit1).getBase() : null);
		}
		return null;
	}

	public double convert(double value, Unit other) throws IllegalArgumentException {
		Unit base = findCommonBaseUnit(other);
		if (base == null) {
			throw new IllegalArgumentException("Cannot convert from " + this + " to " + other);
		}
		double baseValue = convertToBase(value, base);
		return other.convertFromBase(baseValue, base);
	}

	protected double convertToBase(double value, Unit base) {
		if (this != base) {
			throw new IllegalArgumentException(base + " is not a base for " + this);
		}
		return value;
	}

	protected double convertFromBase(double value, Unit base) {
		if (this != base) {
			throw new IllegalArgumentException(base + " is not a base for " + this);
		}
		return value;
	}
}
