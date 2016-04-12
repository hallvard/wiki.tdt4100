package kont2009.mobile;

public class Sms extends ServiceUsage {

	public Sms(String text) {
		super(text.length());
	}
	
	public String getUnitType() {
		return "characters";
	}
	public int getChunkSize() {
		return 128;
	}
}
