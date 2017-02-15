package edu.iit.cs542;

public class TimeSlot {
	protected Packet packetAC;
	protected Packet packetBD;
	protected Packet packetCA;
	
	public TimeSlot(){
		packetAC = null;
		packetBD = null;
		packetCA = null;
	}
	
	public String toString(){
		return "[packetAC:"+(packetAC == null? "null":packetAC.toString())+"]; "+
				 "[packetBD:"+(packetBD == null? "null":packetBD.toString())+"]; "+
				 "[packetCA:"+(packetCA == null? "null":packetCA.toString()+"]; ");
	}
}
