package com.twitterbot2.model;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TweetBotFactory extends Thread {
    private String consumerKey = "";
    private String consumerKeySecret = "";
    private String acessToken = "";
    private String accessTokenSecret = "";

    public void retrieveTimeLineContent() throws TwitterException {

        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        Twitter twitterTimeLine = twitterFactory.getInstance();
        ResponseList<Status> timelineStatus = twitterTimeLine.getHomeTimeline();
         timelineStatus.stream().forEach(item -> System.out.println("" + item.getId()));
    }
  public List<Long> getStatus() throws TwitterException {
      TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
      Twitter twitterTimeLine = twitterFactory.getInstance();
      ResponseList<Status> timelineStatus = twitterTimeLine.getHomeTimeline();
     return  timelineStatus.stream().map(Status::getId).limit(800).collect(Collectors.toList());
  } 
    
    
   /** 
   ignore
   public String postTweet(long id,String tweet ) throws TwitterException {
        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        twitter4j.Twitter twitterTimeLine = twitterFactory.getInstance();
       Status status = twitterTimeLine.updateStatus(tweet);

      return "Successfully Tweeted [" + status.getText() + "].";
    }
    **/

    public String retweet(long id) throws TwitterException {
        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        twitter4j.Twitter twitterTimeLine = twitterFactory.getInstance();
        Status stat = twitterTimeLine.retweetStatus(id);
        return stat.getUser().getName() + ": " + stat.getText();
    }

    public List<String> searchTweets(String search,int count) throws TwitterException {
        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        Twitter twitter = twitterFactory.getInstance();
        Query query = new Query(search);
        query.count(count);
        QueryResult result = twitter.search(query);
        return result.getTweets().stream().map(item -> item.getUser().getName() +  ": " + item.getText().replaceAll(",", "\n")).collect(Collectors.toCollection(ArrayList::new));

    }
    public Configuration authenticateTwitterAccount() {
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerKeySecret)
                .setOAuthAccessToken(acessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return configBuilder.build();

    }

    public void PostTweets(String tweet) throws TwitterException {
        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        twitter4j.Twitter twitterTimeLine = twitterFactory.getInstance();
        Status status = twitterTimeLine.updateStatus(tweet);
        System.out.println("Successfully Tweeted: [" + status.getText() + "].");

    }


}
