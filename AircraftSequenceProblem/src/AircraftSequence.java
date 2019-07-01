
public class AircraftSequence {
	public static String[]flight_name= {"VLE7100","VLE7096","AZA2061","ONG254 ","ISS1508","SMX5297","VLE7110","CYL5001","AZA410 ","VLE7088",
			"AZA2063","NJE210A","CYL5022","AZA080 ","BAW14GM","ICFLY  ","ISS1205","SMX5985","BEL7PC ","SNM643 ","AZA2092","DLH1XK ","ISS223 ","AZA79A ",
			"DEPPG  ","SNM834 ","ICAFD  ","AZA2097","AZA358 ","VLE7046","KLM1621","AZA1647","AZA2088","AZA2099","SNM833 ","ADH2913","SNM644 ","OOELI  ",
			"DLH276 ","ISS1206","AZA1727","VLE7008","AZA2DJ ","VLE7048","ADH6856","AZA2050","BEL10D ","ICDBS  ","AOE402 ","AZA2109","AUF413 ","ADH7VG "
			,"VLE7101","AZA9HM ","BAW47NL","SMX5610","AFR2112","AZA2102","INGIR  ","YUBZZ  "};

	public static int[] flight_time= {1455,1455,1500,1502,1505,1515,1515,1518,1525,1525,1530,1531,1534,1535,1535,1535,1537,1537,1542,
			1547,1547,1550,1555,1555,1558,1600,1600,1600,1610,1613,1614,1615,1617,1620,1626,1628,1630,1630,1631,1635,1635,1639,1640,1641,1645
			,1649,1650,1652,1700,1700,1700,1701,1702,1708,1710,1715,1722,1727,1728,1730};
	public static int[] flight_weight= {5,5,5,2,6,4,6,5,3,4,5,1,5,5,4,2,4,5,3,1,4,4,6,5,1,2,2,
			5,4,5,6,6,5,5,2,5,1,3,6,5,4,6,5,4,5,4,3,1,1,4,1,5,6,5,4,5,5,4,1,1};
	public static int[] flight_time1= {1455,1455,1500,1502,1505,1515,1515,1518,1525,1525,1530,1531,1534,1535,1535,1535,1537,1537,1542,
			1547,1547,1550,1555,1555,1558,1600,1600,1600,1610,1613,1614,1615,1617,1620,1626,1628,1630,1630,1631,1635,1635,1639,1640,1641,1645
			,1649,1650,1652,1700,1700,1700,1701,1702,1708,1710,1715,1722,1727,1728,1730};
	public static int[] delay=new int[flight_time.length];
	public static int[]penalty=new int[flight_weight.length];
	public static int conflicts=0;




	public static void checkFlights() {
		for (int i = 0; i < flight_time.length; i++) {
				for(int j=i+1;j<flight_time.length;j++) {
					
					if(flight_time[j]==flight_time[i]) {
						conflicts+=1;
						int a=checkWeight(i,j);
						delay( i, j,a);
					
					}
					
				}
		}
	}
	public static void delay(int i,int j,int state) {
		switch(state) {
		case 0:	flight_time[j]+=2;	break;
		case 1: flight_time[j]+=2;	break;
		case 2: flight_time[i]+=2;	break;
		
		}
	}
	public static int checkWeight(int i,int j) {
		int result;
		if(flight_weight[i]>flight_weight[j])
		{
			result=0;//aeroplani 'I'-te ka rendesi me te madhe se ai J-te => aeroplani j vonohet 2 min me vone
			
		}
		else if(flight_weight[i]==flight_weight[j]) {
			result=1; // aeroplanat jane te rendesise se njejte aeroplani j vonohet 2 min;
		}
		else {
			result=2;// aeroplani j ka rendesi me te madhe se ai i =>aeroplani i vonohet 2 min;
		}
	return result;
	}
		public static void setDelay() {
			for (int i = 0; i < delay.length; i++) {
				delay[i]=flight_time[i]-flight_time1[i];
			}
		}
	public static void calculatePenaltyForEachPlane() {
		for (int i = 0; i < penalty.length; i++) {
			penalty[i]=delay[i]*flight_weight[i];
		}
	}
	public static int calculateTotalPenalty() {
		int result=0;
		for (int i = 0; i < penalty.length; i++) {
			result=result+penalty[i];
		}
		return result;
	}
	public static int checkConflicts() {
		int result=0;
		for (int i = 0; i < flight_time.length; i++) {
			for(int j=i+1;j<flight_time.length;j++) {
				
				if(flight_time[j]==flight_time[i]) {
					result+=1;
					
				
				}
				
			}
	}
	return result;
	}
	public static void sortFlights(){
		String tempName;
		int tempDelay;
		int tempPenalty;
		int tempTime;
		int tempWeight;

		String temp1;
		for(int i=0;i<flight_time.length;i++) 
		{
			for(int j=i+1 ;j<flight_time.length;j++) {
		
			
			if(flight_time[j]<flight_time[i]) {
				tempTime=flight_time[i];
				tempName=flight_name[i];
            tempWeight=flight_weight[i];
				tempDelay=delay[i];
				tempPenalty=penalty[i];
				flight_time[i]=flight_time[j];
				flight_name[i]=flight_name[j];
            flight_weight[i]=flight_weight[j];
				delay[i]=delay[j];
				penalty[i]=penalty[j];
				flight_name[j]=tempName;
				flight_time[j]=tempTime;
            flight_weight[j]=tempWeight;
				delay[j]=tempDelay;
				penalty[j]=tempPenalty;		
			}
		
		
		 
		}
		}

	}
		public static void main(String[] args) {
			for(int i=0;i<flight_time.length;i++) {
				checkFlights();
			}
			setDelay();
			calculatePenaltyForEachPlane();
			int totalPenalty=calculateTotalPenalty();
			sortFlights();
			
			System.out.println("Fluturimi  "+ " Koha "+" Pesha " +" Vonesa "+ " Ndeshkimi");
			for (int i = 0; i <flight_name.length; i++) {
				System.out.println(flight_name[i]+"     "+flight_time[i]+"    "+flight_weight[i]+"    "+ delay[i] +"        "+penalty[i]);
			}
			System.out.println("Numri total i konflikteve para zgjidhjes: " +conflicts);
			System.out.println("Numri total i konflikteve pas zgjidhjes: "+ checkConflicts());
			System.out.println("Konfliktet u zgjidhen me nje kosto prej: "+totalPenalty);
			
			

		}
}
