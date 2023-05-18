//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tester for classes and methods in package
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

import java.util.Calendar;
import java.util.NoSuchElementException;

/**
 * Defines and tests the methods and classes and their behaviour
 */
public class TwiteratorTester {
	/**
	 * tests behaviour of User class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 *
	 */
	public static boolean testUser() {
		// invalid username with *
		try {
			User user0 = new User("tryverified*");
			if (user0.getUsername().contains("*")) {
				return false;
			}
		} catch (IllegalArgumentException i) {
		} catch (Exception e) {
			return false;
		}
		// invalid user with no username
		try {
			User user0 = new User(null);
			if (user0.getUsername() == null) {
				return false;
			}
		} catch (IllegalArgumentException i) {
		} catch (Exception e) {
			return false;
		}

		User user1 = new User("user1");
		User user2 = new User("user2");

		// non verified users
		boolean expectedverified1 = false;
		boolean expectedverified2 = false;

		boolean realverified1 = user1.isVerified();
		boolean realverified2 = user2.isVerified();

		if (expectedverified1 != realverified1 && expectedverified2 != realverified2) {
			return false;
		}

		// checks usernames
		String expectedusername = "@user1";
		String realusername = user1.toString();
		if (!expectedusername.equals(realusername)) {
			return false;
		}

		// checks verification
		user2.verify();

		boolean expectedverified3 = true;
		boolean realverified3 = user2.isVerified();

		if (expectedverified3 != realverified3) {
			return false;
		}

		String expecteduser1 = "@user1";
		String expecteduser2 = "@user2*";

		String realuser1 = user1.toString();
		String realuser2 = user2.toString();

		if (!expecteduser1.equals(realuser1) && expecteduser2.equals(realuser2)) {
			return false;
		}

		// checks revokeverification
		user2.revokeVerification();

		boolean expectedverified4 = false;
		boolean realverified4 = user2.isVerified();

		if (expectedverified4 != realverified4) {
			return false;
		}

		// tester passed
		return true;
	}

