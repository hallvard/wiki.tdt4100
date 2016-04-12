package kont2009.mobile;

public abstract class ServiceUsage {
	
	private int unitCount;
	
	protected ServiceUsage(int unitCount) {
		this.unitCount = unitCount;
	}
	
	public int getUnitCount() {
		return unitCount;
	}
	
	public abstract String getUnitType();
	public abstract int getChunkSize();
	
	public int getPrice(int pricePrUsage, int pricePrChunk) {
		return pricePrUsage + pricePrChunk * ((unitCount - 1) / getChunkSize() + 1);
	}
}
