package com.project.template.common.async;

import cn.hutool.extra.spring.SpringUtil;
import com.project.template.common.utils.ServletUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


public class AsyncManager {

  /**
   * 声明带线程池的执行器
   */
  private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

  /**
   * 声明一个自定义类 这个类用来管理定时任务的调度器
   */
  private static AsyncManager asyncManager;

  /**
   * 单例 构造函数私有化
   */
  private AsyncManager() {
    //使用自定义的工具类从spring容器中获取对象
    threadPoolTaskExecutor = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
  }

  /**
   * 单例模式 对外公开的方法 用于获取对象
   */
  public static AsyncManager getInstance() {
    if (asyncManager == null) {
      asyncManager = new AsyncManager();
    }
    return asyncManager;
  }

  /**
   * 传入Runnable类对象 执行器就会执行这个对象的指定的任务 比如把下面的AsyncFactory类的方法作为参数传入
   */
  public void executor(Runnable runnable) {
    // 处理子线程无法获取RequestAttributes问题
    ServletUtils.setLocalRequestAttributes();
    threadPoolTaskExecutor.execute(runnable);
  }

  /**
   * 停止线程运行
   */
  public void shutDown() {
    threadPoolTaskExecutor.shutdown();
  }
}
