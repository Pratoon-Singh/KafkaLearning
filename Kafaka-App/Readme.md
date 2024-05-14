Next step is to create a restful controller class that uses the user, producer service to do that right

click, create a new class, call it user controller, put it in a package called Kafka dot Controllers.

Finish.

Mark this with at rest controller annotation.

Also, we are going to mark it with @ request mapping annotation within the double quotes forward slash user

API, inject the user producer service which will use call it service mark it with at auto wired annotation.

We can make this private create a method public void send user data is the method.

Mark this with @ post mapping.

We need two things here.

The string name of the user comma, the int age of the user within this post mapping will have a

uri slash call it publish user data, slash the placeholders for name forward slash age another

placeholder within Angular brackets.

So we're going to receive this data as path variables.

We then invoke the service dot, send user data method pass in the name and age, but we need to mark

these parameters with @ path variable annotation so that the values that come in will be binded.

Let me add some lines so that you can see it clearly.

This path variable within brackets, within the double quotes, we specify whatever placeholder we have used

here.

You can copy that, paste it next to the path variable here, which is age

And use age right there do a control shift f to format so restful controller is ready.

The last step is to configure the properties for both the producer and the consumer projects, go to

the producer project go to source main resources, open up the application dot properties.

The very first property always is the Broker Information Spring dot kafka dot producer dot

Bootstrap hyphen servers is the property localhost Colen nine zero nine two.

If we have multiple brokers, we can pass a comma separated list here next spring

Dot Kafka dot producer Dot Key Serializer is equal to it is the string serializer username is of type

string command shift t or control shift t and search for string serializer.

Also you can copy it from earlier classes if you have it.

Copy that package name, come back paste it, dot the class name, grab the class name from here, go

back paste it, you can copy that entire property paste it on the next line.

Next is the value serializer and the class name is Integer Serializer

On the right hand side it is integer the package remains the same and it is the integer serializer.

So those are the three properties required on the producer side do a control a control c go to the application

dot properties of the consumer, the user consumer.

Source main resources application dot properties, you can paste those properties here need to make

some changes.

The bootstrap servers remains the same key.

This is deserializer.

And it is consumer to start with here.

The producers should change the consumer, copy that consumer once you have it, paste it on the two

other properties as well.

Consumer dot de serializer.

It doesn't have a serializer on the consumer.

It has to be a deserializer on the right side as well.

Ensure that we have string D capital.

Dese, everything else is small, string de serializer, similarly integer, D is capital Deserializer,

just to ensure copy that string Deserializer, open it by using command shift t or just search for

that string.

De serializer from Kafka.

Make sure the package and the class name are, as it is, or apache Kafka, Common String deserializer. If

you have any issues, we'll see them when we run the program.

And the last property on the consumer side is Spring Dot kafka dot Consumer Group dot Ide.

We need to configure a group, Id, let's call it, user hyphen group.

Save it.



Time to see our applications in action, go to the user producer, right click Run as Spring boot

app that will launch the application by default on port 8080 and our restful end point is up and running.

Go to the consumer before we launch it.

Go to the application dot properties and add a Server dot port, because by default, this will

also start on port 8080 and that will be a conflict with the producer.

So 8085, you can use any non conflicting port here.

I'm using 8085.

Right

Click on the consumer run as a springboot application, as soon as we do that, it will do a lot of

things for us behind the scenes.

It will connect to the Kafka broker and it will start polling or listening.

You can see that in the logs.

There is a lot of information about the kafka broker right there.

Go to Postman, launch postman, use the http post method in a new tab, and the url

If you remember from the user producers' controller, go to the controller class real quick to see what that

url we should be hitting is it is slash user API slash published user data and then slash Name and age

go back to Postman local host Colen 8080 Slash User API.

In my case I see it here because I have been testing it earlier.

Publish user U capital D capital data slash use.

Bob slash says the age 40 hit send.

We get a response, two hundred, OK, that is not what we care about, go back to spring tool suite,

go to the console here, see that age user's age is 40.

If you go to the client console or the consumer console here, you should see users.

age is 40 on the console.

So the request come to the restful controller.

That restful controller will use the service, which internally uses the Kafka template, it sends the

message to this topic with name as the key and age as the value.

And then the consumer, which is already listening when we started this application, consumes that

message and renders it onto the UI.

You can hit it again with a different name if you want.

You can use your own first name, bharath 40 send and you get a 200 response back.

If you go to the console, you again see the response on the console or the message being read and render

to the console.


This lecture will see how easy it is to produce or send our own object types instead of simple types

on the producer and in the next lecture will do the same on the consumer and then we will test it.

Spring makes it super easy, right

