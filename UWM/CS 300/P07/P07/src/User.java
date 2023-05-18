//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Methods and class for User Class
// Course:   CS 300 Spring 2023
//
// Author:   Abhinandan Saha
// Email:    asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help given or recieved
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This (very basic) data type models a Twitter user.
 */
public class User {
	private String username; // The verified status of this User (whether they have a blue checkmark or not)
	private boolean isVerified; // The username this User tweets under

	/**
	 * Constructs a new User object with a given username. All Users are unverified
	 * by default.
	 *
	 * @param username the username of this User.
	 * @throws IllegalArgumentException if the given username contains an asterisk
	 *                                  ("*") character, or is null, or is blank
	 */
	public User(String username) throws IllegalArgumentException {
		if (username == null || username.length() == 0 || username.contains("*")) {
			throw new IllegalArgumentException();
		} else {
			this.username = username;
			this.isVerified = false;
		}
	}

	/**
	 * Accesses the username of this User
	 *
	 * @return the username this User tweets under
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Determines whether the User is a verified user or not
	 *
	 * @return true if this User is verified
	 */
	public boolean isVerified() {
		return this.isVerified;
	}

	/**
	 * Gives this User an important-looking asterisk
	 */
	public void verify() {
		this.isVerified = true;
	}

	/**
	 * Takes this User's important-looking asterisk away
	 */
	public void revokeVerification() {
		this.isVerified = false;
	}

	/**
	 * Creates a String representation of this User for display. For example, if
	 * this User's username is "uwmadison" and they are verified, this method will
	 * return "@uwmadison*"; if this User's username is "dril" and they are not
	 * verified, this method will return "@dril" (with no asterisk).
	 *
	 * @return a String representation of this User as described above
	 */
	@Override
	public String toString() {
		if (this.isVerified()) {
			return "@" + this.getUsername() + "*";
		} else {
			return "@" + this.getUsername();
		}
	}
}
