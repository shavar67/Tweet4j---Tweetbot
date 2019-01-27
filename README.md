
Initially i was building a twitter bot to automatically retweet mentions and hastags.


### Prerequistes

1. tweet4j - Uses your twitter developer account to authenticate [tweet4j](http://twitter4j.org/en/)
1. twitter developer account - create a twitter developer account for access Token [Twitter Developer Portal](https://developer.twitter.com/en.html)
1. jfoenix material design library  [Jfoenix](http://www.jfoenix.com/index.html#start)



### How To Use This Application
* Clone this project and replace the empty fields with your access tokens
* Add the tweet4j and jfoenix library and organize imports

### Authentication 
* These keys can be found under the keys and tokens section in your app via the twitter developer portal

Consumer Keys|Access Tokens & access token secret
-------|-----------------------------------------
Consumer API key|lqX6ZguMbSEdOq1fgvjgf4rheNzG3
Consumer Secret|EFFfTqc6SZCrWEVNYl8WBzc5uvd
AccessToken|VUAHKVROonYByleMlEmzK5dh5j4
AccessToken Secret Key|OyvCiRn3zxMRbabuu44NDKvnBu7




#### Declare and assign your variables
```java
 private String consumerKey = "DO NOT EXPOSE";
 private String consumerKeySecret = "DO NOT EXPOSE";
 private String acessToken = "DO NOT EXPOSE";
 private String accessTokenSecret = "DO NOT EXPOSE";

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

