package edu.iit.cs542;

public class Simulator {

protected static CS542Queue<TimeSlot> timeStream = new CS542Queue<TimeSlot>(12);

	protected static NetworkGraph network = new NetworkGraph();

	protected static long numberOfTimeSlot = 400000;
	protected static int lengthOfqueue = 1200;

	protected static double cost = 1000000;
	protected static double alfa = 1;
	protected static double beta = 1;
	protected static double gamma = 1;

	/**
	 * This Simulator following these steps: 
	 * 1. Dequeue a time slot from each
	 * three queues 
	 * 2. If the time slot contains a packets, get info for new
	 * packet 
	 * 3. Generate time slot and fill with the info of packet 
	 * 5, enqueue the packet
	 */
	public static void main(String[] args) {
		initQueue();

		for (int i = 0; i < numberOfTimeSlot; i++) {
			TimeSlot newSlot = new TimeSlot(null, null, null);
			TimeSlot passSlot = timeStream.remove();
			// dnqueue a timeslot

			for (int j = 0; j < 3; j++) {// check all the packets in
											// timeslot
				if (passSlot.packets[j] != null) {
					// If a packet arrive in destination, a new packet
					// send
					// out from source
					newSlot.packets[j] = sendNewPacket(passSlot.packets[j].path);
					// reduce one from the number of corresponding path
					// packets
					int pi = Path_Type.get_path_index(passSlot.packets[j].path);
					network.distribution[pi]--;
				}
			}
			timeStream.add(newSlot);
		}

		alfa = (double) network.counter[0] / (network.counter[0] + network.counter[1]);
		gamma = ((double) network.counter[2] / (network.counter[2] + network.counter[3]));
		beta = (double) network.counter[4] / (network.counter[4] + network.counter[5]);
		System.out.println("For " + numberOfTimeSlot + " time slots we have:\n\nafa is:" + alfa + "\ngamma is:" + gamma
				+ "\nbeta is:" + beta+"\n\n");

		System.out.println(Strategic.getNetworkInfor(network, lengthOfqueue, alfa, gamma, beta));

	}

	/**
	 * Initial the time stream queue
	 */
	private static void initQueue() {
		for (int i = 0; i < lengthOfqueue; i++) {
			TimeSlot timeslot = new TimeSlot(null, null, null);

			if (i % 3 == 0) {// send an packet from A to C
				timeslot.packets[0] = sendNewPacket(Path_Type.ABC);
			}
			if (i % 4 == 0) {// send an packet from B to D
				timeslot.packets[1] = sendNewPacket(Path_Type.BCD);
			}
			if (i % 6 == 0) {// send an packet from C to A
				timeslot.packets[2] = sendNewPacket(Path_Type.CDA);
			}
			timeStream.add(timeslot);
		}
	}

	/**
	 * Sent a new packet from the same source of arrived packet using the
	 * strategic of chosing the smaller utilization
	 * 
	 * @param type
	 *            -the path of arrived packet
	 * @return -the sent packet
	 */
	protected static Packet sendNewPacket(Path_Type type) {
		Packet packet = new Packet();
		Strategic startegic = new Strategic();
		// packet.type = type;
		// packet_index of the arrived packet
		int packet_index = Path_Type.get_path_index(type);
		// pccket_index of the sent packet
		packet_index = (packet_index / 2) * 2 + startegic.choseThePath(network, packet_index);

		packet.path = Path_Type.get_path_name(packet_index);
		network.counter[packet_index]++;
		network.distribution[packet_index]++;

		return packet;
	}

}
