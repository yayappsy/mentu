/**
 * 
 */
package com.weimhc.api.modules.web.order;

import com.weimhc.api.core.web.ApiBaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单信息Controller
 * 
 * @author lc
 * @version 2017-06-16
 */
@Api
@Controller("apiOrderController")
@RequestMapping(value = "${apiPath}/order")
public class OrderController extends ApiBaseController {

}