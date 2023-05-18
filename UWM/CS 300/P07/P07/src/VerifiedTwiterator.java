//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Method for iterating through the Tweets made by verified accounts
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through tweets in reverse chronological order
 * by verified users only
 */
public class VerifiedTwiterator implements Iterator<Tweet> {
	private TweetNode next; // The TweetNode containing the next tweet to be returned in the iteration

	/**
	 * Constructs a new twiterator at the given starting node
	 *
	 * @param startNode the node to begin the iteration at
	 */
	public VerifiedTwiterator(TweetNode startNode) {
		TweetNode curNode = startNode;
		// skips tweets not made by verified users
		while (curNode != null && !curNode.getTweet().isUserVerified()) {
			curNode = curNode.getNext();
		}
		this.next = curNode;
	}

	/**
	 * Checks whether there is a next tweet to return
	 *
	 * @return true if there is a next tweet, false if the value of next is null
	 */
	@Override
	public boolean hasNext() {
		if (this.next == null) {
			return false;
		} else {
			TweetNode currNode = this.next;
			// skips tweets by non-verified users
			while (currNode != null && !currNode.getTweet().isUserVerified()) {
				currNode = currNode.getNext();
			}
			if (currNode == null) {
				return false;
			}
			return true;
		}
	}

	/**
	 * Returns the next tweet in the iteration if one exists, and advances next to
	 * the next tweet by a verified user
	 *
	 * @return the next tweet in iteration by a verified user
	 * @throws NoSuchElementException if there is not a next tweet to return
	 */
	public Tweet next() throws NoSuchElementException {
		if (this.next == null) {
			throw new NoSuchElementException();
		} else {
			TweetNode currNode = this.next;
			// skips non verified user tweet
			while (currNode != null && !currNode.getTweet().isUserVerified()) {
				currNode = currNode.getNext();
			}
			if (currNode == null) {
				throw new NoSuchElementException();
			}
			this.next = currNode.getNext();
			return currNode.getTweet();
		}
	}
}
