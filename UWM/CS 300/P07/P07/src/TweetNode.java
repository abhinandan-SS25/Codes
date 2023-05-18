//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Methods and class for TweetNode Class
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
 * A container for a Tweet in a singly-linked list
 */
public class TweetNode {
	private Tweet tweet; // The next TweetNode in this linked list
	private TweetNode nextTweet; // The tweet contained in this node

	/**
	 * Constructs a singly-linked node containing a tweet
	 *
	 * @param tweet the tweet to put in this node
	 * @param next  the next tweet in the linked list
	 */
	public TweetNode(Tweet tweet, TweetNode next) {
		this.tweet = tweet;
		this.nextTweet = next;
	}

	/**
	 * Constructs a singly-linked node containing a tweet, with no successor tweet
	 *
	 * @param tweet the tweet to put in this node
	 */
	public TweetNode(Tweet tweet) {
		this.tweet = tweet;
		this.nextTweet = null;
	}

	/**
	 * Accesses the tweet in this node
	 *
	 * @return the tweet in this node
	 */
	public Tweet getTweet() {
		return this.tweet;
	}

	/**
	 * Accesses the next TweetNode in the list
	 *
	 * @return the successor TweetNode
	 */
	public TweetNode getNext() {
		return this.nextTweet;
	}

	/**
	 * Links this node to another node
	 *
	 * @param next the successor TweetNode (can be null)
	 */
	public void setNext(TweetNode next) {
		this.nextTweet = next;
	}
}
