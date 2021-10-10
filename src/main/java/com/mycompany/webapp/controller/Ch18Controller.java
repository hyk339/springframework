package com.mycompany.webapp.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch18")
public class Ch18Controller {
   private static final Logger logger = LoggerFactory.getLogger(Ch18Controller.class);
   
   private static int count = 0;
   
   //ExecutorService 
   private ExecutorService executorsService = Executors.newFixedThreadPool(1);
   
   //동시사용자수 몇명? : 
   
   
   @RequestMapping("/content")
   public String content() {
      logger.info("실행");
      
      return "ch18/content";
   }
   
   @RequestMapping("/joinEvent")
   public String joinEvent() throws Exception {
      // 시간 측정 코드(x)
      Callable<Integer> task = new Callable<Integer>() {
         @Override
         public Integer call() throws Exception {
            // 시간 측정 코드(o) // 이 시점을 기준으로 선착순을 정해야함
//            쿠폰을 10개 다 받아갔으면 그 다음 부터는 DB연결 하지 말고 돌려보내야 함
            // Service 객체 호출 코드
            logger.info(Thread.currentThread().getName() + ": 이벤트 처리");

            Ch18Controller.count++;
            if (Ch18Controller.count > 3) {
               return 0;
            } else {
               return 1;
            }
         }
      };

      Future<Integer> future = executorsService.submit(task);
      logger.info(Thread.currentThread().getName() + ": 큐에 작업을 저장");

      // 이밴트 처리가 완료될 때 까지 대기상태가 되게 된다.
      int result = future.get();

      if (result == 1) {
         return "ch18/successEvent";
      } else {
         return "ch18/failEvent";
      }
   }
   
   @RequestMapping(value="/joinEvent2", produces="application/json; charset=UTF-8")
   @ResponseBody
   public String joinEvent2() throws Exception {
      //시간 측정 코드(x)
      Callable<Integer> task = new Callable<Integer>() {         
         @Override
         public Integer call() throws Exception {
            //시간 측정 코드(o)
            //Service 객체 호출 코드
            logger.info(Thread.currentThread().getName() + ": 이벤트 처리");
            
            Ch18Controller.count++;
            if(Ch18Controller.count > 10) {
               return 0;
            } else {
               return 1;
            }
         }
      };
      
      Future<Integer> future = executorsService.submit(task);
      logger.info(Thread.currentThread().getName() + ": 큐에 작업을 저장");
      
      //이벤트 처리가 완료될 때까지 대기
      int result = future.get();
      
      JSONObject jsonObject = new JSONObject();
      if(result == 1) {
         jsonObject.put("result", "success");
      } else {
         jsonObject.put("result", "fail");
      }
      return jsonObject.toString();
   }   
}