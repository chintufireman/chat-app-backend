package com.chatapp.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import javax.annotation.PostConstruct;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void initIndexes() {
		MongoCollection<Document> collection = mongoTemplate.getCollection("sender_receiver_relation");
		Document index = new Document().append("sender_id", 1).append("receiver_id", 1);
		collection.createIndex(index, new IndexOptions().unique(true));
	}
}
