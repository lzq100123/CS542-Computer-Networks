package edu.iit.cs542;

public class NetworkGraph {
	protected int[] distribution = { 0, 0, 0, 0, 0, 0, };// Each elements
															// represent the
															// number of
															// packet in
															// ABC, ADC,
															// BCD, BAD,
															// CBA, CDA
	protected int[] counter = { 0, 0, 0, 0, 0, 0, };// Each elements represents
														// the counter of all
														// arriving packets in
														// ABC, ADC, BCD, BAD,
														// CBA, CDA

	public NetworkGraph() {
	
	}

	public String toString() {
		return "{ AC : ABC[" + distribution[0] + "]," + "ADC:[" + distribution[1] + "]}" + ";{ BD :BCD["
				+ distribution[2] + "]" + ",BAD[" + distribution[3] + "]}" + ";{ CA :CDA[" + distribution[4] + "]"
				+ ",CBA[" + distribution[5] + "]}";

	}
}
