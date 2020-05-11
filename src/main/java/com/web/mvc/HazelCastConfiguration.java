package com.web.mvc;

import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
/**
 * Hazel cast config
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
@Configuration
@EnableCaching
public class HazelCastConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(HazelCastConfiguration.class);

	@Value("${hazelcast.ip.addresses:localhost}")
	private String ipAddresses;

	@Value("${hazelcast.public.ipaddress}")
	private String hazelPublicIp;

	@Bean
	public Config config() throws UnknownHostException{
		logger.info("Configurtion for hazelcast Started");
		Config config = new Config();
		config.setProperty("hazelcast.initial.min.cluster.size","1");

		NetworkConfig network = config.getNetworkConfig();
		//network.setPort(8085);
		if(null!=hazelPublicIp && hazelPublicIp.trim().length()>0)
			network.setPublicAddress(hazelPublicIp);
		JoinConfig join = network.getJoin();

		join.getAwsConfig().setEnabled(false);
		join.getMulticastConfig().setEnabled(false);
		join
		.getTcpIpConfig()
		.addMember(ipAddresses)
		.setRequiredMember(null)
		.setEnabled(true);
		//this sets the allowed connections to the cluster? necessary for multicast, too?
		//network.getInterfaces().setEnabled(true).addInterface("192.168.0.*");

		config
		.addMapConfig(new MapConfig().setName("LOCK_MAP").setNearCacheConfig(new NearCacheConfig()))
		.setProperty("hazelcast.logging.type","slf4j"); 
		logger.info("Configurtion for hazelcast : {}, is done", config);
		return config;
	}

	//this is to make sure spring is using this hazelcast manager, u can use same kind of config for redis, guavus. 
	//u can remove this code. this is very much spring specific, tellign soring to use which caching mehcanis
	@Bean
	public CacheManager cacheManager(HazelcastInstance instance) {
		return new HazelcastCacheManager(instance);
	}

	@Bean
	public HazelcastInstance hazelcastInstance(Config config) {
		logger.info("Building Hazel cast instance");
		// for client HazelcastInstance LocalMapStatistics will not available
		// return HazelcastClient.newHazelcastClient();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
		logger.info("Hazel cast instance : {}",instance);
		return instance;
	}

	@PreDestroy
	public void shutdownHazelCastInstance(){
		Hazelcast.shutdownAll();
	}
}

