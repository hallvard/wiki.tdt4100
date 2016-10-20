package objectstructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FeatureList {

	private List<String> featureNames = new ArrayList<>();
	private List<Double> featureValues = new ArrayList<>();

	public FeatureList() {
	}
	
	// extra constructor
	public FeatureList(Object... namesValues) {
		for (int i  = 0; i  < namesValues.length; i += 2) {
			featureNames.add((String) namesValues[i]);
			featureValues.add((Double) namesValues[i + 1]);
		}
	}
	
	@Override
	public String toString() {
		String result = "(";
		for (int i = 0; i < featureNames.size(); i++) {
			if (i > 0) {
				result += ", ";
			}
			result += featureNames.get(i) + ": " + featureValues.get(i);
		}
		result += ")";
		return result;
	}
	
	public Collection<String> getFeatureNames() {
		return featureNames;
	}

	public boolean hasFeature(String featureName) {
		return featureNames.contains(featureName);
	}

	public double getFeatureValue(String featureName) {
		int pos = featureNames.indexOf(featureName);
		return (pos < 0 ? 0.0 : featureValues.get(pos));
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
			double value1 = getFeatureValue(featureName), value2 = features.getFeatureValue(featureName);
			setFeatureValue(featureName, value1 * value2);
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
	
	//

	public static void main(String[] args) {
		FeatureList featureList = new FeatureList("java", 100.0, "c++", 0.1);
		System.out.println(featureList);
	}
}
