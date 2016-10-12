package objectstructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FeatureList {

	private List<String> featureNames = new ArrayList<>();
	private List<Double> featureValues = new ArrayList<>();
	
	public Collection<String> getFeatureNames() {
		return featureNames;
	}

	public boolean hasFeature(String featureName) {
		return featureNames.contains(featureName);
	}

	public double getFeatureValue(String featureName) {
		int pos = featureNames.indexOf(featureName);
		return featureValues.get(pos);
	}

	public void addFeature(String featureName, double value) {
		if (! hasFeature(featureName)) {
			featureNames.add(featureName);
			featureValues.add(value);
		}
	}

	public void setFeatureValue(String featureName, double value) {
		if (hasFeature(featureName)) {
			int pos = featureNames.indexOf(featureName);
			featureValues.set(pos, value);
		}
	}
	
	public void increment(double value) {
		for (int i = 0; i < featureValues.size(); i++) {
			featureValues.set(i, featureValues.get(i) + value);
		}
	}

	public void add(FeatureList features) {
		for (String featureName : features.getFeatureNames()) {
			double value = features.getFeatureValue(featureName);
			if (hasFeature(featureName)) {
				setFeatureValue(featureName, getFeatureValue(featureName) + value);
			} else {
				addFeature(featureName, value);
			}
		}
	}

	public void mult(FeatureList features) {
		for (String featureName : getFeatureNames()) {
			double value = getFeatureValue(featureName);
			if (features.hasFeature(featureName)) {
				double value2 = features.getFeatureValue(featureName);
				setFeatureValue(featureName, value * value2);
			} else {
				setFeatureValue(featureName, 0.0);
			}
		}
		for (String featureName : features.getFeatureNames()) {
			if (! hasFeature(featureName)) {
				addFeature(featureName, 0.0);
			}
		}
	}
	
	public FeatureList toFeatureList() {
		FeatureList featureList = new FeatureList();
		featureList.featureNames = new ArrayList<>(featureNames);
		featureList.featureValues = new ArrayList<>(featureValues);
		return featureList;
	}
}
