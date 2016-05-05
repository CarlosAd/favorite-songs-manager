import java.util.Scanner;

import manager.PhoneSongList;
import manager.USBSongList;
import utils.Constant;
import utils.PathFinder;

public class SaveSongs {

	public static void main(String[] args) throws Exception {
		Scanner reader = new Scanner(System.in);
		PhoneSongList phoneSongList = new PhoneSongList();
		USBSongList usbSongList = new USBSongList();
		int option;
		String username = "";
		String password = "vlc";
		
		while (true) {	
			displayMenu();
			option = reader.nextInt();
			
			switch(option) {
			case 1:
				phoneSongList.addFromVLC(new PathFinder(username, password));
				break;
			case 2:
				displayUSBSongMenu();
				option = reader.nextInt();
				usbSongList.addFromVLC(new PathFinder(username, password), option);
				break;
			default:
				phoneSongList.save();
				usbSongList.save();
				reader.close();
				System.exit(1);
			}
		}
	}

	private static void displayUSBSongMenu() {
		System.out.println("What level do you think the current song belongs to?\n" +
				Integer.toString(Constant.BAKEN) + " - Baken\n" +
				Integer.toString(Constant.STUDY) + " - Study\n" +
				Integer.toString(Constant.PACEDOWN) + " - Pacedown\n" +
				Integer.toString(Constant.FORBIDDEN) + " - Forbidden\n");
	}

	private static void displayMenu() {
		System.out.println("Welcome to the Song Manager!");
		System.out.println("What do you intend to do now?\n" + 
				"1 - Add a song to the phone list\n" +
				"2 - Add a song to the usb list\n" +
				"3 - Quit\n");
	}
}
