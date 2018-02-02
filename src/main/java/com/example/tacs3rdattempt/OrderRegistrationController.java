package com.example.tacs3rdattempt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Controller
public class OrderRegistrationController {

    @Autowired
    ProductRepository pr;

    @Autowired
    SoldProductRepository spr;

    //http://localhost:4000/registerOrder?productName=taco1&price=45.5 &date=2000-10-31T01:30:00.000-05:00 for testing

    static List<Product> listOfProducts = new ArrayList<>();

@GetMapping("/registerOrder")
    public String registerOrder(
            @RequestParam(name="productName", defaultValue = "NO_PRODUCT")
            String productName,
            @RequestParam(name= "price", defaultValue = "-1.0")
            double price
            /*@RequestParam(name= "dateTime", defaultValue = "NO_DATE")
            @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime dateTime*/
){

    Product toCheckIfExists= pr.findByProductName(productName);
    Product toRegister;
    if(toCheckIfExists==null){
        toRegister = new Product ();
        toRegister.setProductName(productName);
        toRegister.setPrice(price);
        pr.save(toRegister);
    }else{
        toRegister=toCheckIfExists;
    }


    Date dateTime = new Date();
    SoldProduct soldProduct = new SoldProduct();
    soldProduct.setSoldDate(dateTime);
    soldProduct.setProduct(toRegister);
    spr.save(soldProduct);

    return "SavedProducts";
}


    //example of recieved request: http://localhost:63342/quantitiesReport?startdate=2017-11-08&finishdate=2017-11-17
@RequestMapping("/quantitiesReport")
@ResponseBody()
    public ModelAndView getQuantityReport(
            @RequestParam(name= "startdate", defaultValue = "2018-01-13")
            @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
                    Date startDate,
            @RequestParam(name= "finishdate", defaultValue = "2018-01-17")
            @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
                    Date finishDate
){

    //dates are received with the time set at 01:00, we add another 24 hours in order count the sales made during the finishDate
    finishDate.setTime(finishDate.getTime()+86400000);


    //this is the object we will put inside the ModelAndView
    List <DailyReport> dailyReportList= new ArrayList<>();


    //we start from the startDate and repeat till we reach the finishDate
    do{
        //TODO remove
        System.out.println(startDate);
        System.out.println(finishDate);

        //First we get an iterator with all the existing products.
        Iterator<Product> products = pr.findAll().iterator();


        //we make this day's daily report
        DailyReport todaysDailyReport= new DailyReport();
        //we set the date of the report
        todaysDailyReport.setDate(new Date(startDate.getTime()));

        //we iterate through each existing product
        while(products.hasNext()){
            Product thisProduct= products.next();
            //we get from the database all the sales of this product today.
            Date endOfThisDay = new Date(startDate.getTime()+86400000);
            List<SoldProduct> salesOfThisProductTodayList = spr.findByProductAndSoldDateBetween(thisProduct, startDate, endOfThisDay);
            //TODO remove
            System.out.println("the sales of the product with id:"+thisProduct.getId()+" are: "+salesOfThisProductTodayList);
            //we make a new line for the daily report, about the sales of this product today
            DailyReportLine thisProductsLine = new DailyReportLine();
            //we set the product of the line to this product
            thisProductsLine.setProduct(thisProduct);
            //we set to sero the lunch and dinner counters
            int lunchSales=0;
            int dinnerSales=0;
            //we go through each sale of this product today, in order to count the sales
            for(SoldProduct sp: salesOfThisProductTodayList){

                //we change the format of the date for convenience
                Date date = sp.getSoldDate();
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(date);

                System.out.println("BOO");

                //lunch shift
                if(calendar.get(Calendar.HOUR_OF_DAY) <= SHIFTS.LUNCH_END){
                    lunchSales++;

                }
                //dinner shift
                else{
                    dinnerSales++;
                }
            }

            //here we set the lunch and dinner sales of the reportline
            thisProductsLine.setLunchSales(lunchSales);
            thisProductsLine.setDinnerSales(dinnerSales);
            //here we add the report line to the daily report
            todaysDailyReport.getDailyReportLineList().add(thisProductsLine);
            //TODO remove after debugging
            System.out.println(lunchSales);
            System.out.println(dinnerSales);
        }
        dailyReportList.add(todaysDailyReport);

        for (DailyReport dailyReport: dailyReportList
                ) {
            System.out.println("daily report date: "+dailyReport.date);

        }

        //now we add 24 hours in miliseconds to the starting date
        startDate.setTime(startDate.getTime()+86400000);
        System.out.println("daily report date before loop ends: "+todaysDailyReport.date);

    }while(startDate.before(finishDate));



    ModelAndView mv = new ModelAndView("productReport");
    mv.getModel().put("dailyReports", dailyReportList);

    return mv;
}

}
