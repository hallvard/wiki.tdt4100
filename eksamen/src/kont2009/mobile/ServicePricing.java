package kont2009.mobile;

public class ServicePricing {
	
	private int pricePrChunk, pricePrUsage;
	
	public ServicePricing(int pricePrChunk, int pricePrUsage) {
		this.pricePrChunk = pricePrChunk;
		this.pricePrUsage = pricePrUsage;
	}

	public int getPricePrChunk() {
		return pricePrChunk;
	}
	
	public int getPricePrUsage() {
		return pricePrUsage;
	}
}
