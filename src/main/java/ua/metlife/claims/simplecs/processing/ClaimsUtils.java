package ua.metlife.claims.simplecs.processing;

public class ClaimsUtils {


//--------------------------------------------------------------------------
private String claimNumberSuffix(Integer counter){

    String suffix = ".000001";
    if (counter != null) {
        suffix = "."+String.format("%06d", counter);
    }
    return suffix;
}

}
