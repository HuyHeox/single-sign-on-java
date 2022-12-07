package com.ks.sso.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ks.sso.config.DomainConfig;
import com.ks.sso.exception.ServerException;
import com.ks.sso.service.SsoService;
import com.ks.sso.service.DTO.REQ.LoginReqDTO;

@Controller
public class SsoController {
    @Autowired
    private SsoService ssoService;
    public static final Logger log = Logger.getLogger("hungnt");


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request, HttpServletResponse response) throws ServerException {
        return "redirect:/sso/login";
    }


    @GetMapping(value = "/ssoserver/sso")
    public String getLoginData(Model model,@RequestParam String redirectUrl, @RequestParam String clientId, @RequestParam String encodedState, HttpServletResponse response) {
    	List<String> domains = DomainConfig.mapClientIdDomain().get(clientId);
    	boolean isOk = false;
    	if(domains != null ) {
    		for (String url : domains) {
				if(url.contains(redirectUrl) || redirectUrl.contains(url)) {
					isOk = true;
					break;
				}
			}
    	}
    	if(isOk) {
    		ssoService.GetLoginData(redirectUrl, clientId, encodedState, response);
    		return "redirect:/sso/login";
    	} else {
             return "notAllow";
    	}
    }

    @RequestMapping(value = "/sso/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServerException {
        boolean availableLogin = ssoService.Login(null, request, response);
        if (!availableLogin) return "index";
        return "loggedIn"; 
    }

    @RequestMapping(value = "/sso/login", method = RequestMethod.POST)
    public String login(Model model, @ModelAttribute(value = "loginReqDTO") LoginReqDTO loginReqDTO, HttpServletRequest request, HttpServletResponse response) throws ServerException {
        boolean availableLogin = ssoService.Login(loginReqDTO, request, response);
        if (!availableLogin) {
            model.addAttribute("credential", "bad");
            return "index";
        }
        return "loggedIn"; 
    }
    
    @RequestMapping(value = "/sso/error", method = RequestMethod.POST)
    public String ssoError(Model model, @ModelAttribute(value = "loginReqDTO") LoginReqDTO loginReqDTO, HttpServletRequest request, HttpServletResponse response) throws ServerException {
            model.addAttribute("credential", "bad");
            return "index";
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<Object> getObject(@RequestParam String token, @RequestParam String clientId, @RequestParam String state, HttpServletRequest request, HttpServletResponse response) { 
        return ResponseEntity.ok(ssoService.GetObject(token, clientId, state, request, response));
    }

    
    @GetMapping("ssoserver/sso/logout")
    public void ssoLogout(HttpServletRequest request,HttpServletResponse response,@RequestParam String redirectUrl){
    	ssoService.logout(request,response);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", redirectUrl);
    }

    @GetMapping("/sso/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        ssoService.logout(request,response);
        return "redirect:/";
    }
    

}
