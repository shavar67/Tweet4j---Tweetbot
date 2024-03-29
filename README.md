 |Better Code |Code Factor |Miscellaneous| 
-------|--------|-----------|
[![BCH compliance](https://bettercodehub.com/edge/badge/shavar67/Tweet4j---Tweetbot?branch=master)](https://bettercodehub.com/)|[![CodeFactor](https://www.codefactor.io/repository/github/shavar67/tweet4j---tweetbot/badge)](https://www.codefactor.io/repository/github/shavar67/tweet4j---tweetbot)|![](https://img.shields.io/github/contributors/shavar67/Tweet4j---Tweetbot.svg) [![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://GitHub.com/Naereen/ama) ![](https://img.shields.io/github/issues/shavar67/Tweet4j---Tweetbot.svg)
### Prerequistes

1. tweet4j - Uses your twitter developer account to authenticate [tweet4j](http://twitter4j.org/en/)
1. twitter developer account - create a twitter developer account for access Token [Twitter Developer Portal](https://developer.twitter.com/en.html)
1. jfoenix material design library  [Jfoenix](http://www.jfoenix.com/index.html#start)


### Authentication 
* These keys can be found under the keys and tokens section in your app via the twitter developer portal

Consumer Keys|Access Tokens & access token secret
-------|-----------------------------------------
Consumer API key|lqX6ZguMbSEdOq1fgvjgf4rheNzG3
Consumer Secret|EFFfTqc6SZCrWEVNYl8WBzc5uvd
AccessToken|VUAHKVROonYByleMlEmzK5dh5j4
AccessToken Secret Key|OyvCiRn3zxMRbabuu44NDKvnBu7




#### Declare and assign your variables, watch out for whitespace it could lead to authorization failed to recogonized api keys.
```java
 private String consumerKey = "lqX6ZguMbSEdOq1fgvjgf4rheNzG3";
 private String consumerKeySecret = "EFFfTqc6SZCrWEVNYl8WBzc5uvd";
 private String acessToken = "VUAHKVROonYByleMlEmzK5dh5j4";
 private String accessTokenSecret = "OyvCiRn3zxMRbabuu44NDKvnBu7";

```
#### This method will authenticate your account whenever you need to
```java
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

```java
public List<Long> getStatus() throws TwitterException {
      TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
      Twitter twitterTimeLine = twitterFactory.getInstance();
      ResponseList<Status> timelineStatus = twitterTimeLine.getHomeTimeline();
     return  timelineStatus.stream().map(Status::getId).limit(10).collect(Collectors.toList());
  }
```
#### Retweet Method

```java
 public String retweet(long id) throws TwitterException {
        TwitterFactory twitterFactory = new TwitterFactory(authenticateTwitterAccount());
        twitter4j.Twitter twitterTimeLine = twitterFactory.getInstance();
        Status stat = twitterTimeLine.retweetStatus(id);
        return stat.getUser().getName() + ": " + stat.getText();
    }
```


<img src="https://github.com/shavar67/TwitterBot2.0/blob/master/searchByHashTag.jpg"  title="Tweetbot Gui">

<img src="https://github.com/shavar67/Tweet4j---Tweetbot/blob/master/prototype.JPG" title="New Ui">
