package edu.iit.cs542;

public class Packet {
	protected Packet_Type type;
	protected Path_Type path;
	
	public String toString(){
		return "Type:"+type+", "+"path:"+ path+" ";
	}
}
