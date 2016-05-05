package song;

import java.io.PrintWriter;

import utils.Constant;
import utils.PathFinder;

public class PhoneSong extends Song {
	
	public PhoneSong(String path, String artistName, String songName){
		super(path, artistName, songName);
	}

	public PhoneSong(PathFinder pathFinder) {
		super(pathFinder);
	}
	
	@Override
	public void print() {
		System.out.println(getPath() + "\t" + 
				getArtistName() + "\t" +
				getSongName());
	}

	@Override
	public void writeToFile(PrintWriter writer) {
		writer.println(this.path_ + Constant.delimiter +
				this.artistName_ + Constant.delimiter +
				this.songName_ + Constant.delimiter);
	}
}
