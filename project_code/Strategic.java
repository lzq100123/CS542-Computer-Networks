package edu.iit.cs542;

import java.util.Random;

public class Strategic {
	public Strategic(){}
	
	/**
	 * Chose the path whose utilization is lower than the others
	 * @param network -the network graph
	 * @param pindex -the start vertex
	 * @return return 0 for chose the clockwise direction, and 1 for anticlockwise direction
	 */
	public int choseThePath(NetworkGraph network, int pindex){
		return Utilization(pindex,network);
	}

	public int Utilization(int pi, NetworkGraph network) {
		double alfa = (double) network.distribution[0] / (network.distribution[0] + network.distribution[1]) ;
		double gamma = ((double) network.distribution[2] / (network.distribution[2] + network.distribution[3]) );
		double beta = (double) network.distribution[4] / (network.distribution[4] + network.distribution[5]) ;
	
		double  abc = 4*alfa, 
				adc = 4-abc,
				bcd = 3*gamma,
				bad = 3-bcd,
				cda = 2*beta,
				cba = 2-cda;
		
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
		
		if ((pi == 0 || pi == 1) &&Uabc >= Uadc) {
			return 1;
		} else if ((pi == 2 || pi == 3) && Ubcd >= Ubad) {
			return 1;
		} else if ((pi == 4 || pi == 5) && Ucda >= Ucba) {
			return 1;
		} else {
			return 0;
		}
	}
	public String toString(NetworkGraph network) {
		double alfa = (double) network.distribution[0] / (network.distribution[0] + network.distribution[1]) ;
		double gamma = ((double) network.distribution[2] / (network.distribution[2] + network.distribution[3]) );
		double beta = (double) network.distribution[4] / (network.distribution[4] + network.distribution[5]) ;
		
		double  abc = 4*alfa, 
				adc = 4-abc,
				bcd = 3*gamma,
				bad = 3-bcd,
				cda = 2*beta,
				cba = 2-cda;
		
		double Uab = abc / 3, Ubc = (abc + bcd) / 5, Uad = (adc + bad) / 2, Udc = adc / 6, Uba = (bad + cba) / 4,
				Ucb = cba / 3, Uda = cda / 5, Ucd = bcd + cda, Uabc = Uab + Ubc, Uadc = Uad + Udc, Ubcd = Ubc + Ucd,
				Ubad = Uba + Uad, Ucba = Ucb + Uba, Ucda = Ucd + Uda;

		return "{"+alfa+", "+gamma+", "+beta+"}";
	}
	
	public static String getNetworkInfor(NetworkGraph network, int lengthOfqueue, double alfa, double gamma, double beta){
		double  abc = 4*alfa, 
				adc = 4-abc,
				bcd = 3*gamma,
				bad = 3-bcd,
				cda = 2*beta,
				cba = 2-cda;
	
			
		double  Iab = abc, 
				Ibc = (abc + bcd) , 
				Iad = (adc + bad), 
				Idc = adc , 
				Iba = (bad + cba) , 
				Icb = cba , 
				Ida = cda , 
				Icd = bcd + cda, 
				Uab = abc / 3, 
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
		
		return "This is the linke info:\n"
				+ "{Iab:"+Iab+", Uab="+Uab+"}\n"+
		"{Iba:"+Iba+", Uba="+Uba+"}\n"+
		"{Ibc:"+Ibc+", Ubc="+Ubc+"}\n"+
		"{Icb:"+Icb+", Ucb="+Ucb+"}\n"+
		"{Icd:"+Icd+", Ucd="+Ucd+"}\n"+
		"{Idc:"+Idc+", Udc="+Udc+"}\n"+
		"{Ida:"+Ida+", Uda="+Uda+"}\n"+
		"{Iad:"+Iad+", Uad="+Uad+"}\n"+
		"\n\nThis is all the transit paths info:\n"+
		"{Uabc:"+Uabc+"}\n"+"{Uadc:"+Uadc+"}\n"+
		"{Ubcd:"+Ubcd+"}\n"+"{Ubad:"+Ubad+"}\n"+
		"{Ucba:"+Ucba+"}\n"+"{Ucda:"+Ucda+"}\n"+
		"\nThe total cost:"+(Uabc+Uadc+Ubcd+Ubad+Ucba+Ucda);
	}
	
}
