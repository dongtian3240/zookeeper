package zookeeper.start.simple;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class Client {

	private static Logger logger = Logger.getLogger(Client.class);
	
	public static void main(String[] args) throws Exception {
		
		InputStream inputStream = Client.class.getClassLoader().getResourceAsStream("zk.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		logger.info("load properties ");
		ZooKeeper zk = new ZooKeeper(properties.getProperty("zk.servers"), 3000, new ZookeeperWatcher());
		Stat has = zk.exists("/znode", true);
		logger.info(" znode is " + has);
		
		
	}
	
	private static class ZookeeperWatcher implements Watcher {

		@Override
		public void process(WatchedEvent event) {
			
			logger.info("event is ：" + event.getType());
			
		}
		
	}
}
