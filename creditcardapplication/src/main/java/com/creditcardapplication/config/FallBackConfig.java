package com.creditcardapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.creditcardapplication.bean.ApprovalResponse;
import com.creditcardapplication.bean.CreditCardApprovalBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FallBackConfig {
	
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallbackcall")
	public ApprovalResponse apiCallforapproval(CreditCardApprovalBean approvalRequest) {
		return restTemplate.postForObject(
				"http://credit-card-approval-service/approvalservice/registerforapproval",
				approvalRequest, ApprovalResponse.class);
	}

	/*
	 * fall back method
	 */
	public ApprovalResponse fallbackcall(CreditCardApprovalBean approvalRequest) {
		log.info("fallback method");
		return null;
	}

}
