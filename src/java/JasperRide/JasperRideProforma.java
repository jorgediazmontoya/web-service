/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperRide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import TiposComprobantes.Proforma;

/**
 *
 * @author yoelvys
 */
public class JasperRideProforma {

    public void CrearRide(Proforma proforma, String dirGuardarDoc, String ireportDir) throws FileNotFoundException, JRException {
        List data = new ArrayList();
        String dirPlantillas = dirPlantillaJasper(proforma.getRuc(), ireportDir);
        data.add(proforma);
        InputStream inputStream = new FileInputStream(new File(dirPlantillas + "/proforma.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        HashMap parameters = new HashMap();
        parameters.put("DIR_PLANTILLAS", dirPlantillas);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, dirGuardarDoc);

    }

    private String dirPlantillaJasper(String ruc, String ireportDir) {
        File f = new File(ireportDir + "/" + ruc);
        if (f.exists()) {
            return f.getAbsolutePath();
        }
        f = new File(ireportDir + "/Default");
        return f.getAbsolutePath();
    }

    private String impuestoRetencion(String codigo) {
        return codigo.equals("1") ? "RENTA" : (codigo.equals("2") ? "IVA" : "ISD");
    }

    private static String formatDouble(double num) {
        return String.format("%.2f", num).replace(",", ".");
    }
}
