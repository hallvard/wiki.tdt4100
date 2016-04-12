package kont2009.mobile;

public class Mms extends ServiceUsage {

	public Mms(byte[] mmsData) {
		super(mmsData.length);
	}
	
	public String getUnitType() {
		return "bytes";
	}
	public int getChunkSize() {
		return (int)Math.pow(2, 10);
	}
}
