/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.mityc.javasign.issues;

import java.security.cert.X509Certificate;
  
import es.mityc.javasign.pkstore.IPassStoreKS;
 
  /**
   * <p>Permite automatizar el acceso a las contraseñas de los almacenes de certificados de testeo.</p>
   * 
   */
public class PassStoreKS implements IPassStoreKS {
  	
  	/** Contraseña de acceso al almacén. */
  	private transient String password;
  	
  	/**
  	 * <p>Crea una instancia con la contraseña que se utilizará con el almacén relacionado.</p>
  	 * @param pass Contraseña del almacén
  	 */
  	public PassStoreKS(final String pass) {
  		this.password = new String(pass);
  	}
  
  	/**
  	 * <p>Devuelve la contraseña configurada para este almacén.</p>
  	 * @param certificate No se utiliza
  	 * @param alias no se utiliza
  	 * @return contraseña configurada para este almacén
  	 * @see es.mityc.javasign.pkstore.IPassStoreKS#getPassword(java.security.cert.X509Certificate, java.lang.String)
  	 */
  	public char[] getPassword(final X509Certificate certificate, final String alias) {
  		return password.toCharArray();
  	}
  
 }
