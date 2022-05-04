/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univcod.dongalleto.rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.univcod.dongalleto.core.ControladorProveedor;
import org.univcod.dongalleto.model.Persona;
import org.univcod.dongalleto.model.Proveedor;

/**
 *
 * @author franc
 */
@Path("proveedor")
public class RestProveedor {

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("idProveedor") @DefaultValue("0") int idProveedor,
            @FormParam("idPersona") @DefaultValue("0") int idPersona,
            @FormParam("nombre") @DefaultValue("") String nombre,
            @FormParam("apellidoPaterno") @DefaultValue("") String apellidoPaterno,
            @FormParam("apellidoMaterno") @DefaultValue("") String apellidoMaterno,
            @FormParam("domicilio") @DefaultValue("") String domicilio,
            @FormParam("estatus") @DefaultValue("1") int estatus,
            @FormParam("tel1") @DefaultValue("") String tel1,
            @FormParam("rfc") @DefaultValue("") String rfc,
            @FormParam("razonSocial") @DefaultValue("") String razonSocial
    ) {
        String out = "";
        Persona p = new Persona();
        Proveedor prov = new Proveedor();
        ControladorProveedor ctrlProv = new ControladorProveedor();

        // Llenamos los datos del objeto de tipo Persona:
        p.setNombre(nombre);
        p.setApellidoPaterno(apellidoPaterno);
        p.setApellidoMaterno(apellidoMaterno);
        p.setDomicilio(domicilio);
        p.setEstatus(estatus);
        p.setTel1(tel1);

        // Llenamos los datos del objeto de tipo Cliente:
        prov.setId(idProveedor);
        prov.setRfc(rfc);
        prov.setRazonSocial(razonSocial);
        prov.setPersona(p);

        try {
            // Revisamos si el Persona NO tiene un ID:
            if (prov.getId() == 0) {
                ctrlProv.insert(prov); //Insertamos la cliente en la BD
            }
//            } else {
//                p.setId(prov.getId());
//            }
//            ctrlProv.update(prov); // Actualizamos la cliente en la BD

            out = new Gson().toJson(prov);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("filtro") @DefaultValue("") String filtro
    ) {
        String out = "";
        ControladorProveedor ctrlProv = new ControladorProveedor();
        List<Proveedor> proveedores = new ArrayList<>();
        try {

            proveedores = ctrlProv.getall(filtro);
            out = new Gson().toJson(proveedores);
        } catch (Exception e) {
            e.printStackTrace();

            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

}
