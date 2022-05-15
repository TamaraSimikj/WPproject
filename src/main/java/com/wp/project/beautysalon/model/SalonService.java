package com.wp.project.beautysalon.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SalonService {
    @Id
    //@GeneratedValue
    private String serviceId;

    private String serviceName; //dejnost

    private Integer price;

   @OneToMany
    private List<Rate> serviceRates = new ArrayList<>();

   @ManyToMany
    private List<User> employees;

   public SalonService(){
   }

    public SalonService(String serviceId, String serviceName, Integer price, List<User> employees) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
        this.employees = employees;
    }

    public Double getAvgRating(){
        double sum = 0;
        double num = 0;
        for (Rate x : this.serviceRates){
            if(x.getRatedSalonService().getServiceId().equals(this.serviceId)){
                sum+= x.getRateValue();
                num++;
            }

        } return sum/num;
//        for(Rate x : this.serviceRates)
//            sum+= x.getRateValue();
//
//       return sum/this.serviceRates.size();



    }
}
