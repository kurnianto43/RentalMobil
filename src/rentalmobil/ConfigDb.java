/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmobil;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.io.File;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author asus
 */
public class ConfigDb {
    private String Password="";
    private String Username="root";
    private String Database="db_rentalan";
    
    public Connection koneksi;

    public ConfigDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            koneksi=DriverManager.getConnection("jdbc.mysql://localhost:3306/"+this.Database,
                    this.Username,this.Password);
                    System.out.println("Koneksi Berhasil");
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Maaf terjadi kesalahan pada bagian : \n["+e.toString()+"]");
        }
    }
    
    public Object[][] isiTabel(String SQL, int jumlah){
        Object[][] data=null;
        try{
            Statement st=ConfigDb.this.koneksi.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            rs.last();
            int baris=rs.getRow();
            rs.beforeFirst();
            int j=0;
            data=new Object[baris][jumlah];
                while(rs.next()){
                    for (int i=0; i<jumlah;i++){
                        data[j][i]=rs.getString(i+1);
                    }
                    j++;
                }
        }catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Maaf terjadi kesalahan pada bagian method isiTabel :\n["+e.toString()+"]");
                        }
                return data;
    }
}