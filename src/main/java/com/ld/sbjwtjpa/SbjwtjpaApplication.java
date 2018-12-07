package com.ld.sbjwtjpa;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//自动获取所有的组件，包含配置类注解
//@ComponentScan
//配置类注解(如果不用自动获取所有组件的注解，应该用@Import引入配置)
//@Configuration
//自动装配
//@EnableAutoConfiguration
//使用@ImportResource注释来加载XML配置文件。
//@EnableAutoConfiguration（exclude = {DataSourceAutoConfiguration.class}）禁用当前自动配置类

/**
 * 某些事件实际上ApplicationContext是在创建之前触发的，因此您无法在这些事件上注册侦听器@Bean。
 * 您可以使用SpringApplication.addListeners(…​)方法或 SpringApplicationBuilder.listeners(…​)方法注册它们 。
 * 如果您希望自动注册这些侦听器，无论应用程序的创建方式如何，您都可以META-INF/spring.factories向项目
 * 添加文件并使用该org.springframework.context.ApplicationListener键引用侦听器 ，如以下示例所示：
 * org.springframework.context.ApplicationListener = com.example.project.MyListener
 * <p>
 * 应用程序运行时，应按以下顺序发送应用程序事件：
 * 一个ApplicationStartingEvent是在一个运行的开始，但任何处理之前被发送，除了听众和初始化的注册。
 * 一个ApplicationEnvironmentPreparedEvent当被发送Environment到中已知的上下文中使用，但是在创建上下文之前。
 * 一个ApplicationPreparedEvent刷新开始前，刚刚发，但之后的bean定义已经被加载。
 * 一个ApplicationStartedEvent上下文已被刷新后发送，但是任何应用程序和命令行亚军都被调用前。
 * 的ApplicationReadyEvent任何应用程序和命令行亚军被呼叫后发送。它表示应用程序已准备好为请求提供服务。
 * 一个ApplicationFailedEvent如果在启动时异常发送。
 * <p>
 * 如果您需要在启动后运行某些特定代码SpringApplication，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作，并提供单个run方法，在SpringApplication.run(…​)完成之前调用 。
 * <p>
 * 每个都SpringApplication注册一个与JVM的关闭钩子，以确保 ApplicationContext在退出时正常关闭。
 * 可以使用所有标准的Spring生命周期回调（例如DisposableBean接口或@PreDestroy注释）。
 * <p>
 * 应用程序运行时，应按以下顺序发送应用程序事件：
 * 一个ApplicationStartingEvent是在一个运行的开始，但任何处理之前被发送，除了听众和初始化的注册。
 * 一个ApplicationEnvironmentPreparedEvent当被发送Environment到中已知的上下文中使用，但是在创建上下文之前。
 * 一个ApplicationPreparedEvent刷新开始前，刚刚发，但之后的bean定义已经被加载。
 * 一个ApplicationStartedEvent上下文已被刷新后发送，但是任何应用程序和命令行亚军都被调用前。
 * 的ApplicationReadyEvent任何应用程序和命令行亚军被呼叫后发送。它表示应用程序已准备好为请求提供服务。
 * 一个ApplicationFailedEvent如果在启动时异常发送。
 * <p>
 * 如果您需要在启动后运行某些特定代码SpringApplication，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作，并提供单个run方法，在SpringApplication.run(…​)完成之前调用 。
 * <p>
 * 每个都SpringApplication注册一个与JVM的关闭钩子，以确保 ApplicationContext在退出时正常关闭。
 * 可以使用所有标准的Spring生命周期回调（例如DisposableBean接口或@PreDestroy注释）。
 * <p>
 * 应用程序运行时，应按以下顺序发送应用程序事件：
 * 一个ApplicationStartingEvent是在一个运行的开始，但任何处理之前被发送，除了听众和初始化的注册。
 * 一个ApplicationEnvironmentPreparedEvent当被发送Environment到中已知的上下文中使用，但是在创建上下文之前。
 * 一个ApplicationPreparedEvent刷新开始前，刚刚发，但之后的bean定义已经被加载。
 * 一个ApplicationStartedEvent上下文已被刷新后发送，但是任何应用程序和命令行亚军都被调用前。
 * 的ApplicationReadyEvent任何应用程序和命令行亚军被呼叫后发送。它表示应用程序已准备好为请求提供服务。
 * 一个ApplicationFailedEvent如果在启动时异常发送。
 * <p>
 * 如果您需要在启动后运行某些特定代码SpringApplication，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作，并提供单个run方法，在SpringApplication.run(…​)完成之前调用 。
 * <p>
 * 每个都SpringApplication注册一个与JVM的关闭钩子，以确保 ApplicationContext在退出时正常关闭。
 * 可以使用所有标准的Spring生命周期回调（例如DisposableBean接口或@PreDestroy注释）。
 * <p>
 * 应用程序运行时，应按以下顺序发送应用程序事件：
 * 一个ApplicationStartingEvent是在一个运行的开始，但任何处理之前被发送，除了听众和初始化的注册。
 * 一个ApplicationEnvironmentPreparedEvent当被发送Environment到中已知的上下文中使用，但是在创建上下文之前。
 * 一个ApplicationPreparedEvent刷新开始前，刚刚发，但之后的bean定义已经被加载。
 * 一个ApplicationStartedEvent上下文已被刷新后发送，但是任何应用程序和命令行亚军都被调用前。
 * 的ApplicationReadyEvent任何应用程序和命令行亚军被呼叫后发送。它表示应用程序已准备好为请求提供服务。
 * 一个ApplicationFailedEvent如果在启动时异常发送。
 * <p>
 * 如果您需要在启动后运行某些特定代码SpringApplication，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作，并提供单个run方法，在SpringApplication.run(…​)完成之前调用 。
 * <p>
 * 每个都SpringApplication注册一个与JVM的关闭钩子，以确保 ApplicationContext在退出时正常关闭。
 * 可以使用所有标准的Spring生命周期回调（例如DisposableBean接口或@PreDestroy注释）。
 */

