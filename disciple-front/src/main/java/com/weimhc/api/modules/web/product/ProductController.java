/**
 * 
 */
package com.weimhc.api.modules.web.product;

import com.weimhc.api.core.web.ApiBaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 套餐信息Controller
 * 
 * @author lc
 * @version 2017-06-16
 */
@Api
@ApiIgnore
@Controller("apiProductController")
@RequestMapping(value = "${apiPath}/product")
public class ProductController extends ApiBaseController {

}