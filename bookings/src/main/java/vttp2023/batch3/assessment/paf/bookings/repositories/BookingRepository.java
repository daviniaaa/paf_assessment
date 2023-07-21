package vttp2023.batch3.assessment.paf.bookings.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.utility.BookingsUtility;
import vttp2023.batch3.assessment.paf.bookings.utility.ListingsUtility;

@Repository
public class BookingRepository {

    private final String SQL_GET_VACANCY = "select vacancy from acc_occupancy where acc_id = ?";
    private final String SQL_INSERT_BOOKING = "insert into reservations (resv_id,name,email,acc_id,arrival_date,duration) values (?, ?, ?,?,?,?)";
    private final String SQL_UPDATE_VACANCY = "update acc_occupancy set vacancy = ? where acc_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookingsUtility utility;

    public int getVacancyById(String id) {
        return jdbcTemplate.queryForObject(SQL_GET_VACANCY, BeanPropertyRowMapper
            .newInstance(Integer.class), id);
    }

    public int addBooking(Booking booking, String accId ) {
        return jdbcTemplate.update(SQL_INSERT_BOOKING, 
            utility.generateUUID(8), 
            booking.getName(), 
            booking.getEmail(),
            accId,
            booking.getArrivalDate(),
            booking.getDuration());
    }

    public int updateVacancy(int days, String id) {
        int newVacancy = (getVacancyById(id) - days);

        return jdbcTemplate.update(SQL_UPDATE_VACANCY, newVacancy, id);
    }
}
