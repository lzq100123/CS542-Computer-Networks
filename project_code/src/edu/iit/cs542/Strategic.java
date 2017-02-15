package edu.iit.cs542;

public class Strategic {

	public boolean choseThePath(NetworkGraph network, Packet_Type type){
		if (type == Packet_Type.AC) {
			return Utilization(type, network.packetNumberInABC,
					network.packetNumberInADC, network.packetNumberInBCD,
					network.packetNumberInBAD, network.packetNumberInCBA,
					network.packetNumberInCDA);
		}else if (type == Packet_Type.BD) {
			return Utilization(type, network.packetNumberInABC,
					network.packetNumberInADC, network.packetNumberInBCD,
					network.packetNumberInBAD, network.packetNumberInCBA,
					network.packetNumberInCDA);
		}else if (type == Packet_Type.CA) {
			return Utilization(type, network.packetNumberInABC,
					network.packetNumberInADC, network.packetNumberInBCD,
					network.packetNumberInBAD, network.packetNumberInCBA,
					network.packetNumberInCDA);
		}else{
			return true;
		}
	}

	public boolean Utilization(Packet_Type type, double abc, double adc,
			double bcd, double bad, double cba, double cda) {
			
		double  Uab = abc / 3, 
				Ubc = (abc + bcd) / 5, 
				Uad = (adc + bad) / 2, 
				Udc = adc / 6, 
				Uba = (bad + cba) / 4, 
				Ucb = cba / 3, 
				Uda = cda / 5, 
				Ucd = bcd + cda, 
				Uabc = Uab + Ubc, 
				Uadc = Uad + Udc, 
				Ubcd = Ubc + Ucd, 
				Ubad = Uba + Uad, 
				Ucba = Ucb + Uba, 
				Ucda = Ucd + Uda;
		
		if (type == Packet_Type.AC && Uabc >= Uadc) {
			return false;
		} else if (type == Packet_Type.BD && Ubcd >= Ubad) {
			return false;
		} else if (type == Packet_Type.CA && Ucda >= Ucba) {
			return false;
		} else {
			return true;
		}
	}
	
	public String toString(NetworkGraph network){
		
		return getNetworkInfo(network.packetNumberInABC,
				network.packetNumberInADC, network.packetNumberInBCD,
				network.packetNumberInBAD, network.packetNumberInCBA,
				network.packetNumberInCDA);
	}
	
	public String getNetworkInfo(double abc, double adc,
			double bcd, double bad, double cba, double cda){
		double  Uab = abc / 3, 
				Ubc = (abc + bcd) / 5, 
				Uad = (adc + bad) / 2, 
				Udc = adc / 6, 
				Uba = (bad + cba) / 4, 
				Ucb = cba / 3, 
				Uda = cda / 5, 
				Ucd = bcd + cda, 
				Uabc = Uab + Ubc, 
				Uadc = Uad + Udc, 
				Ubcd = Ubc + Ucd, 
				Ubad = Uba + Uad, 
				Ucba = Ucb + Uba, 
				Ucda = Ucd + Uda;
		
		return "{Uabc:"+Uabc+"},{Uadc:"+Uadc+"},{Ubcd:"+Ubcd+"},{Ubad:"+Ubad+"},{Ucba:"+Ucba+"},{Ucda:"+Ucda+"}";
	}
}
