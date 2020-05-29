import java.util.ArrayList;

public class ResourceCentre_no_input {

	public static void main(String[] args) {
		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();

		camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));

		ResourceCentre.menu();
		
		System.out.println("****** Testing Option 1 ******");
		ResourceCentre.viewAllCamcorder(camcorderList);
		ResourceCentre.viewAllChromebook(chromebookList);

		System.out.println("****** Testing Option 2 ******");
		ResourceCentre.itemTypeMenu();
		Camcorder cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		
		//System.out.println("****** Testing Option 3 ******");
		
		//System.out.println("****** Testing Option 4 ******");
	}

}
