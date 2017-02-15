package edu.iit.cs542;

public class NetworkGraph {
	protected int packetNumberInABC;
	protected int packetNumberInADC;
	protected int packetNumberInBCD;
	protected int packetNumberInBAD;
	protected int packetNumberInCBA;
	protected int packetNumberInCDA;

	protected int acountABC;
	protected int acountADC;
	protected int acountBCD;
	protected int acountBAD;
	protected int acountCBA;
	protected int acountCDA;

	public NetworkGraph() {
		packetNumberInABC = 0;
		packetNumberInADC = 0;
		packetNumberInBCD = 0;
		packetNumberInBAD = 0;
		packetNumberInCBA = 0;
		packetNumberInCDA = 0;

		acountABC = 0;
		acountADC = 0;
		acountBCD = 0;
		acountBAD = 0;
		acountCBA = 0;
		acountCDA = 0;
	}

	public String toString() {
		return "{ AC : ABC[" + packetNumberInABC + "]," + "ADC:["
				+ packetNumberInADC + "]}" + ";{ BD :BCD[" + packetNumberInBCD
				+ "]" + ",BAD[" + packetNumberInBAD + "]}" + ";{ CA :CDA["
				+ packetNumberInCDA + "]" + ",CBA[" + packetNumberInCBA + "]}";

	}
}
