package kont2010.gps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GPSData implements Iterable<GPSSample> {

	private List<GPSSample> samples = new ArrayList<GPSSample>();

	public void addGPSSample(GPSSample sample) {
		samples.add(sample);
	}
	
	public Iterator<GPSSample> iterator() {
		return samples.iterator();
	}
	
	public void reduce() {
		GPSSample lastSample = null;
		int i = 0;
		while (i < samples.size()) {
			GPSSample sample = samples.get(i);
			if (lastSample != null && (sample.getTimestamp() - lastSample.getTimestamp() < 30 || sample.distance(lastSample) < 15)) {
				samples.remove(i);
			} else {
				lastSample = sample;
				i++;
			}
		}
	}
	
	public boolean contains(GPSPoint pt, double distance) {
		for (GPSSample sample: samples) {
			if (sample.distance(pt) < distance) {
				return true;
			}
		}
		return false;
	}

	public GPSSample closest(GPSPoint pt) {
		GPSSample closest = null;
		for (int i = 0; i < samples.size(); i++) {
			GPSSample sample = samples.get(i);
			if (closest == null || sample.distance(pt) < closest.distance(pt)) {
				closest = sample;
			}
		}
		return closest;
	}
	
	public static void main(String[] args) {
		GPSData gpsData = new GPSData();
		for (GPSSample sample: gpsData) {
			// gj¿r etter eller annet med sample
		}

		Iterator<GPSSample> it = gpsData.iterator();
		while (it.hasNext()) {
			GPSSample next = it.next();
			// ...
		}
	}
}
