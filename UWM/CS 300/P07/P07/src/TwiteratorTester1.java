///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title: Twiterator Tester
// Course: CS300, Spring 2023
//
// Author: Arnav Mohakud
// Email: mohakud@wisc.edu
// Lecturer's Name: Mouna Kacem
//
///////////////////////////////// CITATIONS ////////////////////////////////////
// None
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.NoSuchElementException;

/**
 * This class contains the code for testing all classes related to P07 Twiterator <br>
 * Bugs: None
 *
 * @author Arnav
 */
public class TwiteratorTester1 {
  /**
   * Tests to check if testUser() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testUser() {
    {
      // error checking
      {

        // null input
        try {
          User errorUser = new User(null);
          return false;
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {

        // input containing *
        try {
          User errorUser = new User("Violet***");
          return false;
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {

        // blank input
        try {
          User errorUser = new User("");
          return false;
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
          return false;
        }
      }
    }
    User user = new User("Red");
    {

      {
        // username
        String expected = "Red";
        String actual = user.getUsername();
        if (!actual.equals(expected)) {
          return false;
        }
      }

      {
        // isverified before verification
        boolean expected = false;
        boolean actual = user.isVerified();
        if (actual != expected) {
          return false;
        }
      }

      {
        // toString before verification
        String expected = "@Red";
        String actual = user.toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      // verifying user
      user.verify();
      {
        // isverified after verification
        boolean expected = true;
        boolean actual = user.isVerified();
        if (actual != expected) {
          return false;
        }
      }

      {
        // toString after verification
        String expected = "@Red*";
        String actual = user.toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // isverified after revoking verification
        user.revokeVerification();
        boolean expected = false;
        boolean actual = user.isVerified();
        if (actual != expected) {
          return false;
        }
      }

    }
    return true;
  }

  /**
   * Tests to check if testTweet() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testTweet() {
    User user = new User("Red");
    String tweetString = "Eat enough carrots and you will become orange";

    {
      // Testing exceptions
      {
        // IllegalStateException
        try {
          Tweet tweet = new Tweet(user, tweetString);
          return false;

        } catch (IllegalStateException e) {

        } catch (Exception e) {
          return false;
        }
      }
      // initialising dateGenerator field
      Calendar test = Calendar.getInstance();
      test.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(test);
      {
        // IllegalArgumentException
        try {
          String errorString = "";
          for (int i = 0; i < 281; i++) {
            errorString += "A";
          }

          Tweet tweet = new Tweet(user, errorString);
          return false;

        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
          return false;
        }
      }
      {
        // NullPointerException
        {
          try {


            Tweet tweet = new Tweet(null, tweetString);
            return false;

          } catch (NullPointerException e) {

          } catch (Exception e) {
            return false;
          }
        }
        {
          try {


            Tweet tweet = new Tweet(user, null);
            return false;

          } catch (NullPointerException e) {

          } catch (Exception e) {
            return false;
          }
        }
      }
    }
    {
      Tweet tweet = new Tweet(user, tweetString);
      {
        // checking field values
        {
          // text
          String expected = "Eat enough carrots and you will become orange";
          String actual = tweet.getText();

          if (!actual.equals(expected)) {

            return false;
          }
        }
        {
          // isUserVerified before verification
          boolean expected = false;
          boolean actual = tweet.isUserVerified();
          if (actual != expected) {
            return false;
          }
        }
        user.verify();
        {
          // isUserVerified after verification
          boolean expected = true;
          boolean actual = tweet.isUserVerified();
          if (actual != expected) {
            return false;
          }
        }
        {
          // getTotalEngagement before any engagement
          int expected = 0;
          int actual = tweet.getTotalEngagement();
          if (actual != expected) {
            return false;
          }
        }
        {
          // getLikesRatio before any engagement
          double expected = -1;
          double actual = tweet.getLikesRatio();
          if (actual != expected) {
            return false;
          }
        }
        tweet.like();
        tweet.retweet();

        {
          // getTotalEngagement after engagement

          int expected = 2;
          int actual = tweet.getTotalEngagement();

          if (actual != expected) {
            return false;
          }
        }
        {
          // getLikesRatio after engagement
          double expected = 0.5;
          double actual = tweet.getLikesRatio();
          if (actual != expected) {
            return false;
          }
        }
        {
          // equals
          {
            // using an Object that is not a Tweet
            boolean actual = false;
            boolean expected = tweet.equals(new Object());
            if (actual != expected) {

              return false;
            }
          }
          {
            // using an Tweet that does not match
            boolean actual = false;
            boolean expected =
                tweet.equals(new Tweet(new User("Blue"), "Coffee is great"));

            if (actual != expected) {
              return false;
            }
          }
          {
            // using an Tweet that matches
            boolean actual = false;
            boolean expected = tweet.equals(new Tweet(new User("Red"),
                "Eat enough carrots and you will become orange"));
            if (actual != expected) {
              return false;
            }
          }
        }
        {
          // toString
          String expected = "tweet from @Red* at Tue May 22 14:46:03 CDT 2012:\n"
              + "-- Eat enough carrots and you will become orange\n"
              + "-- likes: 1\n-- retweets: 1";
          String actual = tweet.toString();

          if (!actual.equals(expected)) {

            return false;
          }

        }
      }
    }
    return true;
  }

  /**
   * Tests to check if testNode() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testNode() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);
    TweetNode tweetNode1 = new TweetNode(
        new Tweet(new User("Red"), "Eat enough carrots and you will become orange"));
    TweetNode tweetNode3 = new TweetNode(new Tweet(new User("Green"), "I hate trees"));
    TweetNode tweetNode2 =
        new TweetNode(new Tweet(new User("Blue"), "Coffee is great"), tweetNode3);

    tweetNode1.setNext(tweetNode2);

    {
      // error checking
      {
        try {
          TweetNode errorNode = new TweetNode(null, null);
          errorNode.getNext().getTweet();
          return false;
        } catch (NullPointerException e) {

        } catch (Exception e) {
          return false;
        }
      }
      {
        try {
          TweetNode errorNode = new TweetNode(null, null);
          errorNode.getTweet().toString();
          return false;
        } catch (NullPointerException e) {

        } catch (Exception e) {
          return false;
        }
      }
    }

    {
      // tweetNode1
      String expected = "tweet from @Red at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- Eat enough carrots and you will become orange\n" + "-- likes: 0\n"
          + "-- retweets: 0";
      String actual = tweetNode1.getTweet().toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    {
      // tweetNode2
      String expected = "tweet from @Blue at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- Coffee is great\n" + "-- likes: 0\n" + "-- retweets: 0";
      String actual = tweetNode2.getTweet().toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    {
      // tweetNode3
      String expected = "tweet from @Green at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- I hate trees\n" + "-- likes: 0\n" + "-- retweets: 0";
      String actual = tweetNode3.getTweet().toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests to check if testAddTweet() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAddTweet() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    Tweet tweet1 =
        new Tweet(new User("Red"), "Eat enough carrots and you will become orange");
    Tweet tweet2 = new Tweet(new User("Blue"), "Coffee is great");
    Tweet tweet3 = new Tweet(new User("Green"), "I hate trees");

    TwitterFeed twitterFeed = new TwitterFeed();
    {
      // initial size
      int expected = 0;
      int actual = twitterFeed.size();
      if (expected != actual) {
        return false;
      }
    }
    {
      // isEmpty
      boolean actual = true;
      boolean expected = twitterFeed.isEmpty();
      if (actual != expected) {
        return false;
      }
    }
    {
      // error checking
      {
        // get when index less than 0
        try {
          Tweet errorTweet = twitterFeed.get(-1);
          return false;
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {
        // get when index greater than the largest index of the TwitterFeed
        try {
          Tweet errorTweet = twitterFeed.get(1);
          return false;
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {
        // getHead if the TwitterFeed is empty
        try {
          Tweet errorTweet = twitterFeed.getHead();
          return false;
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {
        // getTail if the TwitterFeed is empty
        try {
          Tweet errorTweet = twitterFeed.getTail();
          return false;
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {
        // add if the index is negative
        try {
          twitterFeed.add(-1, null);
          return false;
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {
        // add if the index is greater than the size of the linked list
        try {
          twitterFeed.add(1, null);
          return false;
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }
      }
    }
    // Adding an element
    twitterFeed.addLast(tweet3);
    {
      // checking size
      int expected = 1;
      int actual = twitterFeed.size();
      if (expected != actual) {
        return false;
      }
    }
    {
      // contains
      boolean actual = true;
      boolean expected =
          twitterFeed.contains(new Tweet(new User("Green"), "I hate trees"));
      if (actual != expected) {
        return false;
      }
    }
    {
      // get
      String expected = "tweet from @Green at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- I hate trees\n-- likes: 0\n" + "-- retweets: 0";
      String actual = twitterFeed.get(0).toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    {
      // tail
      String expected = "tweet from @Green at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- I hate trees\n-- likes: 0\n" + "-- retweets: 0";
      String actual = twitterFeed.getTail().toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    // adding more tweets
    twitterFeed.addFirst(tweet2);
    twitterFeed.addFirst(tweet1);
    {
      // head
      String expected = "tweet from @Red at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- Eat enough carrots and you will become orange\n" + "-- likes: 0\n"
          + "-- retweets: 0";
      String actual = twitterFeed.getHead().toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    {
      // tail
      String expected = "tweet from @Green at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- I hate trees\n" + "-- likes: 0\n" + "-- retweets: 0";
      String actual = twitterFeed.getTail().toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    {
      // get
      String expected = "tweet from @Blue at Tue May 22 14:46:03 CDT 2012:\n"
          + "-- Coffee is great\n" + "-- likes: 0\n" + "-- retweets: 0";
      String actual = twitterFeed.get(1).toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests to check if testInsertTweet() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testInsertTweet() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    Tweet tweet1 =
        new Tweet(new User("Red"), "Eat enough carrots and you will become orange");
    Tweet tweet2 = new Tweet(new User("Blue"), "Coffee is great");
    Tweet tweet3 = new Tweet(new User("Green"), "I hate trees");

    TwitterFeed twitterFeed = new TwitterFeed();
    {
      // error checking
      {
        // add if the index is negative
        try {
          twitterFeed.add(-1, null);
          return false;
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }
      }
      {
        // add if the index is greater than the size of the linked list
        try {
          twitterFeed.add(1, null);
          return false;
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }
      }
      twitterFeed.add(0, tweet2);
      twitterFeed.add(0, tweet1);
      twitterFeed.add(2, tweet3);
      {
        // head
        String expected = "tweet from @Red at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- Eat enough carrots and you will become orange\n" + "-- likes: 0\n"
            + "-- retweets: 0";
        String actual = twitterFeed.getHead().toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // tail
        String expected = "tweet from @Green at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- I hate trees\n" + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.getTail().toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // get
        String expected = "tweet from @Blue at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- Coffee is great\n" + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.get(1).toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }

    }
    return true;
  }

  /**
   * Tests to check if testDeleteTweet() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testDeleteTweet() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);
    {
      // with a single element

      TwitterFeed twitterFeed = new TwitterFeed();
      twitterFeed.addLast(
          new Tweet(new User("Red"), "Eat enough carrots and you will become orange"));
      {
        // deleting first element
        String expected = "tweet from @Red at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- Eat enough carrots and you will become orange\n" + "-- likes: 0\n"
            + "-- retweets: 0";
        String actual = twitterFeed.delete(0).toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // head
        try {
          String actual = twitterFeed.getHead().toString();
          return false;

        } catch (NoSuchElementException e) {
        } catch (Exception e) {
          return false;
        }

      }
      {
        // tail
        try {
          String actual = twitterFeed.getTail().toString();
          return false;

        } catch (NoSuchElementException e) {
        } catch (Exception e) {
          return false;
        }

      }
      {
        // get
        try {
          String actual = twitterFeed.get(0).toString();
          return false;

        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
          return false;
        }

      }
    }
    {
      // with multiple elements
      Tweet[] tweets = new Tweet[5];
      tweets[0] =
          new Tweet(new User("Red"), "Eat enough carrots and you will become orange");
      tweets[1] = new Tweet(new User("Blue"), "Coffee is great");
      tweets[2] = new Tweet(new User("Green"), "I hate trees");
      tweets[3] = new Tweet(new User("Orange"),
          "The cure for scurvy was lost and rediscovered more than 7 times in Europe");
      tweets[4] = new Tweet(new User("Pink"), "just set up my twttr");

      TwitterFeed twitterFeed = new TwitterFeed();
      for (int i = 0; i < tweets.length; i++) {
        twitterFeed.addLast(tweets[i]);
      }

      {
        // deleting last element
        String expected = "tweet from @Pink at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- just set up my twttr\n" + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.delete(4).toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // deleting first element
        String expected = "tweet from @Red at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- Eat enough carrots and you will become orange\n" + "-- likes: 0\n"
            + "-- retweets: 0";
        String actual = twitterFeed.delete(0).toString();
        if (!actual.equals(expected)) {
          System.out.println(actual);

          return false;
        }
      }
      {
        // deleting middle element
        String expected = "tweet from @Green at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- I hate trees\n" + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.delete(1).toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }

      {
        // head
        String expected = "tweet from @Blue at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- Coffee is great\n" + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.getHead().toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // tail
        String expected = "tweet from @Orange at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- The cure for scurvy was lost and rediscovered more than 7 times in Europe\n"
            + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.getTail().toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // get
        String expected = "tweet from @Orange at Tue May 22 14:46:03 CDT 2012:\n"
            + "-- The cure for scurvy was lost and rediscovered more than 7 times in Europe\n"
            + "-- likes: 0\n" + "-- retweets: 0";
        String actual = twitterFeed.get(1).toString();
        if (!actual.equals(expected)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Tests to check if testChronoTwiterator() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testChronoTwiterator() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    String[] tweetMessageArray = new String[5];

    tweetMessageArray[0] = "Eat enough carrots and you will become orange";
    tweetMessageArray[1] = "Coffee is great";
    tweetMessageArray[2] = "I hate trees";
    tweetMessageArray[3] =
        "The cure for scurvy was lost and rediscovered more than 7 times in Europe";
    tweetMessageArray[4] = "just set up my twttr";

    Tweet[] tweetsArray = new Tweet[5];
    tweetsArray[0] =
        new Tweet(new User("Red"), "Eat enough carrots and you will become orange");
    tweetsArray[1] = new Tweet(new User("Blue"), "Coffee is great");
    tweetsArray[2] = new Tweet(new User("Green"), "I hate trees");
    tweetsArray[3] = new Tweet(new User("Orange"),
        "The cure for scurvy was lost and rediscovered more than 7 times in Europe");
    tweetsArray[4] = new Tweet(new User("Pink"), "just set up my twttr");

    TwitterFeed twitterFeed = new TwitterFeed();
    for (int i = 0; i < tweetsArray.length; i++) {
      twitterFeed.addLast(tweetsArray[i]);
    }
    String actual = "";
    for (Tweet t : twitterFeed) {
      actual += t.getText() + "n";
    }

    String expected = "";
    for (String s : tweetMessageArray) {
      expected += s + "n";
    }

    if (!actual.equals(expected)) {
      return false;
    }
    return true;
  }

  /**
   * Tests to check if testVerifiedTwiterator() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testVerifiedTwiterator() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    User[] userArray = new User[5];

    userArray[0] = new User("Red");
    userArray[1] = new User("Blue");
    userArray[2] = new User("Green");
    userArray[3] = new User("Orange");
    userArray[4] = new User("Pink");

    String[] tweetMessageArray = new String[3];

    tweetMessageArray[0] = "Coffee is great";
    tweetMessageArray[1] = "I hate trees";
    tweetMessageArray[2] = "just set up my twttr";

    Tweet[] tweetsArray = new Tweet[5];
    tweetsArray[0] =
        new Tweet(userArray[0], "Eat enough carrots and you will become orange");
    tweetsArray[1] = new Tweet(userArray[1], "Coffee is great");
    tweetsArray[2] = new Tweet(userArray[2], "I hate trees");
    tweetsArray[3] = new Tweet(userArray[3],
        "The cure for scurvy was lost and rediscovered more than 7 times in Europe");
    tweetsArray[4] = new Tweet(userArray[4], "just set up my twttr");

    // verifying certain users
    userArray[1].verify();
    userArray[2].verify();
    userArray[4].verify();

    TwitterFeed twitterFeed = new TwitterFeed();

    for (int i = 0; i < tweetsArray.length; i++) {
      twitterFeed.addLast(tweetsArray[i]);
    }

    // setting mode to verified_only
    twitterFeed.setMode(TimelineMode.VERIFIED_ONLY);

    String actual = "";
    for (Tweet t : twitterFeed) {
      actual += t.getText() + "n";
    }

    String expected = "";
    for (String s : tweetMessageArray) {
      expected += s + "n";
    }

    if (!actual.equals(expected)) {
      return false;
    }
    return true;
  }

  /**
   * Tests to check if testRatioTwiterator() method returns expected values
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testRatioTwiterator() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);


    String[] tweetMessageArray = new String[3];

    tweetMessageArray[0] = "Coffee is great";
    tweetMessageArray[1] = "I hate trees";
    tweetMessageArray[2] = "just set up my twttr";

    Tweet[] tweetsArray = new Tweet[5];
    tweetsArray[0] =
        new Tweet(new User("Red"), "Eat enough carrots and you will become orange");
    tweetsArray[1] = new Tweet(new User("Blue"), "Coffee is great");
    tweetsArray[2] = new Tweet(new User("Green"), "I hate trees");
    tweetsArray[3] = new Tweet(new User("Orange"),
        "The cure for scurvy was lost and rediscovered more than 7 times in Europe");
    tweetsArray[4] = new Tweet(new User("Pink"), "just set up my twttr");

    // liking certain tweets
    tweetsArray[1].like();
    tweetsArray[2].like();
    tweetsArray[4].like();


    TwitterFeed twitterFeed = new TwitterFeed();

    for (int i = 0; i < tweetsArray.length; i++) {
      twitterFeed.addLast(tweetsArray[i]);
    }

    // setting mode to like_ratio
    twitterFeed.setMode(TimelineMode.LIKE_RATIO);

    String actual = "";
    for (Tweet t : twitterFeed) {
      actual += t.getText() + "n";
    }

    String expected = "";
    for (String s : tweetMessageArray) {
      expected += s + "n";
    }

    if (!actual.equals(expected)) {
      return false;
    }
    return true;
  }

  /**
   * Runs each of the tester methods and displays the result.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("testUser: " + testUser());
    System.out.println("testTweet: " + testTweet());
    System.out.println("testNode: " + testNode());
    System.out.println("testAddTweet: " + testAddTweet());
    System.out.println("testInsertTweet: " + testInsertTweet());
    System.out.println("testDeleteTweet: " + testDeleteTweet());
    System.out.println("testChronoTwiterator: " + testChronoTwiterator());
    System.out.println("testVerifiedTwiterator: " + testVerifiedTwiterator());
    System.out.println("testRatioTwiterator: " + testRatioTwiterator());


  }
}
