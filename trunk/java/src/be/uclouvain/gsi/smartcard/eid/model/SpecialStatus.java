package be.uclouvain.gsi.smartcard.eid.model;

public enum SpecialStatus {
	NoStatus("0"),
	Blind("1"),
	ExtendedMinority("2"),
	BlindAndExtendedMinority("3"),
	PartiallyBlind("4"),
	PartiallyBlindAndExtendedMinority("5");
	
	private final String value;

	private SpecialStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}