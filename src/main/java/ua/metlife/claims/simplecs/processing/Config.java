/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.processing;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author NPopov
 */
public class Config {

	static com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
	private static String __OWNER_LOGIN;
	public static String FormDateFormat = "dd.MM.yyyy";

	public static String getFormDateFormat() {
		return FormDateFormat;
	}

	public static String getOWNER_LOGIN() {
		return getCrsLogin(NTSystem.getName().toLowerCase());
	}

	public static String getCrsLogin(String ldapLogin) {
		Map<String, String> users = new TreeMap();
		users.put("oshumar", "SHUMAR");
		users.put("avoitovych", "VOITOVYCH");
		users.put("oburenok", "BURENOK");
		users.put("okorchenkov", "KORCHENKOV");
		users.put("vlypko", "LYPKO");
		users.put("anason", "NASON");
		users.put("okoropnichenko", "OKOROPNICH");
		users.put("gordienkoa", "GORDIENKO");
		users.put("selskag", "SELSKA");
		users.put("sbudishevska", "BUDISHEVS");
		users.put("oshumar", "SHUMAR");
		users.put("kumanskat", "KUMANSKA");
		users.put("npopov", "NASON-TEST");
		users.put("didykk", "DIDYK");

		if (users.containsKey(ldapLogin)) {
			return users.get(ldapLogin);
		} else {
			String und = ldapLogin.toUpperCase();
			if (und.length() > 9) {
				return "??????";
			} else {
				return "?" + ldapLogin.toUpperCase();
			}
		}

	}

}
