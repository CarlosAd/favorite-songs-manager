package song;

import java.io.PrintWriter;

import utils.Constant;
import utils.PathFinder;

public class USBSong extends Song {
	private int level_;

	public USBSong(String path, String artistName, String songName, int level) {
		super(path, artistName, songName);
		this.level_ = level;
	}
	
	public USBSong(PathFinder pathFinder, int level) {
		super(pathFinder);
		this.level_ = level;
	}
	
	public int getLevel() {
		return level_;
	}
	public void setLevel(int level) {
		this.level_ = level;
	}

	@Override
	public void print() {
		System.out.println(path_ + "\t" + 
				artistName_ + "\t" +
				songName_ + "\t" +
				level_);
	}
	
	@Override
	public void writeToFile(PrintWriter writer) {
		writer.println(this.path_ + Constant.delimiter +
				this.artistName_ + Constant.delimiter +
				this.songName_ + Constant.delimiter +
				Integer.toString(this.level_) + Constant.delimiter);
	}
}
