package edu.iit.cs542;

public class TimeSlot {
	protected Packet[] packets = {null,null,null};// packets array, packets[0] is packet in AC,
								// packets[1] is packet in BD,
								// packets[2] is packet in CA

	public TimeSlot(Packet pac, Packet pbd, Packet pca) {
		packets[0] = pac;
		packets[1] = pbd;
		packets[2] = pca;
		
	}

	public String toString() {
		return "[packetAC:" + (packets[0] == null ? "null" : packets[0].toString()) + "]; " + "[packetBD:"
				+ (packets[1] == null ? "null" : packets[1].toString()) + "]; " + "[packetCA:"
				+ (packets[2] == null ? "null" : packets[2].toString() + "]; ");
	}
}