	/**
	 * tests behaviour of tweet class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testTweet() {
		User user1 = new User("testuser");
		User user2 = new User("verifiedUser");

		// dategenerator not initialized resulting in illegalstate
		try {
			Tweet tweet0 = new Tweet(user1, "test");
		} catch (IllegalStateException e) {
		} catch (Exception e1) {
			return false;
		}

		// set date
		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		// proper tweet
		Tweet tweet1 = new Tweet(user1, "This is a new tweet 1.");

		String real = tweet1.toString();
		String expected = "tweet from " + "@testuser" + " at " + "Tue May 22 14:46:03 CDT 2012" + 
		":\n" + "-- "
				+ "This is a new tweet 1." + "\n" + "-- likes: " + 0 + "\n" + "-- retweets: " + 0;
		if (!real.equals(expected)) {
			return false;
		}

		// tweets with error
		try {
			Tweet tweet2 = new Tweet(null, "test");
		} catch (NullPointerException n) {
		} catch (Exception e) {
			return false;
		}
		try {
			Tweet tweet3 = new Tweet(user1, null);
		} catch (NullPointerException n) {
		} catch (Exception e) {
			return false;
		}
		try {
			Tweet tweet4 = new Tweet(user1, "*".repeat(281));
		} catch (IllegalArgumentException i) {
		} catch (Exception e) {
			return false;
		}
		// tester passed
		return true;
	}

	/**
	 * tests behaviour of tweetNode class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testNode() {
		User user1 = new User("user1");

		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		// new tweets
		Tweet tweet1 = new Tweet(user1, "This is tweet1");
		Tweet tweet2 = new Tweet(user1, "This is tweet2");
		Tweet tweet3 = new Tweet(user1, "This is tweet3");

		// nodes from tweets
		// singular node
		TweetNode node1 = new TweetNode(tweet1);
		// connecting tweet2 to tweet1
		TweetNode node2 = new TweetNode(tweet2, node1);
		// singular node
		TweetNode node3 = new TweetNode(tweet3);

		if (node1 != null && !node1.getTweet().equals(tweet1)) {
			return false;
		}
		TweetNode tn2 = node2.getNext();
		if (tn2 != node1) {
			return false;
		}
		if (node2 != null && tn2 == null) {
			return false;
		}
		if (tn2 != null && !tn2.getTweet().equals(tweet1)) {
			return false;
		}

		if (node3 != null && node3.getNext() != null) {
			return false;
		}

		// tester passed
		return true;
	}

	/**
	 * tests behaviour of addFirst and addLast methods of feed class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testAddTweet() {
		TwitterFeed feed = new TwitterFeed();

		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		// new feed
		if (feed.isEmpty() != true) {
			return false;
		}
		if (feed.size() != 0) {
			return false;
		}
		User user1 = new User("testuser");
		Tweet tweet1 = new Tweet(user1, "test tweet1");

		// add to head
		feed.addFirst(tweet1);

		// added 1 element
		if (feed.isEmpty() || feed.size() == 0) {
			return false;
		}
		if (!feed.get(0).equals(tweet1) && !feed.getTail().equals(tweet1) && !feed.getHead().equals(tweet1)) {
			return false;
		}

		// add to tail
		Tweet tweet2 = new Tweet(user1, "test tweet2");
		feed.addLast(tweet2);

		if (!feed.getTail().equals(tweet2) && !feed.getHead().equals(tweet1)) {
			return false;
		}

		Tweet tweet3 = new Tweet(user1, "test tweet3");
		feed.addLast(tweet3);
		if (!feed.getTail().equals(tweet3) && !feed.getHead().equals(tweet1)) {
			return false;
		}

		if (!feed.contains(tweet2)) {
			return false;
		}

		Tweet tweet4 = new Tweet(user1, "test tweet4");
		feed.addFirst(tweet4);
		if (!feed.get(0).equals(tweet4) && !feed.getHead().equals(tweet4) && !feed.getHead().equals(tweet4)) {
			return false;
		}

		// tester passed
		return true;
	}

	/**
	 * tests behaviour of add in feed class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testInsertTweet() {
		TwitterFeed feed = new TwitterFeed();

		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		User user1 = new User("testuser");
		Tweet tweet1 = new Tweet(user1, "test tweet1");
		Tweet tweet2 = new Tweet(user1, "test tweet2");
		Tweet tweet3 = new Tweet(user1, "test tweet3");
		Tweet tweet4 = new Tweet(user1, "test tweet4");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);
		feed.addLast(tweet4);

		// adding to the middle
		Tweet tweet5 = new Tweet(user1, "test tweet5");
		feed.add(2, tweet5);

		if (!feed.get(2).equals(tweet5)) {
			return false;
		}

		Tweet tweet6 = new Tweet(user1, "test tweet6");
		feed.add(2, tweet6);

		if (!feed.get(2).equals(tweet6) && !feed.get(3).equals(tweet6)) {
			return false;
		}

		// adding to head
		Tweet tweet7 = new Tweet(user1, "test tweet7");
		feed.add(0, tweet7);
		Tweet tweet8 = new Tweet(user1, "test tweet8");
		feed.add(7, tweet8);

		if (feed.size() != 8) {
			return false;
		}

		if (!feed.getHead().equals(tweet7) && !feed.getTail().equals(tweet8)) {
			return false;
		}

		if (!feed.contains(tweet4) || !feed.contains(tweet5)) {
			return false;
		}

		// tester passed
		return true;
	}

	/**
	 * tests behaviour of delete method in feed class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testDeleteTweet() {
		TwitterFeed feed1 = new TwitterFeed();
		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		User user2 = new User("user1");
		Tweet tweet10 = new Tweet(user2, "test");

		// delete on empty list
		try {
			feed1.delete(0);
		} catch (IndexOutOfBoundsException i) {
		} catch (Exception e) {
			return false;
		}

		// adding and deleting on new list
		feed1.addFirst(tweet10);
		feed1.delete(0);

		if (!feed1.isEmpty()) {
			return false;
		}
		try {
			feed1.getHead();
			feed1.getTail();
		} catch (NoSuchElementException s) {

		} catch (Exception e) {
			return false;
		}

		TwitterFeed feed = new TwitterFeed();

		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		User user1 = new User("testuser");
		Tweet tweet1 = new Tweet(user1, "test tweet1");
		Tweet tweet2 = new Tweet(user1, "test tweet2");
		Tweet tweet3 = new Tweet(user1, "test tweet3");
		Tweet tweet4 = new Tweet(user1, "test tweet4");
		Tweet tweet5 = new Tweet(user1, "test tweet5");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);
		feed.addLast(tweet4);
		feed.addLast(tweet5);

		// error index
		try {
			feed.delete(5);
			feed.delete(-2);
		} catch (IndexOutOfBoundsException i) {
		} catch (Exception e) {
			return false;
		}

		// delete from middle
		Tweet real1 = feed.delete(4);
		Tweet expected1 = tweet5;

		if (!real1.equals(expected1) && !feed.getTail().equals(tweet4)) {
			return false;
		}

		// delete from head
		Tweet real2 = feed.delete(0);
		Tweet expected2 = tweet1;

		if (!real2.equals(expected2) && !feed.getHead().equals(tweet2)) {
			return false;
		}

		if (!feed.get(1).equals(tweet3) && feed.contains(tweet5)) {
			return false;
		}

		Tweet real3 = feed.delete(1);
		Tweet expected3 = tweet3;

		if (!real3.equals(expected3) && !feed.get(1).equals(tweet4)) {
			return false;
		}

		return true;
	}

	/**
	 * tests behaviour of the chronologicaliterator class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testChronoTwiterator() {
		TwitterFeed feed = new TwitterFeed();

		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		User user1 = new User("testuser");
		Tweet tweet1 = new Tweet(user1, "test tweet1");
		Tweet tweet2 = new Tweet(user1, "test tweet2");
		Tweet tweet3 = new Tweet(user1, "test tweet3");
		Tweet tweet4 = new Tweet(user1, "test tweet4");
		Tweet tweet5 = new Tweet(user1, "test tweet5");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);
		feed.addLast(tweet4);
		feed.addLast(tweet5);

		// expected order of returned elements
		Tweet[] expectedTweets = new Tweet[] { tweet1, tweet2, tweet3, tweet4, tweet5 };

		int i = 0;
		for (Tweet t : feed) {
			if (!t.equals(expectedTweets[i])) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * tests behaviour of the verifiediterator class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testVerifiedTwiterator() {
		TwitterFeed feed = new TwitterFeed();

		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		User user1 = new User("testuser");
		User user2 = new User("verifieduser");
		user2.verify();
		Tweet tweet1 = new Tweet(user1, "test tweet1");
		Tweet tweet2 = new Tweet(user2, "test tweet2");
		Tweet tweet3 = new Tweet(user1, "test tweet3");
		Tweet tweet4 = new Tweet(user2, "test tweet4");
		Tweet tweet5 = new Tweet(user2, "test tweet5");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);
		feed.addLast(tweet4);
		feed.addLast(tweet5);

		feed.setMode(TimelineMode.VERIFIED_ONLY);

		// expected order of return from iterator
		Tweet[] expectedTweets = new Tweet[] { tweet2, tweet4, tweet5 };
		int i = 0;
		for (Tweet t : feed) {
			if (!t.equals(expectedTweets[i])) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * tests behaviour of the ratioiterator class
	 * 
	 * @return true if expected behaviour is satisfied, false otherwise
	 */
	public static boolean testRatioTwiterator() {
		TwitterFeed feed = new TwitterFeed();

		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);

