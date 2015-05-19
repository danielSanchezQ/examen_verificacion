package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
/**
 * Created by netwave on 18/05/15.
 */
public class WebController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView root()
    {
        return new ModelAndView("index");
    }
}
