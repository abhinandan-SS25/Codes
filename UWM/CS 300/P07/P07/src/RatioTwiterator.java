//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Method for iterating through the Tweets having a high like ratio
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
 * by high like ratio only
 */
public class RatioTwiterator implements Iterator<Tweet> {

	private TweetNode next; // The TweetNode containing the next tweet to be returned in the iteration
	private final double THRESHOLD; // The minimum threshold value for the ratio of likes to total engagement

	/**
	 * Constructs a new twiterator at the given starting node
	 *
	 * @param startNode the node to begin the iteration at
	 * @param threshold the minimum threshold value for the ratio of likes to total
	 *                  engagement, assumed to be between 0.0 and 1.0 thanks to
	 *                  range checking in Timeline
	 */
	public RatioTwiterator(TweetNode startNode, double threshold) {
		TweetNode curNode = startNode;
		this.THRESHOLD = threshold;
		// skips the not required values for likes ratio for tweet
		while (curNode != null && curNode.getTweet().getLikesRatio() < this.THRESHOLD) {
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
			// skips the not required values for likes ratio for tweet
			while (currNode != null && currNode.getTweet().getLikesRatio() < this.THRESHOLD) {
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
	 * the next tweet with a likes ratio in excess of the given threshold
	 *
	 * @return the next tweet in the iteration
	 * @throws NoSuchElementException if there is not a next tweet to return
	 */
	@Override
	public Tweet next() throws NoSuchElementException {
		if (this.next == null) {
			throw new NoSuchElementException();
		} else {
			TweetNode currNode = this.next;
			while (currNode != null && currNode.getTweet().getLikesRatio() < this.THRESHOLD) {
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
