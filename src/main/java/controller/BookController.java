package controller;

import cache.Book;
import cache.BookRepositoryImpl;
import cache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by psundriyal on 5/23/15.
 */

@RestController
public class BookController {

    @RequestMapping("/getbooks")
    public Book getBook() {
        return new BookRepositoryImpl().getByIsbn("1325");
    }

    @RequestMapping(value = "/putcache", method=RequestMethod.GET)
    public Element putCache(@RequestParam(value="id", required=false, defaultValue="0") String id,
                            @RequestParam(value="name", required=false, defaultValue="no-value") String name){
        Ehcache ehcache = new Ehcache();
        return ehcache.put(id, name);
    }

    @RequestMapping(value= "/get", method = RequestMethod.GET)
    public Element getCache(@RequestParam(value="id", required=false, defaultValue="0") String id) {
        Ehcache ehcache = new Ehcache();
        return ehcache.get(id);
    }

    @RequestMapping("/getall")
    public Map getall(){
        Ehcache ehcache = new Ehcache();
        return ehcache.getElements();
    }
}
