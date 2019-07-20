package com.stackroute.kafka.domain;

import com.stackroute.userservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;



public class Consumer {
MyBookings myBookings=new MyBookings();
    @Autowired
    BookingService bookingService;
    @KafkaListener(topics = "BookingTopic")
    public void receive(@Payload Booking booking)
    {
        System.out.println("consumed booking" +booking.toString());
        myBookings.setSpaceName(booking.getMyUser().getMyCategory().getMySpace().getSpaceName());
        myBookings.setCategoryName(booking.getMyUser().getMyCategory().getCategoryName());
        myBookings.setLocationName(booking.getMyUser().getMyCategory().getMyLocation().getLocationName());
        myBookings.setPrice(booking.getMyUser().getMyCategory().getPrice());
        myBookings.setBookingId(booking.getBookingId());
        myBookings.setStartDate(booking.getStartDate());
        myBookings.setEndDate(booking.getEndDate());
        myBookings.setName(booking.getMyUser().getName());
        bookingService.saveUserBookedDetails(myBookings);
        System.out.println("+++++++++"+myBookings.toString());
    }

}


