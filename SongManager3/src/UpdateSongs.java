import manager.PhoneSongList;
import manager.SongFileManager;

public class UpdateSongs {

	public static void main(String[] args) throws Exception {
		PhoneSongList phoneSongList = new PhoneSongList();
		SongFileManager songFileManager = new SongFileManager(phoneSongList);
		songFileManager.update();
		phoneSongList.save();
	}
}
