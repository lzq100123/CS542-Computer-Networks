package edu.iit.cs542;

public enum Path_Type {
	ABC, ADC, BAD, BCD, CBA, CDA;
	public static int get_path_index(Path_Type name) {
		int v = -1;

		switch (name) {
		case ABC:
			v = 0;
			break;
		case ADC:
			v = 1;
			break;
		case BCD:
			v = 2;
			break;
		case BAD:
			v = 3;
			break;
		case CDA:
			v = 4;
			break;
		case CBA:
			v = 5;
			break;
		default:
			System.out.println("get_path_index: invalid name");
			break;
		}
		return v;
	}

	public static Path_Type get_path_name(int index) {
		Path_Type v = null;
		switch (index) {
		case 0:
			v = ABC;
			break;
		case 1:
			v = ADC;
			break;
		case 2:
			v = BCD;
			break;
		case 3:
			v = BAD;
			break;
		case 4:
			v = CDA;
			break;
		case 5:
			v = CBA;
			break;
		default:
			System.out.println("get_path_name: invalid index");
			break;
		}
		return v;
	}
}
