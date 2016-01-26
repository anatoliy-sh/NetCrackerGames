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

    List<City> cities =new ArrayList<City>();
    List<Country> countries = new ArrayList<Country>();

    String userName;
    Integer countryId;
    Integer cityId;

    public List<User> getUsers() {
        List<HashMap.Entry<String, Object>> parametersList = new ArrayList<HashMap.Entry<String, Object>>();
        Map.Entry<String, Object> param;
        FacesContext context = FacesContext.getCurrentInstance();
        if (userName != null && userName.length()>0) {
            param = new HashMap.SimpleEntry<String, Object>("login", userName);
            parametersList.add(param);
        }
        if (countryId != null && countryId>0) {
            param = new HashMap.SimpleEntry<String, Object>("country", countryService.getCountryById(countryId));
            parametersList.add(param);
        }
        if (cityId != null && cityId>0) {
            param = new HashMap.SimpleEntry<String, Object>("city", cityService.getCityById(cityId));
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
        if (countries.size()==0) {
            Country c = new Country();
            c.setName("None");
            c.setId(0);
            countries.add(c);
            countries.addAll(countryService.findAll());
        }
        return countries;
    }

    public List<City> getCities(){
        if (cities.size()==0)
        {
            City c = new City();
            c.setId(0);
            c.setName("None");
            cities.add(c);
        }
        return cities;
    }

    public void updateCities(){
        cities = new ArrayList<City>();
        City c = new City();
        c.setId(0);
        c.setName("None");
        cities.add(c);
        if (countryId != null && countryId>0)
            cities.addAll(cityService.getCitiesById(countryId));
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

}
