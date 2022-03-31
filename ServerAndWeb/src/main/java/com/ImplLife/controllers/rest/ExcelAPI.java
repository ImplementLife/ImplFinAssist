package com.ImplLife.controllers.rest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface ExcelAPI {
    void procExcel(Principal principal, @RequestParam("excel") MultipartFile excel);
}
