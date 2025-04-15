package com.conference.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "An error occurred";
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode", statusCode);
            
            switch (statusCode) {
                case 404:
                    errorMessage = "Page not found";
                    break;
                case 500:
                    errorMessage = "Internal server error";
                    break;
                default:
                    errorMessage = "Unexpected error";
            }
        }
        
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
} 