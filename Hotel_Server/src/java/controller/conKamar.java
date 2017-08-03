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
import model.Kamar;

/**
 *
 * @author Lukas
 */
public class conKamar 
{
    private DBConnection con;
    private String prefixKamar;
    private String prefixTiket;
    private String prefixJalur;
    
    private String prefixKodeKamar;
    private String prefixNamaKamar;
    private String prefixLantaiKamar;
    private String prefixTipeKamar;
    private String prefixHarga;
    
    public conKamar() 
    {
        con = new Koneksi().getConnection();
        prefixKamar = "http://www.hotel.com/kamar/";
        
        prefixKodeKamar = prefixKamar + "kodeKamar";
        prefixNamaKamar = prefixKamar + "namaKamar";
        prefixLantaiKamar = prefixKamar + "lantaiKamar";
        prefixTipeKamar = prefixKamar + "tipeKamar";
        prefixHarga = prefixKamar + "harga";
    }
    
    public List<Kamar> getAllKamar() 
    {
        List<Kamar> listKamar = new ArrayList<>();
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        
        String query = "PREFIX tb_kamar:<" + prefixKamar + "> "
                + "SELECT "
                + "?kode_kamar ?nama_kamar ?lantai_kamar ?tipe_kamar ?harga " 
                + "WHERE { "
                + "?x tb_kamar:kode_kamar ?kode_kamar; "
                + "tb_kamar:nama_kamar ?nama_kamar; "
                + "tb_kamar:lantai_kamar ?lantai_kamar; "
                + "tb_kamar:tipe_kamar ?tipe_kamar; "
                + "tb_kamar:harga ?harga . "
                + "}";
        
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) 
        {
            QuerySolution querySolution = resultSet.nextSolution();
            Kamar kamar = new Kamar();
            kamar.setKodeKamar(querySolution.getLiteral("kodeKamar").getString());
            kamar.setNamaKamar(querySolution.getLiteral("namaKamar").getString());
            kamar.setLantaiKamar(querySolution.getLiteral("lantaiKamar").getString());
            kamar.setTipeKamar(querySolution.getLiteral("tipeKamar").getString());
            kamar.setHarga(querySolution.getLiteral("harga").getString());
            listKamar.add(kamar);
        }
        return listKamar; //mereturn listKamar
    }
    
    public boolean insertKamar(Kamar kamar) 
    { 
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin(); 

        model.setNsPrefix("tb_kamar", prefixKamar); //membuat prefix nama dari prefixKamar
        Property propKodeKamar = model.createProperty(prefixKodeKamar);
        Property propNamaKamar = model.createProperty(prefixNamaKamar);
        Property propLantaiKamar = model.createProperty(prefixLantaiKamar);
        Property propTipeKamar = model.createProperty(prefixTipeKamar);
        Property propHarga = model.createProperty(prefixHarga);

        Resource resource = model.createResource(prefixKamar + kamar.getKodeKamar());
        resource.addProperty(propKodeKamar, kamar.getKodeKamar());
        resource.addProperty(propNamaKamar, kamar.getNamaKamar());
        resource.addProperty(propLantaiKamar, kamar.getLantaiKamar());
        resource.addProperty(propTipeKamar, kamar.getTipeKamar());
        resource.addProperty(propHarga, kamar.getHarga());
    
        model.commit(); // commit itu untuk perintah harus di lakukan insert nya
        return true; 
    }

    public boolean deleteKamar(String kodeKamar) 
    {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();
        
        model.getResource(prefixKamar + kodeKamar).removeAll(null);
        
        model.commit();
        return true;
    }   
}
