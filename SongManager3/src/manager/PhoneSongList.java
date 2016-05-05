package manager;

import java.util.Scanner;

import song.PhoneSong;
import song.Song;
import utils.PathFinder;

public class PhoneSongList extends SongList {
	
	public PhoneSongList() {
		super();
		this.filename = "/home/carlos/workspace/SongManager3/phone.txt";
		this.realDirectory = "/storage/extSdCard/Musicas/";
		this.load();
	}

	@Override
	protected Song recoverFromFile(Scanner reader) {
		String path, artistName, songName;
		path = reader.next();
		artistName = reader.next();
		songName = reader.next();
		
		return new PhoneSong(path, artistName, songName);
	}
	
	public void addFromVLC(PathFinder pathFinder) {
		this.add(new PhoneSong(pathFinder));
	}

	@Override
	public void removeFromDirectory(int d) throws Exception, Exception {
		String path = this.realDirectory;
		String[] cmd = {"adb", "shell", "rm", "-rf", ""};
		Runtime runtime = Runtime.getRuntime();
		
		for (int i = 0; i < d; i++) {
			cmd[4] = path + this.songs.get(i).getArtistName() + "/" + this.songs.get(i).getSongName() + ".mp3";
			runtime.exec(cmd).waitFor();
		}
		
		this.numberOfCopiedSongs -= d;
	}

	@Override
	public void removeFromList(int e) {
		int i = 0;
		while (i < e) {
			this.songs.remove(0);
			i++;
		}
	}

	@Override
	public void copySongs() throws Exception, Exception {
		String[] cmd = {"adb", "push", "", ""};
		Runtime runtime = Runtime.getRuntime();
		Process process;
		
		for (int i = this.numberOfCopiedSongs; i < this.songs.size(); i++) {
			cmd[2] = this.songs.get(i).getPath();
			cmd[3] = this.realDirectory + this.songs.get(i).getArtistName() + "/" + this.songs.get(i).getSongName() + ".mp3";
			process = runtime.exec(cmd);
			process.waitFor();
			if (process.exitValue() == 0) {
				System.out.println("Song: " + this.songs.get(i).getSongName() + " - " + this.songs.get(i).getArtistName() + "\t status = copied.\n");
				this.numberOfCopiedSongs++;
			}
			else {
				System.out.println("Error copying song '" + this.songs.get(i).getSongName() + " - " + this.songs.get(i).getArtistName() + "' to device.\n");
				System.out.println("Copying process terminated.\n");
				break;
			}
		}
	}
}
