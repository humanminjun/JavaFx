package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class WordDAO {

	private static Connection conn;
	private static ResultSet rs;

	
	public WordDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "task";
			String dbPassword = "1234";
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int deleteWordList(){
		String SQL = "DELETE FROM WORD";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int insertWordList(ObservableList<Word> wordList) {
		String SQL = "INSERT INTO WORD VALUES(?, ?)";
		try {
			int i;
			for(i = 0; i < wordList.size(); i++) {
				Word word = wordList.get(i);
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, word.getKorean());
				pstmt.setString(2, word.getEnglish());
				pstmt.executeUpdate();
			}
			return i;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public ObservableList<Word>getWordList(){
		String SQL = "SELECT * FROM WORD";
		ObservableList<Word> wordList = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Word word = new Word(rs.getString(0), rs.getString(1));
				wordList.add(word);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}
	public int saveWordList(ObservableList<Word>wordList) {
		if(deleteWordList() == -1) {
			return -1;
		}
		if(insertWordList(wordList) == -1) {
			return -1;
		}
		return 1;
	}
}	
	