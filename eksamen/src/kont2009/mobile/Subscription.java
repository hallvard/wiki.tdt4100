package kont2009.mobile;

import java.util.ArrayList;
import java.util.List;

public class Subscription {

	private List<ServiceUsage> serviceUsages = new ArrayList<ServiceUsage>();
	
	public void addServiceUsage(ServiceUsage serviceUsage) {
		serviceUsages.add(serviceUsage);
	}
	
	private ServicePricing smsPricing, mmsPricing, voiceCallPricing;
	
	public Subscription(ServicePricing smsPricing, ServicePricing mmsPricing, ServicePricing voiceCallPricing) {
		this.smsPricing = smsPricing;
		this.mmsPricing = mmsPricing;
		this.voiceCallPricing = voiceCallPricing;
	}

	private ServicePricing getServicePricing(ServiceUsage serviceUsage) {
		if (serviceUsage instanceof Sms) {
			return smsPricing;
		} else if (serviceUsage instanceof Mms) {
			return mmsPricing;
		} else if (serviceUsage instanceof VoiceCall) {
			return voiceCallPricing;
		}
		throw new IllegalArgumentException("No pricing found for " + serviceUsage);
	}
	
	public int printUsages() {
		int totalPrice = 0;
		for (ServiceUsage serviceUsage : serviceUsages) {
			ServicePricing servicePricing = getServicePricing(serviceUsage);
			int usagePrice = serviceUsage.getPrice(servicePricing.getPricePrUsage(), servicePricing.getPricePrChunk());
			System.out.println("Usage of " + serviceUsage.getUnitCount() + " " + serviceUsage.getUnitType() + " cost " + usagePrice);
			totalPrice += usagePrice;
		}
		return totalPrice;
	}
	
	public static void main(String[] args) {
		Subscription subscription = new Subscription(new ServicePricing(0, 59), new ServicePricing(0, 590), new ServicePricing(99, 59));
		subscription.addServiceUsage(new Sms("Hei"));
		subscription.addServiceUsage(new Mms(new byte[2024]));
		subscription.addServiceUsage(new VoiceCall(185));
		int totalPrice = subscription.printUsages();
		System.out.println("Total price: " + totalPrice);
	}
}
