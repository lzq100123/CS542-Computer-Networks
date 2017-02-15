package edu.iit.cs542;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Simulator {

	protected static CS542Queue<TimeSlot> timeStream = new CS542Queue<TimeSlot>(
			12);

	protected static NetworkGraph network = new NetworkGraph();
	// protected static Queue<Packet> BDqueue = new PriorityQueue<Packet>(12);
	// protected static Queue<Packet> CAqueue = new PriorityQueue<Packet>(12);

	protected static long numberOfTimeSlot = 1000;
	/**
	 * This Simulator following these steps: 1. Dequeue a time slot from each
	 * three queues 2. If the time slot contains a packets, get info for new
	 * packet 3. If the time slot contains nothing 4. Generate time slot and
	 * fill with the info of packet 5, enqueue the packet
	 */
	public static void main(String[] args) {
		initQueue();
		File f = new File("/Users/matthewxfz/Workspaces/542/cs542project.log");
		try {
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");

			for (int i = 0; i < numberOfTimeSlot; i++) {
				TimeSlot newSlot = new TimeSlot();
				TimeSlot passSlot = timeStream.remove();
				// dnqueue a timeslot

				if (passSlot.packetAC != null) {
					newSlot.packetAC = sendNewPacket(passSlot.packetAC.type);
					if (passSlot.packetAC.path == Path_Type.ABC) {
						network.packetNumberInABC--;
					} else {
						network.packetNumberInADC--;
					}
					
				}
				if (passSlot.packetBD != null) {
					newSlot.packetBD = sendNewPacket(passSlot.packetBD.type);
					if (passSlot.packetBD.path == Path_Type.BCD) {
						network.packetNumberInBCD--;
					} else {
						network.packetNumberInBAD--;
					}
				
				}
				if (passSlot.packetCA != null) {
					newSlot.packetCA = sendNewPacket(passSlot.packetCA.type);
					if (passSlot.packetCA.path == Path_Type.CBA) {
						network.packetNumberInCBA--;
					} else {
						network.packetNumberInCDA--;
					}
					
				}

				writer.append("[" + i + "]\n" + "dequeue"
						+ (passSlot == null ? "null" : passSlot.toString())
						+ "\n");
				writer.append("Intensity Distribution:" + network.toString()
						+ "\n");
				writer.append("Network Utilization:"
						+ (new Strategic()).toString(network) + "\n");
				writer.append("enqueue"
						+ (newSlot == null ? "null" : newSlot.toString())
						+ "\n");
				// enqueue the new timeslot
				timeStream.add(newSlot);
			}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double afa = (double)Math.round((double)network.acountABC/(network.acountABC+network.acountADC)*100)/100;
		double gamma = (double)Math.round((double)network.acountBCD/(network.acountBCD+network.acountBAD)*100)/100;
		double beta = (double)Math.round((double)network.acountCDA/(network.acountCDA+network.acountCBA)*100)/100;
		System.out.println("For "+numberOfTimeSlot+" time slots we have:\nafa is:"+afa+
				"\ngamma is:"+gamma+
				"\nbeta is:"+beta);
	}

	/**
	 * Fill the queue with packets when the transmission begins
	 */
	private static void initQueue() {
		for (int i = 0; i < 12; i++) {
			TimeSlot timeslot = new TimeSlot();
			if (i % 3 == 0) {// send an AC packet
				timeslot.packetAC = sendNewPacket(Packet_Type.AC);
			}
			if (i % 4 == 0) {// send an BD packet
				timeslot.packetBD = sendNewPacket(Packet_Type.BD);
			}
			if (i % 6 == 0) {// send an CA packet
				timeslot.packetCA = sendNewPacket(Packet_Type.CA);
			}
			timeStream.add(timeslot);
		}
	}

	protected static Packet sendNewPacket(Packet_Type type) {
		Packet packet = new Packet();
		Strategic startegic = new Strategic();
		packet.type = type;
		
		if (type == Packet_Type.AC) {
			if (startegic.choseThePath(network, Packet_Type.AC)) {
				packet.path = Path_Type.ABC;
				network.acountABC++;
				network.packetNumberInABC++;
			} else {
				packet.path = Path_Type.ADC;
				network.acountADC++;
				network.packetNumberInADC++;
			}
		} else if (type == Packet_Type.BD) {
			if (startegic.choseThePath(network, Packet_Type.BD)) {
				packet.path = Path_Type.BCD;
				network.acountBCD++;
				network.packetNumberInBCD++;
			} else {
				packet.path = Path_Type.BAD;
				network.acountBAD++;
				network.packetNumberInBAD++;
			}

		} else if (type == Packet_Type.CA) {
			if (startegic.choseThePath(network, Packet_Type.CA)) {
				packet.path = Path_Type.CDA;
				network.acountCDA++;
				network.packetNumberInCDA++;
			} else {
				packet.path = Path_Type.CBA;
				network.acountCBA++;
				network.packetNumberInCBA++;
			}
		}
		return packet;
	}
}
