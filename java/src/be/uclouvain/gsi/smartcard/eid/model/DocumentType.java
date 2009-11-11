package be.uclouvain.gsi.smartcard.eid.model;

public enum DocumentType {
	BelgianCitizen("1"),
	EuropeanCommunityCitizen("2"),
	nonEuropeanCommunityCitizen("3"),
	bootstrapCard("7"),
	habilitationMachtigingsCard("8");
	
	private final String value;

	private DocumentType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
