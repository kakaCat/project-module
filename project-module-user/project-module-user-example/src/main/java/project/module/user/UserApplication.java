package project.module.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常通知入口类
 */
@SpringBootApplication
@MapperScan("project.module.user.repository.rdb")
@RestController
public class UserApplication {
	/**
	 * logger new instance
	 */
	static Logger logger = LoggerFactory.getLogger(UserApplication.class);

	/**
	 * 程序入口方法
	 * @param args 参数
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
		logger.info("【【【【【业务异常统一处理-启动完成】】】】】");
	}

	@RequestMapping(value = "/index")
	public String index(){
		return "hellow";
	}

}
