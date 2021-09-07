package ua.metlife.claims.simplecs.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class processing {

    //если в CS_ID_LINK есть для данного клиета POLNO - берем его
    /*
    Scenario > Add Claim from Credit Life

    cs_id_link -> C_POLICY_ID || C_TAXCODE //идентификатор есть ли уже номер полиса
    INSERT INTO CRSFCRP (POLNO, PLOB, EFFDTE, MATDTE, ONAME, ADRS1, ADRS2, ZIP, CITY, RECUSR, RECDTE) VALUES
    ('C21.005675', '70', '20210415', '20210515', 'віаріва іварівар іваріо     ', '                              ', '                              ', '      ', '                              ', 'TKACHENKO ', '20210513');
    1. CRSFCRP > (if not exist for this Insured-> check CS_ID_LINK
    2. CRSFPOL > Add new claim
    3. CRSFCRR > Add products if it not exist for CRSFGPL.GPLPOLNO
    4. CRSFRSB
    5. CRSFCLM
    6. add data to CS_ID_LINK if not exist

     */

//    //--------------------------------------------------------------------------
//    //for CLMNO
//    public String nextClaimNumber(Integer year) {
//        if (year == null) {
//            year = Integer.parseInt(String.valueOf(DateTools.getCurrentYear().toString().substring(2, 4)));
//        }
//        String res = null;
//        Integer counter = null;
//        String sql = ""
//                + "select CLMNO, substr(CLMNO,5,10) as LN "
//                + "from  CRSFCLM  "
//                + "where substr(CLMNO,2,2)= ? "
//                + "ORDER BY substr(CLMNO,5,10) DESC";
//        try {
//            PreparedStatement stmt = GlobalConnection.getSqlConnection().prepareStatement(sql);
//            stmt.setInt(1, year);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                counter = rs.getInt("LN") + 1;
//                break;
//            }
//            stmt.close();
//
//        } catch (SQLException err) {
//            S.p(err.getMessage());
//        }
//
//        String suffix = claimNumberSuffix (counter);
//        res =  " " + year + suffix;
//        S.p("ClaimSystemLink.nextClaimNumber(), claim number is: "+res);
//        return res;
//    }
//
//
////--------------------------------------------------------------------------
//private String claimNumberSuffix(Integer counter){
//
//    String suffix = ".000001";
//    if (counter != null) {
//        suffix = "."+String.format("%06d", counter);
//    }
//    return suffix;
//}
//    //--------------------------------------------------------------------------
//    //for POLNO
//    public String nextClaimNumber(Integer year) {
//        if (year == null) {
//            year = Integer.parseInt(String.valueOf(DateTools.getCurrentYear().toString().substring(2, 4)));
//        }
//        String res = null;
//        Integer counter = null;
//        String sql = ""
//                + "select CLMNO, substr(CLMNO,5,10) as LN "
//                + "from  CRSFCLM  "
//                + "where substr(CLMNO,2,2)= ? "
//                + "ORDER BY substr(CLMNO,5,10) DESC";
//        try {
//            PreparedStatement stmt = GlobalConnection.getSqlConnection().prepareStatement(sql);
//            stmt.setInt(1, year);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                counter = rs.getInt("LN") + 1;
//                break;
//            }
//            stmt.close();
//
//        } catch (SQLException err) {
//            S.p(err.getMessage());
//        }
//
//        String suffix = claimNumberSuffix (counter);
//        res =  " " + year + suffix;
//        S.p("ClaimSystemLink.nextClaimNumber(), claim number is: "+res);
//        return res;
//    }
//
//
//    public void addToCRSFPOL() {
//
//        String sql = "insert into CRSFPOL "
//                + "("
//                + "CLMNO,POLNO,CRFID,CLMON,STAT,"
//                + "STATDTE,FAOK,RAOK,BNKASGN,INAME,"
//                + "IBTHD,IIDNO,ONAME,OBTHD,CPYFR,"
//                + "TYPE,PLOB,ISSDTE,EFFDTE,PAYDTE,"
//                + "CASTAT,BILMOD,MODPRM,ANNPRM,WCPPRM,"
//                + "COVAMT,RESAMT,BFTAMT,BFTTOT,CCAG1FC,"
//                + "EAAG1NM,CCAG2FC,EAAG2NM,DCLOBJ,DCLP2S,"
//                + "RECUSR,RECDTE"
//                + ") "
//                + " VALUES "
//                + " (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?)";
//
//        try {
//            csObject.setCsClaimPolicyNumber(nextClaimNumber(null));
//            PreparedStatement stmt = GlobalConnection.getSqlConnection().prepareStatement(sql);
//            stmt.setString(1, csObject.getCsClaimPolicyNumber()); // +1
//            stmt.setString(2, csObject.getCsPolNo()); // from exist or generate for user
//            stmt.setString(3, "P");
//            stmt.setString(4, "");
//            stmt.setString(5, "U");
//
//            stmt.setString(6, csObject.getClaimDate());
//
//            stmt.setString(7, "N");
//            stmt.setString(8, "N");
//            stmt.setString(9, "N");
//            stmt.setString(10, Convert.checkString(csObject.getInsObject().getName(), 30));
//
//            stmt.setString(11, Convert.checkString(csObject.getInsObject().getDob(), 8));
//            stmt.setString(12, "");
//            stmt.setString(13, Convert.checkString(csObject.getcObject().getCompanyShortNameLocal().trim().length() == 0 ? csObject.getcObject().getCompanyNameUa() : csObject.getcObject().getCompanyShortNameLocal(), 30));
//            stmt.setString(14, "");
//            stmt.setString(15, "G");
//
//            stmt.setString(16, "G");
//            stmt.setString(17, Convert.checkString(csObject.getDeathStatus().equals("death") ? "10" : "20", 2));
//            stmt.setString(18, "");//
//            stmt.setString(19, Convert.checkString(csObject.getCnObject().getIssueDate(), 8));
//            stmt.setString(20, "");
//
//            stmt.setString(21, "");
//            stmt.setString(22, "00");
//            stmt.setBigDecimal(23, new BigDecimal("0.00"));
//            stmt.setBigDecimal(24, new BigDecimal("0.00"));
//            stmt.setBigDecimal(25, new BigDecimal("0.00"));
//
//            stmt.setBigDecimal(26, csObject.getTotalAmountPayment());
//            stmt.setBigDecimal(27, new BigDecimal("0.00"));
//
//            stmt.setBigDecimal(28, new BigDecimal("0.00"));
//            stmt.setBigDecimal(29, new BigDecimal("0.00"));
//            stmt.setString(30, "");
//
//            stmt.setString(31, "");
//            stmt.setString(32, "");
//
//            stmt.setString(33, "");
//            stmt.setString(34, "");
//            stmt.setString(35, "0");
//            stmt.setString(36, GlobalConnection.getOlassLogin().toUpperCase());
//            stmt.setString(37, DateTools.getDateNowYmd());
//
//            stmt.executeUpdate();
//            stmt.close();
//        } catch (SQLException ex) {
//            System.err.println("SQLException: " + ex.getMessage());
//            System.err.println("SQL list>: " + sql);
//        }
//
//    }
//
}