Click on one of the packages, create a new pojo class, call it user.

Let's put it in a package called

kafka dot dbo finish.

Let's add some fields on to the user private string name of the user, private int age of the user

and private string favorite genre of movies.

Let's say we are working on a website like Netflix where we are capturing all the user profile information.

We are capturing what his favorite genre is in movies, select all of them, go to source, generate

getters and setters, select all they wanted after the last field, generate control shift f to format.

We have the pojo next.

Go to the controller right away.

Instead of using these simple types, we will be using the user so you can get rid of these parameters

that are being passed to the send user data method, use the user object that we have created control

one import it from our package.

Take these two out of the url that url will now be slash published user data thats the uri.

mark this parameter with at request body annotation that will deserialize the incoming JSON into this

user object.

Next, we pass this user to the send user data method hit control one when it says that method doesn't take

this parameter.

So go into that method, take out the string name and age use user.

Here as well control one import that

And when we invoke the send method we will use user dot name as the key and the user object itself as the

value.

But for that to work when we create the Kafka template, we need to use user instead of a integer thats

it.

And then you go configure the serializer, that's the key step, so you go to the application dot properties.

Let me save the user controller.

Once that is done, go to the application DOT properties when we create our own producers and consumers.

You have seen that we had to create our own custom serializer or use the Avros serializers spring supports

in built Json Serializer that is ready to use internally.

They do the same.

What we have done in earlier sections hit command shift t be on Mac or control shift T on windows and

search

For Json Serializer, there are many Json serializers from various Jackson packages.

That's not what we are looking for.

Go to org spring framework.

Kafka support serializer.

That package, again, org spring framework, Kafka support dot serializer, open that up.

So this is the Json Serializer class, which implements the serializer for us, whatever we have manually

done in sections earlier on spring has already done it and it has given us this.

Json Serializer, go up top, grab this package.

Copy that.

Come back to the properties of our producer project.

This guy right there, instead of using the integer serializer for the value.

Take that out, paste the package , the key serializer will still remain the same, it is a string serializer,

but the value serializer now is going to be

Json Serializer, Paste that package dot.

Go back to that serializer.

Copy that class name.

Which is Json Serializer come back paste it thats it, we don't have to create our own serializer spring

already has a inbuilt serializer that can be used in the next lecture will do something similar on the

consumer side.

In this lecture, you will see how easy it is to use Json deserializer, on the consumer

side, to deserealize the object type data, to do that, go to the consumer project, open up the service

there we have a method that is mark it with @ kafka Listner.

Now go copy the dto package from the producer side because we need access to the dto copy that

Paste it onto the source main Java.

So I have copied the entire package in which the dto lives i pasted it right here.

Now we have the dto on the consumer side as well.

Usually we put this into a common project and these projects can share it or they can create one of

their own.

Go back to the user consumer service, change this from int age to user control

One import user from the package we have copied over and the age we can get it from get age

Also, I want to display the genere he likes favorite genere space plus user dot get favorite genere

Thats it, now you go to the application dot properties of the consumer, configure the de serializer

here for the value instead of integer deserializer do a command shift or control shift t search

for Json deSerializer.

Make sure these org dot spring framework.

Dot kafka dot support

Dot serializer.

Scroll up, grab the package name first

Go back to the application dot properties, take out this integer, de serializer paste the package

dot, go back.

grab the class name, which is Json deSerializer Comeback paste it.

On the consumer side, there is one additional property we need to convey right now, if we run the

producer and consumer and the message is received and when it is trying to be de serealized, there will

be some trust issues that Json Serializer does not trust all the dtos when it goes ahead and tries

to deserialize this user, it will through a trust exception.

If you want to try it, you can try it.

But there is one additional property that tells this deserializer, which all dtos it can trust and use

or deserealize that is a long property.

Spring dot, Kafka, dot, consumer dot properties, dot spring again, dot, Jason dot trusted dot

packages.

Is equal to here, you can give it the packages which have your dtos, in our case, it is this guy

com bharath kafka dto

go back, paste it

If you have multiple packages, you can give a comma separated list or you can simply use a star if you

want it to trust all the packages.

That's it now we are ready to go ahead and test it in the next lecture, will run the producer and consumer

and will test them from Postman.

In this lecture, we'll test the object serialization and de serialization before we go ahead and do that

in the application dot properties of the user producer.

I got a small typo when I have configure the json serializer and I copied the package.

I missed r

at the end, it should be serializer, dot Json serializer, if you had got it right.

You are awesome.

right click run as

Spring boot app stop the applications, I have already stopped them, stopped them and then start

them again.

The producer is up and running, right

click start the consumer as well.

