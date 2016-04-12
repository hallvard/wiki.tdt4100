package kont2009.mobile;

public class VoiceCall extends ServiceUsage {

	public VoiceCall(int seconds) {
		super(seconds);
	}
	
	public String getUnitType() {
		return "seconds";
	}
	public int getChunkSize() {
		return 60;
	}
}
