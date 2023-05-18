//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Method for Chronologically iterating through the tweets
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
 */
public class ChronoTwiterator implements Iterator<Tweet> {
	private TweetNode next; // The TweetNode containing the next tweet to be returned in the iteration

	/**
	 * Constructs a new twiterator at the given starting node
	 *
	 * @param startNode the node to begin the iteration at
	 */
	public ChronoTwiterator(TweetNode startNode) {
		this.next = startNode;
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
			return true;
		}
	}

	/**
	 * Returns the next tweet in the iteration if one exists, and advances next to
	 * the next tweet
	 *
	 * @return next tweet in iteration
	 * @throws NoSuchElementException if there is not a next tweet to return
	 */
	@Override
	public Tweet next() throws NoSuchElementException {
		if (this.next == null) {
			throw new NoSuchElementException();
		} else {
			TweetNode currNode = this.next;
			this.next = currNode.getNext();
			return currNode.getTweet();
		}
	}
}
