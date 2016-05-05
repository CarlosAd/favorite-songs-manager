package song;

import java.io.PrintWriter;
import utils.PathFinder;

public abstract class Song {
	protected String path_;
	protected String artistName_;
	protected String songName_;
	
	public Song(String path, String artistName, String songName){
		this.path_ = path;
		this.artistName_ = artistName;
		this.songName_ = songName;
	}
	
	public Song(PathFinder pathFinder){
		this.path_ = pathFinder.getPath();
		this.artistName_ = pathFinder.getArtistName();
		this.songName_ = pathFinder.getSongName();
	}
	
	public String getPath() {
		return path_;
	}
	public String getArtistName() {
		return artistName_;
	}
	public void setArtistName(String artistName_) {
		this.artistName_ = artistName_;
	}
	public String getSongName() {
		return songName_;
	}
	public void setSongName(String songName_) {
		this.songName_ = songName_;
	}
	
	public boolean isEqual(Song song) {
		return this.path_.equals(song.getPath());
	}
	
	public abstract void print();
	public abstract void writeToFile(PrintWriter writer);
}