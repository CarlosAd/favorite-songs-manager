package manager;

public class SongFileManager {
	SongList songList;
	
	public SongFileManager(SongList songList) {
		this.songList = songList;
	}
	
	public void update() throws Exception {
		this.clearOldSongs();
		this.copySongs();
	}

	private void copySongs() throws Exception {
		this.songList.copySongs();
	}

	private void clearOldSongs() throws Exception {
		int S = this.songList.getSongs().size();
		int C = this.songList.getCapacity();
		int N = this.songList.getNumberOfCopiedSongs();
		int D, E;
		
		D = Math.min(S - C, N);
		D = (D < 0) ? 0 : D;
		songList.removeFromDirectory(D);
		
		E = S - C;
		E = (E < 0) ? 0 : E;
		songList.removeFromList(E);
	}
}