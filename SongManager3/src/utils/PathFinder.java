package utils;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PathFinder {
	private String path_;
	private String artistName_;
	private String songName_;
	
	public PathFinder (String username, String password) throws Exception {
		URLConnection uc = getConnection(username, password);
		Document doc = getXMLDocument(uc);
		Element el = getCurrentSongElement(doc);
		getInfo(el);
	}

	private URLConnection getConnection(String username, String password) throws Exception {
		Authenticator.setDefault (new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication ("", "vlc".toCharArray());
		    }
		});
	
		URL url = new URL("http", "localhost", 8080, "/requests/playlist_jstree.xml");
		URLConnection uc = url.openConnection();
		return uc;
	}

	private Document getXMLDocument(URLConnection uc) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(uc.getInputStream());
		return doc;
	}
	
	private Element getCurrentSongElement(Document doc) {
		NodeList nodeList = doc.getElementsByTagName("item");
		
		if(nodeList != null && nodeList.getLength() > 0){
		     for (int j = 0; j < nodeList.getLength(); j++) {
		         Element el = (org.w3c.dom.Element) nodeList.item(j);
		         if (el.hasAttribute("current")) {
		        	 return el;
		         }
		     }
		}
		return null;
	}

	private void getInfo(Element el) {
		path_ = decoder(el.getAttribute("uri").substring(7));
		int startIndex = path_.indexOf("/Carlos/") + 8;
		
		int endIndex = path_.indexOf("/", startIndex);
		artistName_ = path_.substring(startIndex, endIndex);
		songName_ = el.getFirstChild().getFirstChild().getFirstChild().toString();
		songName_ = songName_.substring(8, songName_.length() - 1);
	}
	
	private String decoder(String substring) {
		String s = new String();
		for (int i = 0; i < substring.length(); i++){
			if (substring.charAt(i) == '%') {
				int[] codeAndIncrement = UTF8CodeToCharacter(substring, i);
				s += (char)codeAndIncrement[0];
				i += codeAndIncrement[1];
			}
			else {
				s += substring.charAt(i);
			}
		}
		return s;
	}

	private int[] UTF8CodeToCharacter(String substring, int i) {
		int nextNumber = Integer.parseInt(substring.substring(i+1, i+2), 16);
		String charCode, binaryCodeString, UTF16CodeString;
		int code;
		int[] result = new int[2];
		
		if (nextNumber < 12) {
			charCode = substring.substring(i+1, i+3);
			result[0] = Integer.parseInt(charCode, 16);
			result[1] = 2;
		}
		else if (nextNumber < 14) {
			charCode = substring.substring(i+1, i + 3) + substring.substring(i + 4, i + 6);
			code = Integer.parseInt(charCode, 16);
			binaryCodeString = Integer.toBinaryString(code);
			UTF16CodeString = binaryCodeString.substring(3, 8) + binaryCodeString.substring(10, 16);
			code = Integer.parseInt(UTF16CodeString, 2);
			result[0] = code;
			result[1] = 5;
		}
		else /*if (nextNumber < 15) */{
			charCode = substring.substring(i + 1, i + 3) + substring.substring(i + 4, i + 6) + substring.substring(i + 7, i + 9);
			code = Integer.parseInt(charCode, 16);
			binaryCodeString = Integer.toBinaryString(code);
			UTF16CodeString = binaryCodeString.substring(4, 8) + binaryCodeString.substring(10, 16) + binaryCodeString.substring(18, 24);
			code = Integer.parseInt(UTF16CodeString, 2);
			result[0] = code;
			result[1] = 8;
		}
		return result;
	}

	public String getPath() {
		return path_;
	}
	
	public String getArtistName() {
		return artistName_;
	}
	
	public String getSongName() {
		return songName_;
	}
}
