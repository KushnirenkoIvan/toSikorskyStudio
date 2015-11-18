package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import webapp.dao.TextStringFileDaoImpl;
import webapp.domain.TextString;
import webapp.util.JSONConvertor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This is a Controller for page - index.jsp
 *
 * @see <b></>visitor_count</b> is a counter of visits;
 */

@Controller
public class IndexController {

    private int visitor_count;

    @Autowired
    private HashSet<String> ipAdresses;


    TextStringFileDaoImpl textStringFileDao = new TextStringFileDaoImpl();

    @RequestMapping(value = "/")
    public String getHomePage(Model model, HttpServletRequest request) {
        if (checkVisitorIp(request.getRemoteAddr())) {
            visitor_count++;
        }
        model.addAttribute("visitor_count", visitor_count);
        return "index";
    }

    private boolean checkVisitorIp(String ip) {
        if (ipAdresses.contains(ip)) {
            return false;
        }
        ipAdresses.add(ip);
        System.out.println(ipAdresses);
        return true;
    }

    @RequestMapping(value = "/save")
    public void save(HttpServletResponse response) {
        textStringFileDao.save();
        response.setStatus(4);
    }

    @RequestMapping(value = "/saveAll")
    public void saveAll(@RequestParam("infoStrings") String info) {
        textStringFileDao.getSession().setInfo(info);
        textStringFileDao.save();
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public
    @ResponseBody
    List addList(ModelAndView model) {
        List<TextString> list = textStringFileDao.findAll();
        List<String> result = new ArrayList<>(list.size());
        for (TextString ts : list) {
            result.add(JSONConvertor.convertToJSON(ts));
        }
        return result;
    }

    @RequestMapping(value = "/addString")
    public void addString(@RequestParam("TextString") String name) {
        int i = name.indexOf(" : ");
        textStringFileDao.create(new TextString(Long.parseLong(name.substring(0, i)), name.substring(i + 3)));
    }

}
