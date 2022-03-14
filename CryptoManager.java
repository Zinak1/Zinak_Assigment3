

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean flag = true; // We iterate over the plainText and for each char we check whether it is inside bound.
		for(int i=0;i<plainText.length();i++) {
			if (plainText.charAt(i) < LOWER_BOUND || plainText.charAt(i) > UPPER_BOUND) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		char[] plainTextChars = plainText.toCharArray(); // We cast plainText to chars array.
		for(int i=0;i<plainText.length();i++){ // For each char in text we add key to chars.
			key = key % RANGE; // To prevent too big keys we use mode of key.
			char charWithOffset = (char) ((plainTextChars[i] + key)); // add key to char
			if(charWithOffset>UPPER_BOUND){ // If the charwithKey exceeds upperbound we subtract range from char.
				charWithOffset = (char) (charWithOffset - RANGE);
			}
			plainTextChars[i] = charWithOffset;
		}
		return String.valueOf(plainTextChars); // we return string instead of char array.
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		char[] plainTextChars = plainText.toCharArray();
		// We can use string directly instead of using char arrays. However, we have to store them in a new string
		// and we should use charAt for getting each char in the string.
		for(int i=0;i<plainText.length();i++){
			char charWithOffset = (char) ((plainTextChars[i] + bellasoStr.charAt(i%bellasoStr.length())));
			while(charWithOffset>UPPER_BOUND){
				charWithOffset = (char) (charWithOffset - RANGE);
			}
			plainTextChars[i] = charWithOffset;
		}
		return String.valueOf(plainTextChars);
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		char[] encryptedTextChars = encryptedText.toCharArray(); // We cast plainText to chars array.
		for(int i=0;i<encryptedText.length();i++){ // For each char in text we add key to chars.
			key = key % RANGE; // To prevent too big keys we use mode of key.
			char charWithOffset = (char) ((encryptedTextChars[i] - key)); // add key to char
			if(charWithOffset<LOWER_BOUND){ // If the charwithKey exceeds upperbound we subtract range from char.
				charWithOffset = (char) (charWithOffset + RANGE);
			}
			encryptedTextChars[i] = charWithOffset;
		}
		return String.valueOf(encryptedTextChars); // we return string instead of char array.
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		char[] encryptedTextChars = encryptedText.toCharArray();
		for(int i=0;i<encryptedText.length();i++){
			int charWithOffset = ((encryptedTextChars[i] - bellasoStr.charAt(i%bellasoStr.length())));
			while(charWithOffset<LOWER_BOUND){
				charWithOffset =  (charWithOffset + RANGE);
			}
			encryptedTextChars[i] = (char)charWithOffset;
		}
		return String.valueOf(encryptedTextChars);
	}
}
