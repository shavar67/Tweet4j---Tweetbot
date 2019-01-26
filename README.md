# JavaFx-TweetBot-GUI
Initially was building a twitter bot to automatically retweet mentions and hastags.


### Prerequiste

1. tweet4j - Uses your twitter developer account to authenticate 
1. twitter developer account - create a twitter developer account for access Token
1. jfoenix material design 


### How To Use This Application

#### Authentication: This is where your twitter developer account comes into play
* Clone this project and replace the empty fields with your access tokens
* Add the tweet4j and jfoenix library and organize import
* 

#### You will need: 
1. **your consumer-key**
1. **consumer-secret-key**
1. **access-token**
1. **acess-token-secret-key**


#### Declare and assign your variables
```
 private String consumerKey = "DO NOT EXPOSE";
 private String consumerKeySecret = "DO NOT EXPOSE";
 private String acessToken = "DO NOT EXPOSE";
 private String accessTokenSecret = "DO NOT EXPOSE";

```
#### This method will authenticate your account whenever you need to
```
public Configuration authenticateTwitterAccount() {
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerKeySecret)
                .setOAuthAccessToken(acessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return configBuilder.build();

    }

```

#### How to Retweet 
* To retweet you will need a status id (type long) below is an example of what a status looks like for a tweet.
* In the gui client when the listview is populated with the status ids, simply select a status and click the check status button to view that tweet. 
* twitter.com/handler/status/1088653554137542656?s=21

#### How to retrieve statuses on your timeline. 
* The following method uses higher order functions to map the statuses to a list for later uses.

```
public List<Long> getStatus() throws TwitterException {
      TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
      Twitter twitterTimeLine = twitterFactory.getInstance();
      ResponseList<Status> timelineStatus = twitterTimeLine.getHomeTimeline();
     return  timelineStatus.stream().map(Status::getId).limit(10).collect(Collectors.toList());
  }
```
#### Retweet Method

```
 public String retweet(long id) throws TwitterException {
        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        twitter4j.Twitter twitterTimeLine = twitterFactory.getInstance();
        Status stat = twitterTimeLine.retweetStatus(id);
        return stat.getUser().getName() + ": " + stat.getText();
    }
```


<img src="https://github.com/shavar67/TwitterBot2.0/blob/master/searchByHashTag.jpg"  title="Tweetbot Gui">

