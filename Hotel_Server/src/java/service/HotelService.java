/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.conBooking;
import controller.conKamar;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Booking;
import model.Kamar;

/**
 *
 * @author Lukas
 */
@WebService(serviceName = "HotelService")
public class HotelService 
{

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllBooking")//nama operasi yang digunakan
    public List<model.Booking> getAllBooking() 
    {
        //membuat list Booking melalui model Booking yang telah dibuat pada package model
        return new conBooking().getAllBooking();//memanggil fungsi getAllBooking pada conBooking 
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertBooking")
    public boolean operation(@WebParam(name = "booking") Booking booking) 
    {
       return new conBooking().insertBooking(booking);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteBooking")
    public boolean deleteBooking(@WebParam(name = "id") String id) 
    {
        return new conBooking().deleteBooking(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllKamar")
    public List<model.Kamar> getAllKamar() 
    {
        //TODO write your implementation code here:
        return new conKamar().getAllKamar();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertKamar")
    public boolean insertKamar(@WebParam(name = "kamar") Kamar kamar) 
    {
        //TODO write your implementation code here:
        return new conKamar().insertKamar(kamar);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteKamar")
    public boolean deleteKamar(@WebParam(name = "kodeKamar") String kodeKamar) 
    {
        //TODO write your implementation code here:
        return new conKamar().deleteKamar(kodeKamar);
    }
}
