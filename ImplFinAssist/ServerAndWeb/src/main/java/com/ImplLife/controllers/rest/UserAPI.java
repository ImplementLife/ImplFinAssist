package com.ImplLife.controllers.rest;

import java.security.Principal;
import java.util.Map;

public interface UserAPI extends TransactionsAPI, ExcelAPI {
    Map<String, String> userInfo(Principal principal);
}
