package com.zaqbest.study.workflow;

import cn.hutool.core.util.SystemPropsUtil;
import com.zaqbest.workflow.config.ActivitiConfig;
import com.zaqbest.study.BaseTest;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QingJiaProcessInstanceTest extends BaseTest {

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	ProcessEngineConfiguration processEngineConfiguration;

	@Autowired
	HistoryService historyService;

	/**
	 * 如果不存在就会自动建表，这里不需要执行
	 *
	 * {@link ActivitiConfig#processEngineConfiguration(javax.sql.DataSource, org.springframework.transaction.PlatformTransactionManager)}
	 */
	public void createActivitiEngine(){
		/*        *1.通过代码形式创建
		 *  - 取得ProcessEngineConfiguration对象
		 *  - 设置数据库连接属性
		 *  - 设置创建表的策略 （当没有表时，自动创建表）
		 *  - 通过ProcessEngineConfiguration对象创建 ProcessEngine 对象*/

		//取得ProcessEngineConfiguration对象
		ProcessEngineConfiguration engineConfiguration=ProcessEngineConfiguration.
				createStandaloneProcessEngineConfiguration();
		//设置数据库连接属性
		engineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		engineConfiguration.setJdbcUrl("jdbc:mysql://node01.zaqbest.com:3306/zaqstudydb_dev?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
		engineConfiguration.setJdbcUsername("zaqstudydb_dev");
		engineConfiguration.setJdbcPassword("zYcyxhAywjwyLp3h");


		// 设置创建表的策略 （当没有表时，自动创建表）
		//          public static final java.lang.String DB_SCHEMA_UPDATE_FALSE = "false";//不会自动创建表，没有表，则抛异常
		//          public static final java.lang.String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";//先删除，再创建表
		//          public static final java.lang.String DB_SCHEMA_UPDATE_TRUE = "true";//假如没有表，则自动创建
		engineConfiguration.setDatabaseSchemaUpdate("true");
		//通过ProcessEngineConfiguration对象创建 ProcessEngine 对象
		ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
		System.out.println("流程引擎创建成功!");
	}


	@Test
	@Order(2)
	public void delpoyActiviti() {
		//创建部署对象

		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		//加载流程的配置文件和图片
		deploymentBuilder.addClasspathResource("QingJia.bpmn20.xml")
				.name("请假流程")
				.category("请假")
				.addClasspathResource("QingJia.bpmn20.png");
		//部署流程
		deploymentBuilder.deploy();
	}

	@Test
	@Order(3)
	public void startProcess() {
		runtimeService.startProcessInstanceByKey("QingJia");
	}

	@Test
	@Order(4)
	public void queryTaksByAssignee() {
		//String assignee = "yojey";
		String assignee = "boss";
		List<Task> taskList = taskService.createTaskQuery()//创建任务查询对象
				.processDefinitionKey("QingJia")//根据流程定义的key来查询
				.taskAssignee(assignee)//根据办理人来查询
				.orderByTaskCreateTime()//根据时间倒序
				.desc()
				.list();

		for(Task task : taskList){
			System.out.println("任务的ID："+task.getId());
			System.out.println("任务的名字："+task.getName());
			System.out.println("任务的创建时间："+task.getCreateTime());
			System.out.println("办理人："+task.getAssignee());
		}

	}

	@Test
	@Order(5)
	public void completeTaksByAssignee() {
		String assignee = "yojey";
		List<Task> taskList = taskService.createTaskQuery()//创建任务查询对象
				.processDefinitionKey("QingJia")//根据流程定义的key来查询
				.taskAssignee(assignee)//根据办理人来查询
				.orderByTaskCreateTime()//根据时间倒序
				.desc()
				.list();

		//拿到第一个任务
		Task task = taskList.get(0);
		//使用任务服务接口来办理任务
		taskService.complete(task.getId());
	}

	/**
	 * 查询历史的流程实例
	 */
	@Test
	@Order(6)
	public void selectHistoryProcessInstance() {
		List<HistoricProcessInstance> historicProcessInstanceList = historyService
				.createHistoricProcessInstanceQuery()//创建查询历史流程实例的对象
				.processDefinitionKey("QingJia")
				.orderByProcessInstanceEndTime()
				.desc()
				.list();

		for(HistoricProcessInstance historicProcessInstance : historicProcessInstanceList){
			System.out.println("历史流程实例ID："+historicProcessInstance.getId());
			System.out.println("历史流程实例完成时间："+historicProcessInstance.getEndTime());
		}

	}
}
