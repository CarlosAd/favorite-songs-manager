package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import song.Song;
import utils.Constant;

public abstract class SongList {
	protected int capacity;
	protected int numberOfCopiedSongs;
	protected List<Song> songs;
	protected String filename;
	protected String realDirectory;
	
	public SongList() {
		songs = new ArrayList<Song>();
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getNumberOfCopiedSongs() {
		return this.numberOfCopiedSongs;
	}
	
	public List<Song> getSongs() {
		return this.songs;
	}
	
	public Song get(int index) {
		return this.songs.get(index);
	}
	
	public void save() throws Exception, Exception {
		PrintWriter writer = new PrintWriter(this.filename, "UTF-8");
		
		writer.println(Integer.toString(this.capacity) + Constant.delimiter +
				Integer.toString(this.numberOfCopiedSongs) + Constant.delimiter);
		
		for (int i = 0; i < songs.size(); i++) {
			songs.get(i).writeToFile(writer);
		}
		
		writer.close();
	}
	
	protected boolean add(Song song) {
		if(!belongs(song)) {
			this.songs.add(song);
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean belongs(Song song) {
		for (int i = 0; i < this.songs.size(); i++) {
			if(song.isEqual(this.songs.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		System.out.println(Integer.toString(this.capacity) + ":" + Integer.toString(this.numberOfCopiedSongs));
		
		int size = this.songs.size();
		for (int i = 0; i < size; i++) {
			this.songs.get(i).print();
		}
	}
	
	protected void load() {
		Scanner reader;
		try {
			reader = new Scanner (new File(this.filename));
			reader.useDelimiter(Character.toString(Constant.delimiter));
			
			this.capacity = Integer.parseInt(reader.next());
			this.numberOfCopiedSongs = Integer.parseInt(reader.next());
			reader.nextLine();
			
			while (reader.hasNextLine()) {
				songs.add(this.recoverFromFile(reader));
				reader.nextLine();
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Empty List");
		}
	}
	
	protected abstract Song recoverFromFile(Scanner reader);

	public abstract void removeFromDirectory(int d) throws Exception, Exception;
	public abstract void removeFromList(int e);
	public abstract void copySongs() throws Exception, Exception;
}