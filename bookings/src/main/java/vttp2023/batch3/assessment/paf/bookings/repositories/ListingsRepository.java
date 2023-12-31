package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.utility.ListingsUtility;

@Repository
public class ListingsRepository {
	private final String C_LISTINGS = "listings";

	private final String A_ID = "_id";
	private final String A_NAME = "name";
	private final String A_DESC = "description";
	private final String A_ADDRESS_COUNTRY = "address.country";
	private final String A_ADDRESS_STREET = "address.street";
	private final String A_ADDRESS_SUBURB = "address.surburb";
	private final String A_COUNTRY = "country";
	private final String A_STREET = "street";
	private final String A_SUBURB = "surburb";
	private final String A_PERSON = "accommodates";
	private final String A_PRICE = "price";
	private final String A_IMAGE_URL = "images.picture_url";
	private final String A_URL = "picture_url";
	private final String A_AMENITIES = "amenities";

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	ListingsUtility utility;
	
	//TODO: Task 2

	// db.listings.distinct("address.country")
	public List<String> getCountries() {
		return mongoTemplate.findDistinct(new Query(), A_ADDRESS_COUNTRY, C_LISTINGS, getClass(), String.class);
	}

	
	//TODO: Task 3

	// db.listings.aggregate([
	// 	{ $match: { "address.country": "Australia", $and: [{price: { $gte: 10 , $lte: 500 }}], 
	// 		accommodates: { $gte: 2 }}},
	// 	{ $lookup: {
	// 		from: "address", foreignField: "street", localField: "_id", as: "street"
	// 	}},
	// 	{ $project: { _id:1, description:1, street:1, "address.suburb":1, "address.country":1,
	// 		name:1, price:1,  "images.picture_url":1, amenities:1}},
	// 	{ $sort: {price: -1}
	// }])
	public List<Listing> getPreviews(String country, int person, double priceMin, double priceMax) {
		MatchOperation match = Aggregation.match(
			Criteria.where(A_ADDRESS_COUNTRY).is(country)
				.and(A_PERSON).gte(person)
				.andOperator(
					Criteria.where(A_PRICE).gte(priceMin),
					Criteria.where(A_PRICE).lte(priceMax)
				)
		);

		ProjectionOperation project = Aggregation.project(
			A_ID, A_NAME, A_DESC, A_ADDRESS_STREET, A_ADDRESS_SUBURB, A_ADDRESS_COUNTRY, 
			A_PRICE, A_IMAGE_URL, A_AMENITIES
		);

		SortOperation sort = Aggregation.sort(Sort.by(Direction.DESC, A_PRICE));

		Aggregation pipeline = Aggregation.newAggregation(match, project, sort);
		AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, C_LISTINGS, 
			Document.class);
		List<Document> docList = results.getMappedResults();

		return utility.getListingFromDocList(docList);
	}


	//TODO: Task 4

	// db.listings.aggregate([
	// 	{ $match: { _id: "16134812" }},
	// 	{ $lookup: {
	// 		from: "address", foreignField: "street", localField: "_id", as: "street"
	// 	}},
	// 	{ $project: { _id:1, description:1, street:1, "address.suburb":1, "address.country":1,
	// 		name:1, price:1,  "images.picture_url":1, amenities:1}
	// }])
	public Listing getListingById(String id) {
		// Criteria c = Criteria.where(A_ID).is(id);
		// Query q = Query.query(c);

		// List<Document> result = mongoTemplate.find(q, Document.class, C_LISTINGS);

		MatchOperation match = Aggregation.match(
			Criteria.where(A_ID).is(id)
		);

		ProjectionOperation project = Aggregation.project(
			A_ID, A_NAME, A_DESC, A_ADDRESS_STREET, A_ADDRESS_SUBURB, A_ADDRESS_COUNTRY, 
			A_PRICE, A_IMAGE_URL, A_AMENITIES
		);

		Aggregation pipeline = Aggregation.newAggregation(match, project);
		AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, C_LISTINGS, 
			Document.class);
		List<Document> docList = results.getMappedResults();

		return utility.getListingFromDocList(docList).get(0);
	}
	

	//TODO: Task 5


}
