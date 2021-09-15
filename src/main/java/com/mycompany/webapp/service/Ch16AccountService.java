package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mycompany.webapp.dao.Ch16AccountDao;
import com.mycompany.webapp.dto.Ch16Account;

@Service
public class Ch16AccountService {
	private static final Logger logger = LoggerFactory.getLogger(Ch16AccountService.class);

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
	public void transfer1(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		String result = transactionTemplate.execute(new TransactionCallback<String>() {
			
			
			//어떤 조건이 되면 실행되는 부분이다.
			//어떤 시점에 실행된다.
			//우리는 메서드를 작성해주기만 하면 된다.
			@Override
			public String doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		//출금하기
		Ch16Account fromAccount = accountDao.selectByAno(fromAno);
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		accountDao.updateBalance(fromAccount);
		
		//예금하기
		Ch16Account toAccount = accountDao.selectByAno(toAno);
		toAccount.setBalance(toAccount.getBalance()+amount);
		accountDao.updateBalance(toAccount);
		
	}
	
	public void transfer2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		//출금하기
		Ch16Account fromAccount = accountDao.selectByAno(fromAno);
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		accountDao.updateBalance(fromAccount);
		
		//예금하기
		Ch16Account toAccount = accountDao.selectByAno(toAno);
		toAccount.setBalance(toAccount.getBalance()+amount);
		accountDao.updateBalance(toAccount);
	}
}
