package gamepub.beans;

import gamepub.db.entity.City;
import gamepub.db.entity.Country;
import gamepub.db.entity.User;
import gamepub.db.service.CityService;
import gamepub.db.service.CountryService;
import gamepub.db.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 26.01.16.
 */
@ManagedBean
@SessionScoped
@ViewScoped
public class AllUsersBean {
    @EJB
    UserService userService;
    @EJB
    CountryService countryService;
    @EJB
    CityService cityService;

    List<City> cities;

    String userName;
    Integer countryId;
    Integer cityId;

    public List<User> getUsers() {
        List<HashMap.Entry<String, Object>> parametersList = new ArrayList<HashMap.Entry<String, Object>>();
        Map.Entry<String, Object> param;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().containsKey("name") && context.getExternalContext().getSessionMap().get("name").toString().length() > 0) {
            param = new HashMap.SimpleEntry<String, Object>("login", context.getExternalContext().getSessionMap().get("name"));
            parametersList.add(param);
        }
        if (context.getExternalContext().getSessionMap().containsKey("countryId")) {
            param = new HashMap.SimpleEntry<String, Object>("country", countryService.getCountryById((Integer) context.getExternalContext().getSessionMap().get("countryId")));
            parametersList.add(param);
        }
        if (context.getExternalContext().getSessionMap().containsKey("cityId")) {
            param = new HashMap.SimpleEntry<String, Object>("city", cityService.getCityById((Integer)context.getExternalContext().getSessionMap().get("cityId")));
            parametersList.add(param);
        }
        return userService.getUsersByCustomParams(parametersList);
    }

    public void search(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(countryId != null){
            facesContext.getExternalContext().getSessionMap().put("countryId", countryId);
        }

        if(userName != null){
            facesContext.getExternalContext().getSessionMap().put("name", userName);
        }

        if(cityId != null) {
            facesContext.getExternalContext().getSessionMap().put("cityId", cityId);
        }
    }

    public List<Country> getCountries(){
        return countryService.findAll();
    }

    public List<City> getCities(){
        return cities;
    }

    public void updateCities(){
        if (countryId == null)
            cities = null;
        cities = cityService.getCitiesById(countryId);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void clearSelectedCountry(){
        countryId = null;
    }

    public void clearSelectedCity(){
        cityId = null;
    }
}
