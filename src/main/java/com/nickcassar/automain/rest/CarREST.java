/**
 * Class to handle the RESTful APIs for Cars
 */

package com.nickcassar.automain.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.nickcassar.automain.controllers.DatabaseOperations;
import com.nickcassar.automain.models.Car;

@Path("Car")
public class CarREST {

	@PUT
	@Consumes({ "application/xml", "application/json" })
	public Response edit(Car c) {
    DatabaseOperations.updateCarRecord(c.getCarId(), c.getMake(), c.getModel(), c.getYear(), c.getOdometerReading(), c.getType());
		return Response.ok().entity("Car edited successfully").build();
  }
  
  @PUT
	@Consumes({ "application/xml", "application/json" })
	public Response create(String make, String model, int year, double oReading, String type) {
    DatabaseOperations.createRecord(make, model, year, oReading, type);
		return Response.ok().entity("Car edited successfully").build();
	}

	@DELETE
	@Path("remove/{carId}")
	public Response remove(@PathParam("carId") Long carId) {
    DatabaseOperations.deleteCarRecord(carId);
		return Response.ok().entity("Car deleted successfully").build();
	}

	@GET
	@Path("{carId}")
	@Produces({ "application/json" })
	public Car find(@PathParam("carId") Long carId) {
		return DatabaseOperations.findCarRecord(carId);
	}

  @GET
  @Path("test")
  @Produces("text/plain")
  public String displayTest() {
    return "TEST";
  }

  @GET
  @Path("count")
  @Produces("text/plain")
  public String displayCount() {
    return String.valueOf(DatabaseOperations.displayCarRecords().size());
  }

  @GET
  @Produces( { "application/json" })
	public List<Car> findAll() {
		return DatabaseOperations.displayCarRecords();
	}
}
	