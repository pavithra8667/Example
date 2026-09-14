package com.infinite.ehrSystem.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OAuthCallbackController {

    @GetMapping("/login/oauth2/code/ehr-client")
    @ResponseBody
    public String callback(
            @RequestParam String code) {

        return """
                <h2>Authorization Code Generated Successfully</h2>
                <br>
                <b>Authorization Code:</b>
                <br><br>
                """ + code;
    }
}