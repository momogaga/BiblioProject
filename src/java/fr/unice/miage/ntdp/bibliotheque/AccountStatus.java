/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque;

/**
 *
 * @author edou
 */
public enum AccountStatus {
  ATTENTE(0),
  ACTIF(1),
  BLOQUE(2);

  private int status;
  
  private AccountStatus(int status) {
        this.status= status;
    }
}
