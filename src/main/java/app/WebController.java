package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
/**
 * Created by netwave on 18/05/15.
 */
@Controller
public class WebController {


    @Autowired
    private DatabaseTalkie dbt;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root()
    {
        return new ModelAndView("index");
    }


    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ModelAndView store(@RequestParam String TagName_s,
                              @RequestParam String Data,
                              @RequestParam String Pwd_s)
    {
        String ret_msg = "Could not store it, Tag Name must be unique";
        boolean try_store = dbt.store(TagName_s, Data, Pwd_s);
        if(try_store) ret_msg = "Succesfully Stored";
        return new ModelAndView("succes").addObject("stored", ret_msg);
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public ModelAndView retrieve(@RequestParam String TagName_r,
                                 @RequestParam String Pwd_r)
    {
        String data_r = dbt.retrieve(TagName_r, Pwd_r);
        boolean retrieved = !data_r.equals("");

        return new ModelAndView("show").addObject("retrieved", retrieved)
                .addObject("data", data_r)
                .addObject("tag", TagName_r);
    }

}
