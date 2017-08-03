/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import connection.Koneksi;
import java.util.ArrayList;
import java.util.List;
import model.Booking;

/**
 *
 * @author Lukas
 */
public class conBooking 
{
    private DBConnection con;
    private String prefixBooking;
    private String prefixKamar;
    
    
    private String prefixId;
    private String prefixKodeKamar;
    private String prefixNamaKamar;
    private String prefixKodePelanggan;
    private String prefixNama;
    private String prefixHarga;
    private String prefixCheckIn;
    private String prefixCheckOut;

    public conBooking() 
    {
        con = new Koneksi().getConnection();
        prefixBooking = "http://www.hotel.com/booking/";
        prefixId = prefixBooking + "id";
        prefixKodeKamar = prefixBooking + "kodeKamar";
        prefixNamaKamar = prefixBooking + "namaKamar";
        prefixKodePelanggan = prefixBooking + "kodePelanggan";
        prefixNama = prefixBooking + "nama";
        prefixHarga = prefixBooking + "harga";
        prefixCheckIn = prefixBooking + "checkIn";
        prefixCheckOut = prefixBooking + "checkOut";
        
    }
    
    public List<Booking> getAllBooking() 
    {
        List<Booking> listBooking = new ArrayList<>();
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        String query = "PREFIX booking:<" + prefixBooking + "> "
                + "SELECT "
                + "?kodeKamar ?namaKamar ?kodePelanggan ?nama ?harga ?checkIn ?checkOut "
                + "WHERE { "
                + "?x booking:kodeKamar ?kodeKamar; "
                + "booking:namaKamar ?namaKamar; "
                + "booking:kodePelanggan ?kodePelanggan; "
                + "booking:nama ?nama; "
                + "booking:harga ?harga; "
                + "booking:checkIn ?checkIn; "
                + "booking:checkOut ?checkOur . "
                + "}";

        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) 
        {
            QuerySolution querySolution = resultSet.nextSolution();
            Booking booking = new Booking();
            booking.setKodeKamar(querySolution.getLiteral("kodeKamar").getString());
            booking.setNamaKamar(querySolution.getLiteral("namaKamar").getString());
            booking.setKodePelanggan(querySolution.getLiteral("kodePelanggan").getString());
            booking.setNama(querySolution.getLiteral("nama").getString());
            booking.setHarga(querySolution.getLiteral("harga").getString());
            booking.setCheckIn(querySolution.getLiteral("checkIn").getString());
            booking.setCheckOut(querySolution.getLiteral("checkOut").getString());
            
            //resource.addProperty(kode, model.createResource("http://www.tiket.com/" + listTiket.get(i).getKode()));
            
            listBooking.add(booking);
        }
        return listBooking;
    }
    
    public boolean insertBooking(Booking booking) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.setNsPrefix("booking", prefixBooking);
        Property propKodeKamar = model.createProperty(prefixKodeKamar);
        Property propNamaKamar = model.createProperty(prefixNamaKamar);
        Property propKodePelanggan = model.createProperty(prefixKodePelanggan);
        Property propNama = model.createProperty(prefixNama);
        Property propHarga = model.createProperty(prefixHarga);
        Property propCheckIn = model.createProperty(prefixCheckIn);
        Property propCheckOut = model.createProperty(prefixCheckOut);

        Resource resource = model.createResource(prefixBooking + booking.getId());
        resource.addProperty(propKodeKamar, booking.getKodeKamar());
        resource.addProperty(propNamaKamar, booking.getNamaKamar());
        resource.addProperty(propKodePelanggan, booking.getKodePelanggan());
        resource.addProperty(propNama, booking.getNama());
        resource.addProperty(propHarga, booking.getHarga());
        resource.addProperty(propCheckIn, booking.getCheckIn());
        resource.addProperty(propCheckOut, booking.getCheckOut());

        model.commit();
        return true;
    }

    public boolean deleteBooking(String id) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();
        
        model.getResource(prefixBooking + id).removeAll(null);
        
        model.commit();
        return true;
    }   
}
