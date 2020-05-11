package com.web.mvc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import com.web.mvc.request.AddEmployeeRequest;
import com.web.mvc.response.AddEmployeeResponse;
import com.web.mvc.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {


	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


	@Autowired
	private HazelcastInstance hazelcastInstance;


	@Override
	public AddEmployeeResponse addEmployee(AddEmployeeRequest employeeRequest) {
		AddEmployeeResponse response=null;
		String employeeNameTobeAdded = employeeRequest.getName();
		//take a lock on employeeName to make sure only one employee with this name processed in the cluster
		ILock lock = hazelcastInstance.getLock(employeeNameTobeAdded);
		if (lock.tryLock ()) {
			try{
				// this will make sure to sleep the 1st req for 10000 ms, so tht we cna reproduce the effect;
				Thread.sleep(10000);
				response = new AddEmployeeResponse(1l,true,"success");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					if(null!=lock){
						lock.unlock();
						lock.destroy();
					}
				}catch(Exception ex){
					logger.warn("Error at release lock");
				}
			}
		}else{
			response= new AddEmployeeResponse(null,false,"allready in progress");
			//throw new RuntimeException("Unable to obtain lock, seems same request is already in progress");
		}
		return response;
	}

}
