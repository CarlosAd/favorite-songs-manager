package manager;

import java.util.Scanner;

import song.Song;
import song.USBSong;
import utils.PathFinder;

public class USBSongList extends SongList{
	public USBSongList() {
		super();
		this.filename = "/home/carlos/workspace/SongManager3/USB.txt";
		this.realDirectory = "/media/carlos/???/Musicas/";
		this.load();
	}

	@Override
	protected Song recoverFromFile(Scanner reader) {
		String path, artistName, songName;
		int level;
		
		path = reader.next();
		artistName = reader.next();
		songName = reader.next();
		level = Integer.parseInt(reader.next());
		
		return new USBSong(path, artistName, songName, level);
	}
	
	public void addFromVLC(PathFinder pathFinder, int level) {
		this.add(new USBSong(pathFinder, level));
	}

	@Override
	public void removeFromDirectory(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromList(int e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copySongs() {
		// TODO Auto-generated method stub
		
	}
}