		User user1 = new User("testuser");
		User user2 = new User("verifieduser");
		user2.verify();
		Tweet tweet1 = new Tweet(user1, "test tweet1");
		Tweet tweet2 = new Tweet(user2, "test tweet2");
		Tweet tweet3 = new Tweet(user1, "test tweet3");
		Tweet tweet4 = new Tweet(user2, "test tweet4");
		Tweet tweet5 = new Tweet(user2, "test tweet5");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);
		feed.addLast(tweet4);
		feed.addLast(tweet5);

		feed.setMode(TimelineMode.LIKE_RATIO);

		// liking and retweeting to change ratio
		tweet1.like();
		tweet1.retweet();

		tweet3.like();
		tweet3.like();
		tweet3.retweet();
		tweet3.retweet();
		tweet3.retweet();

		tweet4.like();
		tweet4.like();
		tweet4.like();
		tweet4.retweet();
		tweet4.retweet();

		// expected order of return
		Tweet[] expectedTweets = new Tweet[] { tweet1, tweet4 };
		int i = 0;
		for (Tweet t : feed) {
			if (!t.equals(expectedTweets[i])) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * Runs the test methods and returns feedback on them
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.print("Testing user class:");
		if (testUser()) {
			System.out.println("testuser passed");
		} else {
			System.out.println("testuser failed");
		}

		System.out.print("Testing tweet class:");
		if (testTweet()) {
			System.out.println("testtweet passed");
		} else {
			System.out.println("testtweet failed");
		}

		System.out.print("Testing tweetNode class:");
		if (testNode()) {
			System.out.println("testnode passed");
		} else {
			System.out.println("testnode failed");
		}

		System.out.print("Testing twitterfeed add methods:");
		if (testAddTweet()) {
			System.out.println("testaddtweet passed");
		} else {
			System.out.println("testaddtweet failed");
		}

		System.out.print("Testing twitterfeed insert methods:");
		if (testInsertTweet()) {
			System.out.println("testinserttweet passed");
		} else {
			System.out.println("testinserttweet failed");
		}

		System.out.print("Testing twitterfeed delete method:");
		if (testDeleteTweet()) {
			System.out.println("testdeletetweet passed");
		} else {
			System.out.println("testdelettweet failed");
		}

		System.out.print("Testing chronotwiterator method:");
		if (testChronoTwiterator()) {
			System.out.println("testchronotwiterator passed");
		} else {
			System.out.println("testchronotwiterator failed");
		}

		System.out.print("Testing verifiedtwiterator method:");
		if (testVerifiedTwiterator()) {
			System.out.println("testverifiedtwiterator passed");
		} else {
			System.out.println("testverifiedtwiterator failed");
		}

		System.out.print("Testing chron method:");
		if (testRatioTwiterator()) {
			System.out.println("testverifiedtwiterator passed");
		} else {
			System.out.println("testverifiedtwiterator failed");
		}
	}
}
