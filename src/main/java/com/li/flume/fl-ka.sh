 nohup ../bin/flume-ng agent  --name kafka2hdfs  /usr/local/apache-flume-1.6.0-bin/conf  --conf-file /usr/local/apache-flume-1.6.0-bin/conf/flume-kafka-agent.conf  -Dflume.root.logger=INFO,console > kafkaout.tx 2>&1 &