//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Methods and class for TwitterFeed Class
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
 * This class models a reverse-chronological Twitter feed using a singly-linked
 * list. By default, new tweets are added at the head of the list. Note that
 * while we CALL this "reverse chronological" order, this is NOT enforced. You
 * can add Tweets anywhere in the list relative to each other, regardless of
 * their respective timestamps.
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
	private TweetNode head; // The node containing the most recent tweet
	private TweetNode tail; // The node containing the oldest tweet
	private int size; // The number of tweets in this linked list
	private TimelineMode mode; // The iteration mode for the timeline display
	private static double ratio; // The ratio of likes to retweets that we want to see; set to .5 by default

	/**
	 * Default constructor; creates an empty TwitterFeed by setting all data fields
	 * to their default values, and the timeline mode to CHRONOLOGICAL.
	 */
	public TwitterFeed() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.ratio = 0.5;
		this.mode = TimelineMode.CHRONOLOGICAL;
	}

	/**
	 * Accessor for the size of the feed
	 *
	 * @return the number of TweetNodes in this TwitterFeed
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Determines whether this feed is empty. Recommend checking MORE than just the
	 * size field to get this information, though if all methods are implemented
	 * correctly the size field's value will be sufficient.
	 *
	 * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return (this.head == null && this.tail == null && this.size == 0);
	}

	/**
	 * Determines whether a given Tweet is present in the TwitterFeed. Use Tweet's
	 * equals() method!
	 *
	 * @param findObject the Tweet to search for
	 * @return true if the Tweet is present, false otherwise
	 */
	@Override
	public boolean contains(Tweet findObject) {
		if (!this.isEmpty()) {
			TweetNode currNode = this.head;
			while (currNode != null) {
				if (currNode.getTweet().equals(findObject)) {
					return true;
				}
				currNode = currNode.getNext();
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Accessor method for the index of a given Tweet in the TwitterFeed.
	 *
	 * @param findObject the Tweet to search for
	 * @return the index of the Tweet in the TwitterFeed if present, -1 if not
	 */
	@Override
	public int indexOf(Tweet findObject) {
		if (!this.isEmpty()) {
			int index = 0;
			TweetNode currNode = this.head;
			while (currNode != null) {
				if (currNode.getTweet().equals(findObject)) {
					return index;
				}
				currNode = currNode.getNext();
				index++;
			}
			return -1;
		} else {
			return -1;
		}
	}

	/**
	 * Accessor method for the Tweet at a given index
	 *
	 * @param index the index of the Tweet in question
	 * @return the Tweet object at that index (NOT its TweetNode!)
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   the largest index of the TwitterFeed
	 */
	@Override
	public Tweet get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			int i = 0;
			TweetNode currNode = this.head;
			while (i != index) {
				currNode = currNode.getNext();
				i++;

			}
			return currNode.getTweet();
		}
	}

	/**
	 * Accessor method for the first Tweet in the TwitterFeed
	 *
	 * @return the Tweet object at the head of the linked list
	 * @throws NoSuchElementException if the TwitterFeed is empty
	 */
	public Tweet getHead() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return this.head.getTweet();
		}
	}

	/**
	 * Accessor method for the last Tweet in the TwitterFeed
	 *
	 * @return the Tweet object at the tail of the linked list
	 * @throws NoSuchElementException if the TwitterFeed is empty
	 */
	public Tweet getTail() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return this.tail.getTweet();
		}
	}

	/**
	 * Adds the given Tweet to the tail of the linked list
	 *
	 * @param newObject the Tweet to add
	 */
	@Override
	public void addLast(Tweet newObject) {
		TweetNode newTweet = new TweetNode(newObject);
		// adding to empty list
		if (this.isEmpty()) {
			this.head = newTweet;
			this.tail = newTweet;
		} else {
			// adding to end
			TweetNode lastNode = this.tail;
			lastNode.setNext(newTweet);
			this.tail = newTweet;
		}
		this.size++;
	}

	/**
	 * Adds the given Tweet to the head of the linked list
	 *
	 * @param newObject the Tweet to add
	 */
	@Override
	public void addFirst(Tweet newObject) {
		TweetNode newTweet = new TweetNode(newObject);
		// adding to empty
		if (this.isEmpty()) {
			this.head = newTweet;
			this.tail = newTweet;
		} else {
			// adding to first and apt update
			TweetNode firstNode = this.head;
			this.head = newTweet;
			this.head.setNext(firstNode);
		}
		this.size++;
	}

	/**
	 * Adds the given Tweet to a specified position in the linked list
	 *
	 * @param index     the position at which to add the new Tweet
	 * @param newObject the Tweet to add
	 */
	@Override
	public void add(int index, Tweet newObject) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// adding to first
		if (index == 0) {
			this.addFirst(newObject);
		}
		// adding to last
		else if (index == size) {
			this.addLast(newObject);
		} else {
			// adding in between
			TweetNode newTweet = new TweetNode(newObject);
			int i = 0;
			TweetNode beforeNode = this.head;
			while (i != index - 1) {
				beforeNode = beforeNode.getNext();
				i++;
			}
			TweetNode nextNode = beforeNode.getNext();
			beforeNode.setNext(newTweet);
			newTweet.setNext(nextNode);
			this.size++;
		}
	}

	/**
	 * Removes and returns the Tweet at the given index
	 *
	 * @param index the position of the Tweet to remove
	 * @return the Tweet object that was removed from the list
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   the largest index currently present in the
	 *                                   linked list
	 */
	@Override
	public Tweet delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// deleting from empty list
		if (index == 0 && size == 1) {
			TweetNode curHead = this.head;
			this.head = null;
			this.tail = null;
			this.size--;
			return curHead.getTweet();
		}
		// deleting first element
		else if (index == 0) {
			TweetNode curHead = this.head;
			TweetNode nextNode = curHead.getNext();
			this.head = nextNode;
			this.size--;
			return curHead.getTweet();
		}
		// deleting last element
		else if (index == size - 1) {
			TweetNode curTail = this.tail;
			int i = 0;
			TweetNode beforeNode = this.head;
			while (i != index - 1) {
				beforeNode = beforeNode.getNext();
				i++;
			}
			this.tail = beforeNode;
			this.size--;
			return curTail.getTweet();
		}
		// deleting from middle
		else {
			int i = 0;
			TweetNode beforeNode = this.head;
			while (i != index - 1) {
				beforeNode = beforeNode.getNext();
				i++;
			}
			TweetNode currNode = beforeNode.getNext();
			TweetNode nextNode = currNode.getNext();
			beforeNode.setNext(nextNode);
			this.size--;
			return currNode.getTweet();
		}
	}

	/**
	 * Sets the iteration mode of this TwitterFeed
	 *
	 * @param m the iteration mode; any value from the TimelineMode enum
	 */
	public void setMode(TimelineMode m) {
		this.mode = m;
	}

	/**
	 * Creates and returns the correct type of iterator based on the current mode of
	 * this TwitterFeed
	 *
	 * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator,
	 *         initialized to the head of this TwitterFeed list
	 */
	@Override
	public Iterator<Tweet> iterator() {
		if (this.mode == TimelineMode.CHRONOLOGICAL) {
			return new ChronoTwiterator(this.head);
		} else if (this.mode == TimelineMode.VERIFIED_ONLY) {
			return new VerifiedTwiterator(this.head);
		} else {
			return new RatioTwiterator(this.head, this.ratio);
		}
	}
}