/**
 * 应用程序运行时，应按以下顺序发送应用程序事件：
 * 一个ApplicationStartingEvent是在一个运行的开始，但任何处理之前被发送，除了听众和初始化的注册。
 * 一个ApplicationEnvironmentPreparedEvent当被发送Environment到中已知的上下文中使用，但是在创建上下文之前。
 * 一个ApplicationPreparedEvent刷新开始前，刚刚发，但之后的bean定义已经被加载。
 * 一个ApplicationStartedEvent上下文已被刷新后发送，但是任何应用程序和命令行亚军都被调用前。
 * 的ApplicationReadyEvent任何应用程序和命令行亚军被呼叫后发送。它表示应用程序已准备好为请求提供服务。
 * 一个ApplicationFailedEvent如果在启动时异常发送。
 */

/**
 * 如果您需要在启动后运行某些特定代码SpringApplication，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作，并提供单个run方法，在SpringApplication.run(…​)完成之前调用 。
 */

/**
 * 每个都SpringApplication注册一个与JVM的关闭钩子，以确保 ApplicationContext在退出时正常关闭。
 * 可以使用所有标准的Spring生命周期回调（例如DisposableBean接口或@PreDestroy注释）。
 */

/**
 * @EnableAsync 启用异步方式处理任务，要使用此注解请详细查询使用方法以及配套的其它注解
 */
//启用缓存支持
//@EnableCaching
@SpringBootApplication
public class SbjwtjpaApplication {

    public static void main(String[] args) {
//        启动之前设置某些参数
//        System.setProperty（“spring.devtools.restart.enabled”，“false”）;
//        SpringApplication.run(SbjwtjpaApplication.class, args);
        SpringApplication application = new SpringApplication(SbjwtjpaApplication.class);
//        代码方式关闭条幅打印
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
