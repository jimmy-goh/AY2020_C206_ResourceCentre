import java.util.ArrayList;

public class ResourceCentre {

	public static void main(String[] args) {

		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();

		camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));

		int option = 0;

		while (option != 5) {

			ResourceCentre.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == 1) {
				// View all items
				ResourceCentre.viewAllCamcorder(camcorderList);
				ResourceCentre.viewAllChromebook(chromebookList);

			} else if (option == 2) {

				// Add item
				ResourceCentre.itemTypeMenu();
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					
					// Add camcorder
					Camcorder cc = ResourceCentre.inputCamcorder();
					ResourceCentre.addCamcorder(camcorderList, cc);
					//ResourceCentre.addCamcorder(camcorderList);

				} else if (itemType == 2) {

					// Add Chromebook
					ResourceCentre.addChromebook(chromebookList);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 3) {

				// Loan item
				ResourceCentre.setHeader("LOAN");
				ResourceCentre.itemTypeMenu();

				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {

					// Loan camcorder
					ResourceCentre.loanCamcorder(camcorderList);

				} else if (itemType == 2) {

					// Loan Chromebook
					ResourceCentre.loanChromebook(chromebookList);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 4) {

				// Return item
				ResourceCentre.setHeader("RETURN");
				ResourceCentre.itemTypeMenu();

				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {

					// Return camcorder
					ResourceCentre.returnCamcorder(camcorderList);

				} else if (itemType == 2) {

					// Return Chromebook
					ResourceCentre.returnChromebook(chromebookList);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 5) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}

	public static void menu() {
		ResourceCentre.setHeader("RESOURCE CENTRE APP");
		System.out.println("1. Display Inventory");
		System.out.println("2. Add item");
		System.out.println("3. Loan item");
		System.out.println("4. Return item");
		System.out.println("5. Quit");
		Helper.line(80, "-");

	}
	
	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static void itemTypeMenu() {
		ResourceCentre.setHeader("ITEM TYPES");
		System.out.println("1. Camcorder");
		System.out.println("2. Chromebook");
	}

	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.setHeader("CAMCORDER LIST");
		String output = String.format("%-10s %-20s %-20s %-10s %-10s \n", "ASSET TAG", "DESCRIPTION", "OPTICAL ZOOM",
				"AVAILABLE", "DUE DATE");

		for (int i = 0; i < camcorderList.size(); i++) {

			output += String.format("%-10s %-20s %-20d %-10s %-10s \n", camcorderList.get(i).getAssetTag(),
					camcorderList.get(i).getDescription(), camcorderList.get(i).getOpticalZoom(),
					ResourceCentre.showAvailability(camcorderList.get(i).getIsAvailable()),
					camcorderList.get(i).getDueDate());
		}
		System.out.println(output);
	}

	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {
		ResourceCentre.setHeader("CHROMEBOOK LIST");
		String output = String.format("%-10s %-20s %-20s %-10s %-10s \n", "ASSET TAG", "DESCRIPTION",
				"OPERATING SYSTEM", "AVAILABLE", "DUE DATE");

		for (int i = 0; i < chromebookList.size(); i++) {
			output += String.format("%-10s %-20s %-20s %-10s %-10s \n", chromebookList.get(i).getAssetTag(),
					chromebookList.get(i).getDescription(), chromebookList.get(i).getOs(),
					ResourceCentre.showAvailability(chromebookList.get(i).getIsAvailable()),
					chromebookList.get(i).getDueDate());
		}
		System.out.println(output);
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} else {
			avail = "No";
		}
		return avail;
	}

	public static Camcorder inputCamcorder() {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		int zoom = Helper.readInt("Enter optical zoom > ");

		Camcorder cc= new Camcorder(tag, description, zoom);
		return cc;
		
	}

	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {
		
		camcorderList.add(cc);
		System.out.println("Camcorder added");
	}

	public static void addChromebook(ArrayList<Chromebook> chromebookList) {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		String os = Helper.readString("Enter operating system > ");

		chromebookList.add(new Chromebook(tag, description, os));
		System.out.println("Chromebook added");
	}

	public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		boolean isLoaned = false;

		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
					&& camcorderList.get(i).getIsAvailable() == true) {
				String due = Helper.readString("Enter due date > ");
				camcorderList.get(i).setIsAvailable(false);
				camcorderList.get(i).setDueDate(due);
				isLoaned = true;
				System.out.println("Camcorder " + tag + " loaned out");
			}
		}
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		}
	}

	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		boolean isLoaned = false;

		for (int i = 0; i < chromebookList.size(); i++) {
			if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag())
					&& chromebookList.get(i).getIsAvailable() == true) {
				String due = Helper.readString("Enter due date > ");
				chromebookList.get(i).setIsAvailable(false);
				chromebookList.get(i).setDueDate(due);
				isLoaned = true;
				System.out.println("Chromebook " + tag + " loaned out");
			}
		}
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		}
	}

	public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		boolean isReturned = false;

		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
					&& camcorderList.get(i).getIsAvailable() == false) {
				camcorderList.get(i).setIsAvailable(true);
				camcorderList.get(i).setDueDate("");
				isReturned = true;
				System.out.println("Camcorder " + tag + " returned");
			}
		}
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		}
	}

	public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		boolean isReturned = false;

		for (int i = 0; i < chromebookList.size(); i++) {
			if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag())
					&& chromebookList.get(i).getIsAvailable() == false) {
				chromebookList.get(i).setIsAvailable(true);
				chromebookList.get(i).setDueDate("");
				isReturned = true;
				System.out.println("Chromebook " + tag + " returned");
			}
		}
		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		}
	}


}
