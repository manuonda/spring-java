package com.data.projection.projections;

/**
 * Interfaz Close Projection :  Closed Projections
 * <b>PersonLocation</b> Only need somebody fields
 * @author dgarcia
 * 
 *
 */
public interface PersonLocation {
   String getFirstName();
   String getLastName();
   String getState();
   String getPhoneNumber();
}
