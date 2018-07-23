/**
 * Class to handle the RESTful APIs for Maintenance Tasks
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
import com.nickcassar.automain.models.MaintenanceTask;;

@javax.inject.Singleton
@Path("MaintenanceTask")
public class MTaskREST {

	@PUT
	@Consumes({ "application/xml", "application/json" })
	public Response edit(MaintenanceTask mTask) {
    DatabaseOperations.updateMTRecord(mTask.getMaintenanceId(), mTask.getName(), mTask.getCost(), mTask.getTime());
		return Response.ok().entity("Maintenance Task edited successfully").build();
	}

	@DELETE
	@Path("remove/{maintenanceId}")
	public Response remove(@PathParam("maintenanceId") Long maintenanceId) {
    DatabaseOperations.deleteMTRecord(maintenanceId);
		return Response.ok().entity("Maintenance Task deleted successfully").build();
	}

	@GET
	@Path("{maintenanceId}")
	@Produces({ "application/json" })
	public MaintenanceTask find(@PathParam("maintenanceId") Long maintenanceId) {
		return DatabaseOperations.findMTRecord(maintenanceId);
	}

	@GET
	@Produces({ "application/json" })
	public List<MaintenanceTask> findAll() {
		return DatabaseOperations.displayMaintenaceRecords();
	}
}
	