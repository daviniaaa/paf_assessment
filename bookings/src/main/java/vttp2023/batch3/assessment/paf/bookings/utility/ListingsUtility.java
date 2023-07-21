package vttp2023.batch3.assessment.paf.bookings.utility;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import vttp2023.batch3.assessment.paf.bookings.models.Listing;

@Component
public class ListingsUtility {
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
    
    public List<Listing> getListingFromDocList(List<Document> doc) {

        List<Listing> list = new LinkedList<>();
		for(Document d : doc) {
			Listing l = new Listing();
			l.setId(d.getString(A_ID));
			l.setName(d.getString(A_NAME));
			l.setDesc(d.getString(A_DESC));
			l.setStreet(d.getString(A_STREET));
			l.setSuburb(d.getString(A_SUBURB));
			l.setCountry(d.getString(A_COUNTRY));
			l.setImage(d.getString(A_URL));
			l.setPrice(d.getDouble(A_PRICE));
			l.setAmenities(d.getList(A_AMENITIES, String.class));

			list.add(l);
		}

        return list;
    }

}
