package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mycompany.webapp.dao.Ch16AccountDao;
import com.mycompany.webapp.dto.Ch16Account;
import com.mycompany.webapp.exception.Ch16NotFoundAccountException;

@Service
public class Ch16AccountService {
	private static final Logger logger = LoggerFactory.getLogger(Ch16AccountService.class);

	public enum TransferResult{
		SUCCESS,
		FAIL_NOT_FOUND_ACCOUNT,
		FAIL_NOT_ENOUGH_BALANCE
	}
	
	
	@Resource
	private TransactionTemplate transactionTemplate;
	
	@Resource
	private Ch16AccountDao accountDao;
	
	public List<Ch16Account> getAccounts(){
		logger.info("실행");
		List<Ch16Account> accounts = accountDao.selectAll();
		return accounts;
	}
	
	//프로그래밍 방식으로 transaction처리하는 business 로직1
	public TransferResult transfer1(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		//민약 작업처리 결과에 따라 할게 없다면 아래처럼 사용하면 되지만 String을 그냥 쓰자.
		//transactionTemplate.execute(new TransactionCallback<Void>() {
		//public void doInTransaction(TransactionStatus status) {
		String result = transactionTemplate.execute(new TransactionCallback<String>() {
			
			
			//어떤 조건이 되면 실행되는 부분이다.
			//어떤 시점에 실행된다.
			//우리는 메서드를 작성해주기만 하면 된다.
			@Override
			public String doInTransaction(TransactionStatus status) {
				try { //예외처리 코드를 직접 작성해야한다.
				//출금하기
				Ch16Account fromAccount = accountDao.selectByAno(fromAno);
				fromAccount.setBalance(fromAccount.getBalance()-amount);
				accountDao.updateBalance(fromAccount);
				
				//예금하기
				Ch16Account toAccount = accountDao.selectByAno(toAno);
				toAccount.setBalance(toAccount.getBalance()+amount);
				accountDao.updateBalance(toAccount);
				return "success";
				} catch(Exception e) {
					//어디에서 예외가 발생하건
					//트랜잭션 작업을 모두 취소한다.
					status.setRollbackOnly();
					return "fail";
				}
			}
		});
		
		
		//위에서 String result를 사용하는 이유는 결과값에 따라 추가작업을 하도록하게 하기위함이다.
		//만약 추가작업을 하지 않도록 하려면 
		if(result.equals("success")) {
			//추가 작업
			//이렇게 정상적으로 값을 넘겨줄수도 있다.
			return TransferResult.SUCCESS;
		} else {
			//추가 작업
			return TransferResult.FAIL_NOT_FOUND_ACCOUNT;
			//throw new Ch16NotFoundAccountException("계좌가 존재하지 않습니다.");
		}
	}
	
	
	//@transactional이 내부적으로 aop를 사용한다.
	//@transactional을 사용할때는 exception을 반드시 써주어야한다.
	//위와 비교할때 여기는 명시적으로 rollback하라는 부분이 없다.
	//exception은 runtimeexception이어야한다.
	//이 선언적 방식은 예외가 발
	@Transactional
	public void transfer2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		try { 
			//출금하기
			Ch16Account fromAccount = accountDao.selectByAno(fromAno);
			fromAccount.setBalance(fromAccount.getBalance()-amount);
			accountDao.updateBalance(fromAccount);
			
			//예금하기
			Ch16Account toAccount = accountDao.selectByAno(toAno);
			toAccount.setBalance(toAccount.getBalance()+amount);
			accountDao.updateBalance(toAccount);
		} catch(Exception e) {
			throw new Ch16NotFoundAccountException("계좌가 존재하지 않습니다.");
		}
	}
}
