package kont2015.unit2;

public class DerivedUnit extends Unit {
	
	private final Unit base;

	private final double factor, offset;
	
	public DerivedUnit(String symbol, Unit base, double factor, double offset) {
		super(symbol);
		this.base = base;
		this.offset = offset;
		this.factor = factor;
	}
	
	public DerivedUnit(String symbol, Unit base, double factor) {
		this(symbol, base, factor, 0.0);
	}
	
	public Unit getBase() {
		return base;
	}

	@Override
	protected double convertToBase(double value, Unit base) {
		if (this == base) {
			return value;
		}
		return this.base.convertToBase(value * factor + offset, base);
	}

	@Override
	protected double convertFromBase(double value, Unit base) {
		if (this == base) {
			return value;
		}
		return this.base.convertFromBase(value, base) / factor - offset / factor;
	}
}
