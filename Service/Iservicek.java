/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Mon Pc
 */
public interface Iservicek<C> {
  public void ajouter( C C);
  public void supprimer( C C);
  public void modifier( C C);
  public List<C> afficher();
    
}
