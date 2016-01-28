/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;

import gamepub.db.entity.User;
import gamepub.db.service.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author fitok
 */
@ManagedBean
public class menuModelBean {
private MenuModel menubar;
  
@EJB  
UserService userService;

    public MenuModel getMenubar(List<String> users){
         menubar = new DefaultMenuModel();
        DefaultSubMenu subMenu = new DefaultSubMenu("Capable to exchange users");
        for (String u:users){
            int id = userService.getUserByLogin(u).getId();
            DefaultMenuItem it = new DefaultMenuItem(u); 
            it.setIncludeViewParams(true);
            it.setOutcome("profile");
            it.setParam("userId",id);
            it.setValue(u);
            subMenu.addElement(it);
        }
        this.menubar.addElement(subMenu);
        return menubar;
    }
    public void setMenubar(MenuModel menubar){
        this.menubar=menubar;
    }
    public void navigate(){
        
    }
    
}