As a springboot app, as it comes up, go to Postman.

Take out the name and the age from the url, go to the body.

Select raw

Change this to Json if you have a text or something else in this drop, down change it to Json and

give it what it needs.

We need to pass in the name, age and favorite genre.

The very first thing is name comma

You can pass in your own first name comma it should be colon, then a comma at the end, then the age

colon 40.

And finally, save genere.

Colen, I like different kinds of movies, let's say thrillers for now.

Like comedy, yes, well, an action so you can use your own favorite genere here, name each favorite

genre hit send it says 200.

OK, that means everything went well on the producer.

Now go to the console and on the consumer console, we should see the data coming in.

So here is the consumer console.

And here on the left, user age is 40 favarouite genere

is thriller

So that's how the object serializer the Json serializer and the deserializers work.

The request goes from Postman to our producer.

It takes it.

It then uses the Json Serializer to serialize and send it to the consumer.

And on the consumer side, it is De serializing it.

And we see the data here internally, of course, it uses to Kafka api.

But spring makes things a lot easier.

Kafka Template spring class is used to send messages
The consumers method is marked with @KafkaListener
# Quiz
What is the change or commit log of records in kafka called 
Topic
Which of the following can be used to develop stream based applications
Kafka Streaming Api
Kafka connect can be used to bring in or send out data from Kafka to other data sources
True
A Kafka cluster is a combination of which of the following
Brokers
If a partion is not specified in the record which field in the record will be used to calculate a hash value
Key
The division of a Kafka Topic is called a
Partition
The number of replicas of partitions can be controlled using which of the following
Replication Factor
A consumer group ensures that a partition is consumed by only one consumer
True
Kafka producers batch messages based on
Topic and Partition
Which of the following command can be used to see all the available topics
kafka-topics --list
To delete a topic we need to have which of the following in the server.properties configuration file
delete.topic.enable = true
Which of the following is a Kafka API
Stream Api ,Admin Api,producer and Consumer Api
Kafka producer creates the partitions
False
Which of the following is not a part of a Kafka Producer API
put the record in partition and return metadata
The Kafka Broker expects the key and value in the record to be of which type
Byte Array
A synchronous send method call returns which of the following
Future<RecordMetaData>
The onCompletion method on the asynchronous callback class we create receives which of the following along with the RecordMetadata
Exception
Consumers use the serializer classes
False
The serialize method in a Serializer class returns which of the following type
byte[]
We can use the inbuilt ready to use Kafka api classes to serialize and deserialize object types
False
The Avro Serializer classes will push the schema to the schema registry automatically
True
While using generic record type we use the generated java object
Which of the following is are valid configuration value for ACKS_CONFIG 
0 1 all
All the messages send by the producer are compressed by default
False
Which of the following is best when it comes to compression ratio
gzip
What is the default time that the producer waits before a retry
100 milli secods
Using the BATCH_SIZE_CONFIG we can specify the number of records a batch can have
False
In Atleast once delivery we will not have message duplication
False
Which of the following is the default message delivery semantic used by Kafka
AtLeast once delivery
Which of the following method should be invoked before calling the beginTransaction
initTransaction
A producer instance can open multiple transactions  at the same time
False
Moving the partition ownership from one consumer to another is called
Consumer Group Rebalancing
The group coordinator is the one that does rebalancing
False
If a rebalance happens when some records are processed but not committed which of the following will happen
Records processed multiple times(duplicates)
If a rebalance happens when records are committed but not yet processed which of the following will happens
Records not being processed
Which of the following will allow us take action when a rebalance is triggered
Implementation of consumer rebalance listener
If both FETCH_MIN_BYTES_CONFIG and FETCH_MAX_WAIT_MS_CONFIG are configured whichever value is reached first will be used
True
What is recommended fractional value for HEARTBEAT_INTERVAL_MS_CONFIG compared to SESSION_TIMEOUT_MS_CONFIG
1/3
If we want the consumer to start reading from the beginning of the partition that does not have a committed offset , what is the value to be set to AUTO_OFFSET_RESET_CONFIG
earliest
Which of the following classes is used to start building a topology
Streamuilder
Which of the following is a factory class that returns both Serializer and DeSerializer class instances
Serdes
The KafkaStreams constructor takes which of the two parameters
Topology and properties
The foreach method on KStream returns a KStream back
False

Which of the following methods on the topology object can be used to inspect the topology
describe
Which of the following methods should be used to change both the key and values
map
Which of the following spring class is used to send messages
KafkaTemplate
The consumers method is marked with which of the following spring annotations

Spring Kafka has inbuilt Json Serializers and DeSerialiers to serialize and deserialize object types
true
