/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.utils;

import ua.metlife.claims.simplecs.processing.Convert;
import ua.metlife.claims.simplecs.processing.DateTools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author NPopov
 */



public class ClaimSystemLink {


    //--------------------------------------------------------------------------
    public boolean addToCRSFCLM(Connection conn) {

        String sql = "insert into CRSFCLM "
                + " ("
                + "CLMNO,CRFID,CLMON,STAT,STATDTE,"
                + "CHCK,FAOK,PAOK,CATEG,DEATH,"
                + "CAUSE,EVENT,INAME,IBTHD,IIDNO,"
                + "IADR1,IADR2,IZIP,ICITY,EVTDT,"
                + "NTFDT,ENTDT,COVAMT,RESAMT,BFTAMT,"
                + "BFTTOT,DCLOBJ,DCLP2S,HCLLSQN,SETLSQN,"
                + "FLWLSQN,FMELSQN,FLELSQN,SETPAID,SETPDTE,"
                + "FMEAMT,FLEAMT,RECUSR,RECDTE) "
                + " VALUES "
                + " (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, " 21.056799");//"csObject.getCsClaimPolicyNumber()"
            stmt.setString(2, "C");
            stmt.setString(3, "");
            stmt.setString(4, "U");
            stmt.setString(5, DateTools.getDateNowYmd());
            stmt.setString(6, "N");
            stmt.setString(7, "Y");
            stmt.setString(8, "Y");

            stmt.setString(9, "");
            stmt.setString(10, "N"); //csObject.getDeathStatus().equals("death") ? "Y" : "N");

            stmt.setString(11, "");
            stmt.setString(12, "");

            stmt.setString(13, "INSURES NAME"); //Convert.checkString(csObject.getInsObject().getName(), 30)
            stmt.setString(14, "20210202"); //Convert.checkString(csObject.getInsObject().getDob(), 8)
            stmt.setString(15, "");

            stmt.setString(16, Convert.checkString( "address", 30)); //csObject.getInsObject().getAddress()
            stmt.setString(17, "");
            stmt.setString(18, "");
            stmt.setString(19, "");

            stmt.setString(20, "");
            stmt.setString(21, "");
            stmt.setString(22, DateTools.getDateNowYmd());  // ENTDT = РґР°С‚Р° РѕС‚РєСЂС‹С‚РёСЏ
            stmt.setBigDecimal(23, new BigDecimal(1000)); //csObject.getTotalAmountPayment()

            stmt.setBigDecimal(24, new BigDecimal("0.00"));
            stmt.setBigDecimal(25, new BigDecimal("0.00"));
            stmt.setBigDecimal(26, new BigDecimal("0.00"));

            stmt.setString(27, "");
            stmt.setBigDecimal(28, new BigDecimal("0.00"));

            stmt.setString(29, "0001");
            stmt.setString(30, "0001");

            stmt.setString(31, "0000");
            stmt.setString(32, "0000");
            stmt.setString(33, "0000");

            stmt.setString(34, "");
            stmt.setString(35, "");

            stmt.setBigDecimal(36, new BigDecimal("0.00"));
            stmt.setBigDecimal(37, new BigDecimal("0.00"));
            stmt.setString(38, "L_OLASS");
            stmt.setString(39, DateTools.getDateNowYmd());

            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQL list>: " + sql);
        }
        return false;
    }

    public static String nextClaimNumberForClaim(Connection conn, Integer year) {
        if (year == null) {
            year = Integer.parseInt(String.valueOf(DateTools.getCurrentYear().toString().substring(2, 4)));
        } else {
            year = Integer.parseInt(String.valueOf(year.toString().substring(2, 4)));
        }
        String res = null;
        Integer counter = null;
        String sql = ""
                + "select CLMNO, substr(CLMNO,5,10) as LN "
                + "from  CRSFCLM  "
                + "where substr(CLMNO,2,2)= ? "
                + "ORDER BY substr(CLMNO,5,10) DESC";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                counter = rs.getInt("LN") + 1;
                break;
            }
            stmt.close();

        } catch (SQLException err) {
            err.getMessage();
        }

        String suffix = claimNumberSuffix (counter);
        res =  " " + year + suffix;
        return res;
    }

    public static String nextClaimNumberForCrl(Connection conn, Integer year) {
        if (year == null) {
            year = Integer.parseInt(String.valueOf(DateTools.getCurrentYear().toString().substring(2, 4)));
        } else {
            year = Integer.parseInt(String.valueOf(year.toString().substring(2, 4)));
        }
        String res = null;
        Integer counter = null;
        String sql = ""
                + "select POLNO, substr(POLNO,5,10) as LNC "
                + "from  CRSFCRP  "
                + "where substr(POLNO,2,2)= ? "
                + "ORDER BY substr(POLNO,5,10) DESC";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                counter = rs.getInt("LNC") + 1;
                break;
            }
            stmt.close();

        } catch (SQLException err) {
            err.getMessage();
        }

        String suffix = claimNumberSuffix (counter);
        res =  "C" + year + suffix;
        return res;
    }

    private static String claimNumberSuffix(Integer counter){

        String suffix = ".000001";
        if (counter != null) {
            suffix = "."+String.format("%06d", counter);
        }
        return suffix;
    }

}
